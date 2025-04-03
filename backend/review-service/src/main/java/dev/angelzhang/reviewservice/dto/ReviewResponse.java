package dev.angelzhang.reviewservice.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import dev.angelzhang.reviewservice.entities.Review;
import dev.angelzhang.reviewservice.entities.TVReview;

import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ReviewResponse(
        Long id,
        String userId,
        String type,
        String review,
        Integer rating,
        Integer seasonsWatched,
        Instant createdAt,
        Instant updatedAt
) {
    public static ReviewResponse toResponse(Review review) {
        return new ReviewResponse(
                review.getId(),
                review.getUserId().toString(),
                review.getType().toString(),
                review.getReview(),
                review.getRating(),
                review instanceof TVReview ? ((TVReview) review).getSeasonsWatched() : null,
                review.getCreatedAt(),
                review.getUpdatedAt()
        );
    }
}
