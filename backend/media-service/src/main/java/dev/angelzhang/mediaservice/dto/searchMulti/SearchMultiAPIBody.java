package dev.angelzhang.mediaservice.dto.searchMulti;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;


@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record SearchMultiAPIBody(
        String backdropPath,
        Integer id,
        String title,
        String originalTitle,
        String overview,
        String posterPath,
        String mediaType,
        Boolean adult,
        String originalLanguage,
        List<Integer> genreIds,
        Double popularity,
        String releaseDate,
        Boolean video,
        Double voteAverage,
        Integer voteCount,
        String name,
        String originalName,
        String firstAirDate,
        List<String> originCountry
) {
}
