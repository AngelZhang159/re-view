package dev.angelzhang.reviewservice.dto.media.details;

public record Episode(
         Integer id,
         String name,
         String overview,
         Double vote_average,
         Integer vote_count,
         String air_date,
         Integer episode_number,
         String episode_type,
         String production_code,
         Integer runtime,
         Integer season_number,
         Integer show_id,
         String still_path
) {
}
