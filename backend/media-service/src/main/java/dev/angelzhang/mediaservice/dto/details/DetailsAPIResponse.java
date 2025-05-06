package dev.angelzhang.mediaservice.dto.details;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
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
}
