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
@Data
public class CreatedBy {
    @Id
    private Integer id;
    private String credit_id;
    private String name;
    private String original_name;
    private Integer gender;
    private String profile_path;

    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    private TVDetails tvDetails;

    public static List<CreatedBy> fromRequest(List<dev.angelzhang.mediaservice.dto.details.CreatedBy> createdBy) {
        return createdBy.stream()
                .map(CreatedBy::fromEntity)
                .collect(Collectors.toList());
    }

    public static CreatedBy fromEntity(dev.angelzhang.mediaservice.dto.details.CreatedBy createdBy) {
        return CreatedBy.builder()
                .id(createdBy.id())
                .credit_id(createdBy.credit_id())
                .name(createdBy.name())
                .original_name(createdBy.original_name())
                .build();
    }

    public static List<dev.angelzhang.mediaservice.dto.details.CreatedBy> toResponse(List<CreatedBy> createdBy) {
        return createdBy.stream()
                .map(createdBy1 -> new dev.angelzhang.mediaservice.dto.details.CreatedBy(createdBy1.getId(), createdBy1.getCredit_id(), createdBy1.getName(), createdBy1.getOriginal_name(), createdBy1.getGender(), createdBy1.profile_path))
                .collect(Collectors.toList());
    }
}