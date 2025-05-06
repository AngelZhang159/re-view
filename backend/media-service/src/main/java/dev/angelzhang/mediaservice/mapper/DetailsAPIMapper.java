package dev.angelzhang.mediaservice.mapper;

import dev.angelzhang.mediaservice.dto.details.DetailsAPIResponse;
import dev.angelzhang.mediaservice.entities.details.MovieDetails;
import dev.angelzhang.mediaservice.entities.details.TVDetails;

public class DetailsAPIMapper {
    public static DetailsAPIResponse toTVDTO(TVDetails tvDetails) {
        return new DetailsAPIResponse(
                tvDetails.getAdult(),
                tvDetails.getBackdropPath(),
                null, // belongsToCollection (not in TVDetails)
                null, // budget (not in TVDetails)
                GenreMapper.toDTO(tvDetails.getGenres()),
                tvDetails.getHomepage(),
                tvDetails.getId(),
                null, // imdbId (not in TVDetails)
                tvDetails.getOriginCountry() != null ?
                        CountryCodeMapper.toDTO(tvDetails.getOriginCountry()) :
                        null,
                tvDetails.getOriginalLanguage(),
                null, // originalTitle (not in TVDetails)
                tvDetails.getOverview(),
                tvDetails.getPopularity(),
                tvDetails.getPosterPath(),
                ProductionCompanyMapper.toDTO(tvDetails.getProductionCompanies()),
                ProductionCountryMapper.toDTO(tvDetails.getProductionCountries()),
                null, // releaseDate (not in TVDetails)
                null, // revenue (not in TVDetails)
                null, // runtime (not in TVDetails)
                SpokenLanguageMapper.toDTO(tvDetails.getSpokenLanguages()),
                tvDetails.getStatus(),
                tvDetails.getTagline(),
                null, // title (not in TVDetails)
                null, // video (not in TVDetails)
                tvDetails.getVoteAverage(),
                tvDetails.getVoteCount(),
                CreatedByMapper.toDTO(tvDetails.getCreatedBy()),
                tvDetails.getEpisodeRunTime(),
                tvDetails.getFirstAirDate(),
                tvDetails.getInProduction(),
                tvDetails.getLanguages(),
                tvDetails.getLastAirDate(),
                EpisodeMapper.toDTO(tvDetails.getLastEpisodeToAir()),
                tvDetails.getName(),
                EpisodeMapper.toDTO(tvDetails.getNextEpisodeToAir()),
                NetworkMapper.toDTO(tvDetails.getNetworks()),
                tvDetails.getNumberOfEpisodes(),
                tvDetails.getNumberOfSeasons(),
                tvDetails.getOriginalName(),
                SeasonMapper.toDTO(tvDetails.getSeasons()),
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
                BelongsToCollectionMapper.toDTO(movieDetails.getBelongsToCollection()),
                movieDetails.getBudget(),
                GenreMapper.toDTO(movieDetails.getGenres()),
                movieDetails.getHomepage(),
                movieDetails.getId(),
                movieDetails.getImdbId(),
                movieDetails.getOriginCountry() != null ? CountryCodeMapper.toDTO(movieDetails.getOriginCountry()) :null,
                movieDetails.getOriginalLanguage(),
                movieDetails.getOriginalTitle(),
                movieDetails.getOverview(),
                movieDetails.getPopularity(),
                movieDetails.getPosterPath(),
                ProductionCompanyMapper.toDTO(movieDetails.getProductionCompanies()),
                ProductionCountryMapper.toDTO(movieDetails.getProductionCountries()),
                movieDetails.getReleaseDate(),
                movieDetails.getRevenue(),
                movieDetails.getRuntime(),
                SpokenLanguageMapper.toDTO(movieDetails.getSpokenLanguages()),
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
