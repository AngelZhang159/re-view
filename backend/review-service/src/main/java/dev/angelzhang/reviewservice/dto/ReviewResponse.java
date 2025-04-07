package dev.angelzhang.reviewservice.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.angelzhang.reviewservice.dto.media.details.DetailsAPIResponse;
import dev.angelzhang.reviewservice.entities.Review;
import dev.angelzhang.reviewservice.entities.TVReview;

import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ReviewResponse(
        Long id,
        String userId,
        String mediaType,
        Long mediaId,
        String mediaTitle,
        String mediaPosterPath,
        String review,
        Integer rating,
        Integer seasons,
        Integer seasonsWatched,
        Instant createdAt,
        Instant updatedAt
) {
    public static ReviewResponse toResponse(Review review, DetailsAPIResponse detailsAPIResponse) {
        return new ReviewResponse(
                review.getId(),
                review.getUserId().toString(),
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
}
