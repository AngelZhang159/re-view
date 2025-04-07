package dev.angelzhang.reviewservice;

import dev.angelzhang.reviewservice.clients.MediaClient;
import dev.angelzhang.reviewservice.dto.ReviewRequest;
import dev.angelzhang.reviewservice.dto.ReviewResponse;
import dev.angelzhang.reviewservice.dto.media.details.DetailsAPIResponse;
import dev.angelzhang.reviewservice.entities.MovieReview;
import dev.angelzhang.reviewservice.entities.Review;
import dev.angelzhang.reviewservice.entities.TVReview;
import dev.angelzhang.reviewservice.enums.Type;
import dev.angelzhang.reviewservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MediaClient mediaClient;
    private final JwtUtil jwtUtil;

    public ResponseEntity<ReviewResponse> createReview(String token, ReviewRequest reviewRequest) {

        Long userId = jwtUtil.extractUserId(token.substring(7));

        //prefetch review to keep it in the db for future access and not saturate the api key
        DetailsAPIResponse details = mediaClient.details(token, reviewRequest.type(), reviewRequest.mediaId());

        if (details == null) return ResponseEntity.badRequest().build();

        Review review = new Review();
        if (reviewRequest.type().equals(Type.TV.getLabel())) {
            review = TVReview.fromRequest(userId, reviewRequest);
        } else if (reviewRequest.type().equals(Type.MOVIE.getLabel())) {
            review = MovieReview.fromRequest(userId, reviewRequest);
        }

        Review savedReview = reviewRepository.save(review);

        ReviewResponse reviewResponse = ReviewResponse.toResponse(savedReview, getDetails(token, review));
        return ResponseEntity.ok(reviewResponse);
    }

    public ResponseEntity<ReviewResponse> getReview(String token, Long reviewId) {
        Review review = findValidateReview(token, reviewId);
        if (review == null) return ResponseEntity.notFound().build();

        DetailsAPIResponse detailsAPIResponse = getDetails(token, review);
        ReviewResponse reviewResponse = ReviewResponse.toResponse(review, getDetails(token, review));
        return ResponseEntity.ok(reviewResponse);
    }

    public ResponseEntity<ReviewResponse> deleteReview(String token, Long reviewId) {

        Review review = findValidateReview(token, reviewId);

        if (review == null) return ResponseEntity.notFound().build();
        ReviewResponse reviewResponse = ReviewResponse.toResponse(review, getDetails(token, review));
        reviewRepository.deleteById(reviewId);

        return ResponseEntity.ok(reviewResponse);
    }

    private Review findValidateReview(String token, Long reviewId) {
        Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
        if (reviewOptional.isEmpty()) return null;

        Review review = reviewOptional.get();
        if (!review.getUserId().equals(jwtUtil.extractUserId(token.substring(7)))) return null;

        return review;

    }

    public ResponseEntity<ReviewResponse> updateReview(String token, Long reviewId, ReviewRequest reviewRequest) {
        Review review = findValidateReview(token, reviewId);

        if (review == null) return ResponseEntity.notFound().build();

        if (reviewRequest.type() != null) review.setType(Type.fromString(reviewRequest.type()));
        if (reviewRequest.review() != null) {
            if (reviewRequest.review().isBlank()) {
                review.setReview(null);
            } else {
                review.setReview(reviewRequest.review());
            }
        }
        if (reviewRequest.rating() != null) review.setRating(reviewRequest.rating());
        if (reviewRequest.seasonsWatched() != null && review instanceof TVReview) {
            ((TVReview) review).setSeasonsWatched(reviewRequest.seasonsWatched());
        }
        if (reviewRequest.mediaId() != null) {
            if (review instanceof MovieReview) {
                ((MovieReview) review).setMovieId(reviewRequest.mediaId());
            } else if (review instanceof TVReview) {
                ((TVReview) review).setTvId(reviewRequest.mediaId());
            }
        }
        review.setUpdatedAt(Instant.now());
        reviewRepository.save(review);
        ReviewResponse reviewResponse = ReviewResponse.toResponse(review, getDetails(token, review));
        return ResponseEntity.ok(reviewResponse);
    }

    public ResponseEntity<Page<ReviewResponse>> getAllReviews(String token, Long userId, String type, Integer page, Integer size) {

        Long tokenUserId = jwtUtil.extractUserId(token.substring(7));

        Pageable pageable = Pageable.ofSize(size).withPage(page);

        Page<Review> reviewPage;
        if (userId == null) {
            if (type == null || type.isBlank()) {
                reviewPage = reviewRepository.findAll(pageable);
            } else {
                reviewPage = reviewRepository.findByTypeAndUserId(Type.fromString(type), tokenUserId, pageable);
            }
        } else {
            if (type == null || type.isBlank()) {
                reviewPage = reviewRepository.findByUserId(userId, pageable);
            } else {
                reviewPage = reviewRepository.findByTypeAndUserId(Type.fromString(type), tokenUserId, pageable);
            }
        }

        if (reviewPage.isEmpty()) return ResponseEntity.notFound().build();

        //TODO
        //is itself? is that profile public? is friend?
        Page<ReviewResponse> reviewResponsePage = reviewPage.map((review) -> ReviewResponse.toResponse(review, getDetails(token, review)));

        return ResponseEntity.ok((reviewResponsePage));
    }


    private DetailsAPIResponse getDetails(String token, Review review) {

        if (review instanceof TVReview) {
            return mediaClient.details(token, Type.TV.getLabel(), ((TVReview) review).getTvId());
        } else if (review instanceof MovieReview) {
            return mediaClient.details(token, Type.MOVIE.getLabel(), ((MovieReview) review).getMovieId());
        }
        throw new UnsupportedOperationException("Not supported review type");
    }
}
