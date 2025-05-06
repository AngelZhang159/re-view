package dev.angelzhang.mediaservice.mapper;

import dev.angelzhang.mediaservice.entities.details.Genre;

import java.util.List;
import java.util.stream.Collectors;

public class GenreMapper {
    public static Genre toEntity(dev.angelzhang.mediaservice.dto.details.Genre genre) {
        return Genre.builder()
                .id(genre.id())
                .name(genre.name())
                .build();
    }

    public static List<Genre> toEntity(List<dev.angelzhang.mediaservice.dto.details.Genre> genres) {
        return genres.stream()
                .map(GenreMapper::toEntity)
                .collect(Collectors.toList());
    }

    public static List<dev.angelzhang.mediaservice.dto.details.Genre> toDTO(List<Genre> genres) {
        return genres.stream()
                .map(genre -> new dev.angelzhang.mediaservice.dto.details.Genre(genre.getId(), genre.getName()))
                .collect(Collectors.toList());
    }
}
