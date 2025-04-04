package dev.angelzhang.mediaservice.dto.details;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.angelzhang.mediaservice.entities.details.CountryCode;
import dev.angelzhang.mediaservice.entities.details.MovieDetails;
import dev.angelzhang.mediaservice.entities.details.TVDetails;

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
        Integer id,
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
    public static DetailsAPIResponse toTVDetailsResponse(TVDetails tvDetails) {
        return new DetailsAPIResponse(
                tvDetails.getAdult(),
                tvDetails.getBackdrop_path(),
                null, // belongs_to_collection (not in TVDetails)
                null, // budget (not in TVDetails)
                dev.angelzhang.mediaservice.entities.details.Genre.toResponse(tvDetails.getGenres()),
                tvDetails.getHomepage(),
                tvDetails.getId(),
                null, // imdb_id (not in TVDetails)
                tvDetails.getOrigin_country() != null ?
                        CountryCode.toResponse(tvDetails.getOrigin_country()) :
                        null,
                tvDetails.getOriginal_language(),
                null, // original_title (not in TVDetails)
                tvDetails.getOverview(),
                tvDetails.getPopularity(),
                tvDetails.getPoster_path(),
                dev.angelzhang.mediaservice.entities.details.ProductionCompany.toResponse(tvDetails.getProduction_companies()),
                dev.angelzhang.mediaservice.entities.details.ProductionCountry.toResponse(tvDetails.getProduction_countries()),
                null, // release_date (not in TVDetails)
                null, // revenue (not in TVDetails)
                null, // runtime (not in TVDetails)
                dev.angelzhang.mediaservice.entities.details.SpokenLanguage.toResponse(tvDetails.getSpoken_languages()),
                tvDetails.getStatus(),
                tvDetails.getTagline(),
                null, // title (not in TVDetails)
                null, // video (not in TVDetails)
                tvDetails.getVote_average(),
                tvDetails.getVote_count(),
                dev.angelzhang.mediaservice.entities.details.CreatedBy.toResponse(tvDetails.getCreated_by()),
                tvDetails.getEpisode_run_time(),
                tvDetails.getFirst_air_date(),
                tvDetails.getIn_production(),
                tvDetails.getLanguages(),
                tvDetails.getLast_air_date(),
                dev.angelzhang.mediaservice.entities.details.Episode.toResponse(tvDetails.getLast_episode_to_air()),
                tvDetails.getName(),
                dev.angelzhang.mediaservice.entities.details.Episode.toResponse(tvDetails.getNext_episode_to_air()),
                dev.angelzhang.mediaservice.entities.details.Network.toResponse(tvDetails.getNetworks()),
                tvDetails.getNumber_of_episodes(),
                tvDetails.getNumber_of_seasons(),
                tvDetails.getOriginal_name(),
                dev.angelzhang.mediaservice.entities.details.Season.toResponse(tvDetails.getSeasons()),
                tvDetails.getType(),
                null, // also_known_as (not in TVDetails)
                null, // biography (not in TVDetails)
                null, // birthday (not in TVDetails)
                null, // deathday (not in TVDetails)
                null, // gender (not in TVDetails)
                null, // known_for_department (not in TVDetails)
                null, // place_of_birth (not in TVDetails)
                null,  // profile_path (not in TVDetails)
                "tv"
        );
    }

    public static DetailsAPIResponse toMovieDetailsResponse(MovieDetails movieDetails) {
        return new DetailsAPIResponse(
                movieDetails.getAdult(),
                movieDetails.getBackdrop_path(),
                dev.angelzhang.mediaservice.entities.details.BelongsToCollection.toResponse(movieDetails.getBelongs_to_collection()),
                movieDetails.getBudget(),
                dev.angelzhang.mediaservice.entities.details.Genre.toResponse(movieDetails.getGenres()),
                movieDetails.getHomepage(),
                movieDetails.getId(),
                movieDetails.getImdb_id(),
                null, // origin_country (not in MovieDetails)
                movieDetails.getOriginal_language(),
                movieDetails.getOriginal_title(),
                movieDetails.getOverview(),
                movieDetails.getPopularity(),
                movieDetails.getPoster_path(),
                dev.angelzhang.mediaservice.entities.details.ProductionCompany.toResponse(movieDetails.getProduction_companies()),
                dev.angelzhang.mediaservice.entities.details.ProductionCountry.toResponse(movieDetails.getProduction_countries()),
                movieDetails.getRelease_date(),
                movieDetails.getRevenue(),
                movieDetails.getRuntime(),
                dev.angelzhang.mediaservice.entities.details.SpokenLanguage.toResponse(movieDetails.getSpoken_languages()),
                movieDetails.getStatus(), // status (not in MovieDetails)
                movieDetails.getTagline(), // tagline (not in MovieDetails)
                movieDetails.getTitle(),
                movieDetails.getVideo(),
                movieDetails.getVote_average(),
                movieDetails.getVote_count(),
                null, // created_by (not in MovieDetails)
                null, // episode_run_time (not in MovieDetails)
                null, // first_air_date (not in MovieDetails)
                null, // in_production (not in MovieDetails)
                null, // languages (not in MovieDetails)
                null, // last_air_date (not in MovieDetails)
                null, // last_episode_to_air (not in MovieDetails)
                null, // name (not in MovieDetails)
                null, // next_episode_to_air (not in MovieDetails)
                null, // networks (not in MovieDetails)
                null, // number_of_episodes (not in MovieDetails)
                null, // number_of_seasons (not in MovieDetails)
                null, // original_name (not in MovieDetails)
                null, // seasons (not in MovieDetails)
                null, // type (not in MovieDetails)
                null, // also_known_as (not in MovieDetails)
                null, // biography (not in MovieDetails)
                null, // birthday (not in MovieDetails)
                null, // deathday (not in
                null,
                null,
                null,
                null,
                "movie");
    }
}
