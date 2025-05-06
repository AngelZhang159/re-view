package dev.angelzhang.reviewservice;

import dev.angelzhang.reviewservice.clients.MediaClient;
import dev.angelzhang.reviewservice.dto.ReviewRequest;
import dev.angelzhang.reviewservice.dto.ReviewResponse;
import dev.angelzhang.reviewservice.dto.media.details.DetailsAPIResponse;
import dev.angelzhang.reviewservice.entities.MovieReview;
import dev.angelzhang.reviewservice.entities.Review;
import dev.angelzhang.reviewservice.entities.TVReview;
import dev.angelzhang.reviewservice.enums.Type;
import dev.angelzhang.reviewservice.mapper.ReviewMapper;
import dev.angelzhang.reviewservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MediaClient mediaClient;
    private final JwtUtil jwtUtil;

    public ResponseEntity<ReviewResponse> createReview(String token, ReviewRequest reviewRequest) {

        Long userId = jwtUtil.extractUserId(token.substring(7));

        if (reviewAlreadyExists(userId, reviewRequest.type(), reviewRequest.mediaId())) {
            return ResponseEntity.badRequest().build();
        }

        //prefetch review to keep it in the db for future access and not saturate the api key
        DetailsAPIResponse details = mediaClient.details(token, reviewRequest.type(), reviewRequest.mediaId());

        if (details == null) return ResponseEntity.badRequest().build();

        Review review = new Review();
        if (reviewRequest.type().equals(Type.TV.getLabel())) {
            review = ReviewMapper.toTVReviewEntity(userId, reviewRequest);
        } else if (reviewRequest.type().equals(Type.MOVIE.getLabel())) {
            review = ReviewMapper.toMovieReviewEntity(userId, reviewRequest);
        }

        Review savedReview = reviewRepository.save(review);

        ReviewResponse reviewResponse = ReviewMapper.toDTO(savedReview, getDetails(token, review));
        return ResponseEntity.ok(reviewResponse);
    }

    private boolean reviewAlreadyExists(Long userId, String type, Long mediaId) {
        List<Review> byUserIdAndType = reviewRepository.findByUserIdAndType(userId, Type.fromString(type));

        return byUserIdAndType.stream().map(review ->
                        review instanceof MovieReview ? ((MovieReview) review).getMovieId() : ((TVReview) review).getTvId())
                .anyMatch(id -> id.equals(mediaId));
    }

    public ResponseEntity<ReviewResponse> getReview(String token, Long reviewId) {
        Review review = findValidateReview(token, reviewId);
        if (review == null) {
            log.info("Review with id: {} not found", reviewId);
            return ResponseEntity.notFound().build();
        }

        ReviewResponse reviewResponse = ReviewMapper.toDTO(review, getDetails(token, review));
        return ResponseEntity.ok(reviewResponse);
    }

    public ResponseEntity<ReviewResponse> deleteReview(String token, Long reviewId) {

        Review review = findValidateReview(token, reviewId);

        if (review == null) return ResponseEntity.notFound().build();
        ReviewResponse reviewResponse = ReviewMapper.toDTO(review, getDetails(token, review));
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

        if (review == null) {
            log.info("Updating review with id: {} not found", reviewId);
            return ResponseEntity.notFound().build();
        }

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
        ReviewResponse reviewResponse = ReviewMapper.toDTO(review, getDetails(token, review));
        return ResponseEntity.ok(reviewResponse);
    }

    public ResponseEntity<Page<ReviewResponse>> getAllReviews(String token, Long userId, String type, Integer page, Integer size) {

        Long tokenUserId = jwtUtil.extractUserId(token.substring(7));

        Pageable pageable = Pageable.ofSize(size).withPage(page);

        Page<Review> reviewPage;
        if (userId == null) {
            if (type == null || type.isBlank()) {
                reviewPage = reviewRepository.findAllByUserId(tokenUserId, pageable);
            } else {
                reviewPage = reviewRepository.findByTypeAndUserId(Type.fromString(type), tokenUserId, pageable);
            }
        } else {
            if (type == null || type.isBlank()) {
                reviewPage = reviewRepository.findAllByUserId(userId, pageable);
            } else {
                reviewPage = reviewRepository.findByTypeAndUserId(Type.fromString(type), tokenUserId, pageable);
            }
        }

//        if (reviewPage.isEmpty()) return ResponseEntity.notFound().build();

        //TODO
        //right now any user can see any review by any user
        //is itself? is that profile public? is friend?
        //TODO change this to call a new method, from current review, get id and type of media and make a Map<Media, ID>
        //call in bulk to the media service to get all details and then map them to the ReviewResponse
        Page<ReviewResponse> reviewResponsePage = reviewPage.map((review) -> ReviewMapper.toDTO(review, getDetails(token, review)));

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

    public ResponseEntity<ReviewResponse> getReviewByParameters(String token, String mediaType, Long mediaId) {
        Long userId = jwtUtil.extractUserId(token.substring(7));
        Type type = Type.fromString(mediaType);

        List<Review> byUserIdAndType = reviewRepository.findByUserIdAndType(userId, type);

        Review review = byUserIdAndType.stream().filter(review1 -> switch (type) {
            case TV -> Objects.equals(((TVReview) review1).getTvId(), mediaId);
            case MOVIE -> Objects.equals(((MovieReview) review1).getMovieId(), mediaId);
        }).findFirst().orElse(null);

        if (review == null) return ResponseEntity.badRequest().build();

        ReviewResponse response = ReviewMapper.toDTO(review, getDetails(token, review));
        return ResponseEntity.ok(response);
    }
}
