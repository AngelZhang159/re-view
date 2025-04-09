package dev.angelzhang.mediaservice.dto.details;

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
