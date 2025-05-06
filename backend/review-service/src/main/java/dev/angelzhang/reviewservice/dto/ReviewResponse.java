package dev.angelzhang.reviewservice.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import dev.angelzhang.reviewservice.dto.media.details.DetailsAPIResponse;
import dev.angelzhang.reviewservice.entities.Review;
import dev.angelzhang.reviewservice.entities.TVReview;

import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ReviewResponse(
        Long id,
        Long userId,
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

}
