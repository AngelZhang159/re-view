package dev.angelzhang.mediaservice.dto.details;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record Season(
        Integer id,
        String airDate,
        Integer episodeCount,
        String name,
        String overview,
        String posterPath,
        Integer seasonNumber,
        Double voteAverage
) {
}
