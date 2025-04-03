package dev.angelzhang.reviewservice.dto;


import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record ReviewRequest(
        @NotNull(message = "Type cannot be null (movie or tv)")
        String type,
        @NotNull(message = "Media ID cannot be null")
        Long mediaId,
        @Length(max = 2048, message = "Review cannot exceed 2048 characters")
        String review,
        @NotNull(message = "Rating cannot be null")
        Integer rating,
        Integer seasonsWatched
) {
}
