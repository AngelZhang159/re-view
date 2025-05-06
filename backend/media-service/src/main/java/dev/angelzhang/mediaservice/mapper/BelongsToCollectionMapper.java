package dev.angelzhang.mediaservice.mapper;

import dev.angelzhang.mediaservice.entities.details.BelongsToCollection;

public class BelongsToCollectionMapper {
    public static BelongsToCollection toEntity(dev.angelzhang.mediaservice.dto.details.BelongsToCollection belongsToCollection) {

        if (belongsToCollection == null) return null;

        return BelongsToCollection.builder()
                .id(belongsToCollection.id())
                .name(belongsToCollection.name())
                .posterPath(belongsToCollection.posterPath())
                .backdropPath(belongsToCollection.backdropPath())
                .build();
    }

    public static dev.angelzhang.mediaservice.dto.details.BelongsToCollection toDTO(BelongsToCollection belongsToCollection) {
        if (belongsToCollection == null) return null;
        return new dev.angelzhang.mediaservice.dto.details.BelongsToCollection(belongsToCollection.getId(), belongsToCollection.getName(), belongsToCollection.getPosterPath(), belongsToCollection.getBackdropPath());
    }
}
