package dev.angelzhang.mediaservice.dto.details;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record CreatedBy(
        Integer id,
        String creditId,
        String name,
        String originalName,
        Integer gender,
        String profilePath
) {
}
