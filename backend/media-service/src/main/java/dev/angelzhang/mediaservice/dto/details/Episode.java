package dev.angelzhang.mediaservice.dto.details;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record Episode(
        Integer id,
        String name,
        String overview,
        Double voteAverage,
        Integer voteCount,
        String airDate,
        Integer episodeNumber,
        String episodeType,
        String productionCode,
        Integer runtime,
        Integer seasonNumber,
        Integer showId,
        String stillPath
) {
}
