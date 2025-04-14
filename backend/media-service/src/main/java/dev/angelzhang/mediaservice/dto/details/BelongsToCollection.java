package dev.angelzhang.mediaservice.dto.details;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record BelongsToCollection(
        Integer id,
        String name,
        String posterPath,
        String backdropPath
) {
}
