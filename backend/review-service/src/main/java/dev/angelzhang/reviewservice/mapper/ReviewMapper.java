package dev.angelzhang.reviewservice.mapper;

import dev.angelzhang.reviewservice.dto.ReviewRequest;
import dev.angelzhang.reviewservice.dto.ReviewResponse;
import dev.angelzhang.reviewservice.dto.media.details.DetailsAPIResponse;
import dev.angelzhang.reviewservice.entities.MovieReview;
import dev.angelzhang.reviewservice.entities.Review;
import dev.angelzhang.reviewservice.entities.TVReview;
import dev.angelzhang.reviewservice.enums.Type;

import java.time.Instant;

public class ReviewMapper {
    public static ReviewResponse toDTO(Review review, DetailsAPIResponse detailsAPIResponse) {
        return new ReviewResponse(
                review.getId(),
                review.getUserId(),
                review.getType().toString(),
                detailsAPIResponse.id(),
                detailsAPIResponse.title() == null || detailsAPIResponse.title().isBlank() ? detailsAPIResponse.name() : detailsAPIResponse.title(),
                detailsAPIResponse.poster_path(),
                review.getReview(),
                review.getRating(),
                detailsAPIResponse.number_of_seasons(),
                review instanceof TVReview ? ((TVReview) review).getSeasonsWatched() : null,
                review.getCreatedAt(),
                review.getUpdatedAt()
        );
    }
    public static TVReview toTVReviewEntity(Long userId, ReviewRequest request) {
        return TVReview.builder()
                .userId(userId)
                .type(Type.fromString(request.type()))
                .review(request.review())
                .rating(request.rating())
                .seasonsWatched(request.seasonsWatched())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .tvId(request.mediaId())
                .build();
    }

    public static MovieReview toMovieReviewEntity(Long userId, ReviewRequest reviewRequest) {
        return MovieReview.builder()
                .userId(userId)
                .type(Type.fromString(reviewRequest.type()))
                .review(reviewRequest.review())
                .rating(reviewRequest.rating())
                .movieId(reviewRequest.mediaId())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }
}
