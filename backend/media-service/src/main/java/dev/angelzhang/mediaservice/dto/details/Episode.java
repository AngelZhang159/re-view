package dev.angelzhang.mediaservice.dto.details;

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
