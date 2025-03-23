package dev.angelzhang.mediaservice.dto.details;


public record BelongsToCollection(
        Integer id,
        String name,
        String poster_path,
        String backdrop_path
) {
}
