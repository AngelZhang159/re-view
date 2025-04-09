package dev.angelzhang.mediaservice.dto.details;


public record BelongsToCollection(
        Integer id,
        String name,
        String posterPath,
        String backdropPath
) {
}
