package dev.angelzhang.mediaservice.DTO;

import java.util.ArrayList;

public record SearchMultiAPIBody(
        String backdrop_path,
        Integer id,
        String title,
        String original_title,
        String overview,
        String poster_path,
        String media_type,
        Boolean adult,
        String original_language,
        ArrayList<Integer> genre_ids,
        Double popularity,
        String release_date,
        Boolean video,
        Double vote_average,
        Integer vote_count,
        String name,
        String original_name,
        String first_air_date,
        ArrayList<String> origin_country
) {
}
