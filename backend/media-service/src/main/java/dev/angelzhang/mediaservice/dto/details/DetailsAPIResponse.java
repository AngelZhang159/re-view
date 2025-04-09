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
        String backdropPath,
        BelongsToCollection belongsToCollection,
        Integer budget,
        List<Genre> genres,
        String homepage,
        Long id,
        String imdbId,
        List<String> originCountry,
        String originalLanguage,
        String originalTitle,
        String overview,
        Double popularity,
        String posterPath,
        List<ProductionCompany> productionCompanies,
        List<ProductionCountry> productionCountries,
        String releaseDate,
        Double revenue,
        Integer runtime,
        List<SpokenLanguage> spokenLanguages,
        String status,
        String tagline,
        String title,
        Boolean video,
        Double voteAverage,
        Integer voteCount,
        List<CreatedBy> createdBy,
        List<Integer> episodeRunTime,
        String firstAirDate,
        Boolean inProduction,
        List<String> languages,
        String lastAirDate,
        Episode lastEpisodeToAir,
        String name,
        Episode nextEpisodeToAir,
        List<Network> networks,
        Integer numberOfEpisodes,
        Integer numberOfSeasons,
        String originalName,
        List<Season> seasons,
        String type,
        ArrayList<String> alsoKnownAs,
        String biography,
        String birthday,
        String deathday,
        Integer gender,
        String knownForDepartment,
        String placeOfBirth,
        String profilePath,
        String mediaType
) {
    public static DetailsAPIResponse toTVDTO(TVDetails tvDetails) {
        return new DetailsAPIResponse(
                tvDetails.getAdult(),
                tvDetails.getBackdropPath(),
                null, // belongsToCollection (not in TVDetails)
                null, // budget (not in TVDetails)
                dev.angelzhang.mediaservice.entities.details.Genre.toDTO(tvDetails.getGenres()),
                tvDetails.getHomepage(),
                tvDetails.getId(),
                null, // imdbId (not in TVDetails)
                tvDetails.getOriginCountry() != null ?
                        CountryCode.toDTO(tvDetails.getOriginCountry()) :
                        null,
                tvDetails.getOriginalLanguage(),
                null, // originalTitle (not in TVDetails)
                tvDetails.getOverview(),
                tvDetails.getPopularity(),
                tvDetails.getPosterPath(),
                dev.angelzhang.mediaservice.entities.details.ProductionCompany.toDTO(tvDetails.getProductionCompanies()),
                dev.angelzhang.mediaservice.entities.details.ProductionCountry.toDTO(tvDetails.getProductionCountries()),
                null, // releaseDate (not in TVDetails)
                null, // revenue (not in TVDetails)
                null, // runtime (not in TVDetails)
                dev.angelzhang.mediaservice.entities.details.SpokenLanguage.toDTO(tvDetails.getSpokenLanguages()),
                tvDetails.getStatus(),
                tvDetails.getTagline(),
                null, // title (not in TVDetails)
                null, // video (not in TVDetails)
                tvDetails.getVoteAverage(),
                tvDetails.getVoteCount(),
                dev.angelzhang.mediaservice.entities.details.CreatedBy.toDTO(tvDetails.getCreatedBy()),
                tvDetails.getEpisodeRunTime(),
                tvDetails.getFirstAirDate(),
                tvDetails.getInProduction(),
                tvDetails.getLanguages(),
                tvDetails.getLastAirDate(),
                dev.angelzhang.mediaservice.entities.details.Episode.toDTO(tvDetails.getLastEpisodeToAir()),
                tvDetails.getName(),
                dev.angelzhang.mediaservice.entities.details.Episode.toDTO(tvDetails.getNextEpisodeToAir()),
                dev.angelzhang.mediaservice.entities.details.Network.toDTO(tvDetails.getNetworks()),
                tvDetails.getNumberOfEpisodes(),
                tvDetails.getNumberOfSeasons(),
                tvDetails.getOriginalName(),
                dev.angelzhang.mediaservice.entities.details.Season.toDTO(tvDetails.getSeasons()),
                tvDetails.getType(),
                null, // alsoKnownAs (not in TVDetails)
                null, // biography (not in TVDetails)
                null, // birthday (not in TVDetails)
                null, // deathday (not in TVDetails)
                null, // gender (not in TVDetails)
                null, // knownForDepartment (not in TVDetails)
                null, // placeOfBirth (not in TVDetails)
                null,  // profilePath (not in TVDetails)
                "tv"
        );
    }

    public static DetailsAPIResponse toMovieDTO(MovieDetails movieDetails) {
        return new DetailsAPIResponse(
                movieDetails.getAdult(),
                movieDetails.getBackdropPath(),
                dev.angelzhang.mediaservice.entities.details.BelongsToCollection.toDTO(movieDetails.getBelongsToCollection()),
                movieDetails.getBudget(),
                dev.angelzhang.mediaservice.entities.details.Genre.toDTO(movieDetails.getGenres()),
                movieDetails.getHomepage(),
                movieDetails.getId(),
                movieDetails.getImdbId(),
                null, // originCountry (not in MovieDetails)
                movieDetails.getOriginalLanguage(),
                movieDetails.getOriginalTitle(),
                movieDetails.getOverview(),
                movieDetails.getPopularity(),
                movieDetails.getPosterPath(),
                dev.angelzhang.mediaservice.entities.details.ProductionCompany.toDTO(movieDetails.getProductionCompanies()),
                dev.angelzhang.mediaservice.entities.details.ProductionCountry.toDTO(movieDetails.getProductionCountries()),
                movieDetails.getReleaseDate(),
                movieDetails.getRevenue(),
                movieDetails.getRuntime(),
                dev.angelzhang.mediaservice.entities.details.SpokenLanguage.toDTO(movieDetails.getSpokenLanguages()),
                movieDetails.getStatus(), // status (not in MovieDetails)
                movieDetails.getTagline(), // tagline (not in MovieDetails)
                movieDetails.getTitle(),
                movieDetails.getVideo(),
                movieDetails.getVoteAverage(),
                movieDetails.getVoteCount(),
                null, // createdBy (not in MovieDetails)
                null, // episodeRunTime (not in MovieDetails)
                null, // firstAirDate (not in MovieDetails)
                null, // inProduction (not in MovieDetails)
                null, // languages (not in MovieDetails)
                null, // lastAirDate (not in MovieDetails)
                null, // lastEpisodeToAir (not in MovieDetails)
                null, // name (not in MovieDetails)
                null, // nextEpisodeToAir (not in MovieDetails)
                null, // networks (not in MovieDetails)
                null, // numberOfEpisodes (not in MovieDetails)
                null, // numberOfSeasons (not in MovieDetails)
                null, // originalName (not in MovieDetails)
                null, // seasons (not in MovieDetails)
                null, // type (not in MovieDetails)
                null, // alsoKnownAs (not in MovieDetails)
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
