package dev.angelzhang.reviewservice.dto.media.details;

public record Season(
         Integer id,
         String air_date,
         Integer episode_count,
         String name,
         String overview,
         String poster_path,
         Integer season_number,
         Double vote_average
) {
}
