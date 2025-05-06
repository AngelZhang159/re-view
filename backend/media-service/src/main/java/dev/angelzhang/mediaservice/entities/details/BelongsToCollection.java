package dev.angelzhang.mediaservice.entities.details;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BelongsToCollection {
    @Id
    private Integer id;
    private String name;
    private String posterPath;
    private String backdropPath;

    @OneToMany
    @JsonIgnore
    @ToString.Exclude
    private List<MovieDetails> details;

}