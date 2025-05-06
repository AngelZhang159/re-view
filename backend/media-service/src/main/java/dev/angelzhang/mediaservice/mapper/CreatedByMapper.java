package dev.angelzhang.mediaservice.mapper;

import dev.angelzhang.mediaservice.entities.details.CreatedBy;

import java.util.List;
import java.util.stream.Collectors;

public class CreatedByMapper {
    public static List<CreatedBy> toEntity(List<dev.angelzhang.mediaservice.dto.details.CreatedBy> createdBy) {
        return createdBy.stream()
                .map(CreatedByMapper::toEntity)
                .collect(Collectors.toList());
    }

    public static CreatedBy toEntity(dev.angelzhang.mediaservice.dto.details.CreatedBy createdBy) {
        return CreatedBy.builder()
                .id(createdBy.id())
                .creditId(createdBy.creditId())
                .name(createdBy.name())
                .originalName(createdBy.originalName())
                .build();
    }

    public static List<dev.angelzhang.mediaservice.dto.details.CreatedBy> toDTO(List<CreatedBy> createdBy) {
        return createdBy.stream()
                .map(createdBy1 -> new dev.angelzhang.mediaservice.dto.details.CreatedBy(createdBy1.getId(), createdBy1.getCreditId(), createdBy1.getName(), createdBy1.getOriginalName(), createdBy1.getGender(), createdBy1.getProfilePath()))
                .collect(Collectors.toList());
    }
}
