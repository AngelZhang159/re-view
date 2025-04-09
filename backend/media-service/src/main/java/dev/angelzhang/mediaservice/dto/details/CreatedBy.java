package dev.angelzhang.mediaservice.dto.details;

public record CreatedBy(
         Integer id,
         String creditId,
         String name,
         String originalName,
         Integer gender,
         String profilePath
) {
}
