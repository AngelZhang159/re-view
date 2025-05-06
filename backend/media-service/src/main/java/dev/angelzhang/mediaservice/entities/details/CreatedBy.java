package dev.angelzhang.mediaservice.entities.details;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

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


}