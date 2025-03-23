package dev.angelzhang.mediaservice.entities.details;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}