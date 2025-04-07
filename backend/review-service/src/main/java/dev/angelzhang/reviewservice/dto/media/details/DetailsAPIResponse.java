package dev.angelzhang.reviewservice.dto.media.details;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record DetailsAPIResponse(
        Boolean adult,
        String backdrop_path,
        BelongsToCollection belongs_to_collection,
        Integer budget,
        List<Genre> genres,
        String homepage,
        Long id,
        String imdb_id,
        List<String> origin_country,
        String original_language,
        String original_title,
        String overview,
        Double popularity,
        String poster_path,
        List<ProductionCompany> production_companies,
        List<ProductionCountry> production_countries,
        String release_date,
        Double revenue,
        Integer runtime,
        List<SpokenLanguage> spoken_languages,
        String status,
        String tagline,
        String title,
        Boolean video,
        Double vote_average,
        Integer vote_count,
        List<CreatedBy> created_by,
        List<Integer> episode_run_time,
        String first_air_date,
        Boolean in_production,
        List<String> languages,
        String last_air_date,
        Episode last_episode_to_air,
        String name,
        Episode next_episode_to_air,
        List<Network> networks,
        Integer number_of_episodes,
        Integer number_of_seasons,
        String original_name,
        List<Season> seasons,
        String type,
        ArrayList<String> also_known_as,
        String biography,
        String birthday,
        String deathday,
        Integer gender,
        String known_for_department,
        String place_of_birth,
        String profile_path,
        String media_type
) {
}
