package dev.angelzhang.mediaservice.dto.searchMulti;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.ArrayList;


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
        ArrayList<Integer> genreIds,
        Double popularity,
        String releaseDate,
        Boolean video,
        Double voteAverage,
        Integer voteCount,
        String name,
        String originalName,
        String firstAirDate,
        ArrayList<String> originCountry
) {
}
