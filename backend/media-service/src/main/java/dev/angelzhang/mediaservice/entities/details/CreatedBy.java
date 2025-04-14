package dev.angelzhang.mediaservice.entities.details;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreatedBy {
    @Id
    private Integer id;
    private String creditId;
    private String name;
    private String originalName;
    private Integer gender;
    private String profilePath;

    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    private TVDetails tvDetails;

    public static List<CreatedBy> toEntity(List<dev.angelzhang.mediaservice.dto.details.CreatedBy> createdBy) {
        return createdBy.stream()
                .map(CreatedBy::toEntity)
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
                .map(createdBy1 -> new dev.angelzhang.mediaservice.dto.details.CreatedBy(createdBy1.getId(), createdBy1.getCreditId(), createdBy1.getName(), createdBy1.getOriginalName(), createdBy1.getGender(), createdBy1.profilePath))
                .collect(Collectors.toList());
    }
}