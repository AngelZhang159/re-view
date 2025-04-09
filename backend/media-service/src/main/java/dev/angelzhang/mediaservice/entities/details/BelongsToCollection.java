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
@Data
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

    public static BelongsToCollection toEntity(dev.angelzhang.mediaservice.dto.details.BelongsToCollection belongsToCollection) {

        if (belongsToCollection == null) {
            return null;
        }

        return BelongsToCollection.builder()
                .id(belongsToCollection.id())
                .name(belongsToCollection.name())
                .posterPath(belongsToCollection.posterPath())
                .backdropPath(belongsToCollection.backdropPath())
                .build();
    }

    public static dev.angelzhang.mediaservice.dto.details.BelongsToCollection toDTO(BelongsToCollection belongsToCollection) {
        if (belongsToCollection == null ) {
            return null;
        }
        return new dev.angelzhang.mediaservice.dto.details.BelongsToCollection(belongsToCollection.getId(), belongsToCollection.getName(), belongsToCollection.getPosterPath(), belongsToCollection.getBackdropPath());
    }
}