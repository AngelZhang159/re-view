package dev.angelzhang.reviewservice.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

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
