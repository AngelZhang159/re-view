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
    private String poster_path;
    private String backdrop_path;

    @OneToMany
    @JsonIgnore
    @ToString.Exclude
    private List<MovieDetails> details;

    public static BelongsToCollection fromRequest(dev.angelzhang.mediaservice.dto.details.BelongsToCollection belongsToCollection) {

        if (belongsToCollection == null) {
            return null;
        }

        return BelongsToCollection.builder()
                .id(belongsToCollection.id())
                .name(belongsToCollection.name())
                .poster_path(belongsToCollection.poster_path())
                .backdrop_path(belongsToCollection.backdrop_path())
                .build();
    }

    public static dev.angelzhang.mediaservice.dto.details.BelongsToCollection toResponse(BelongsToCollection belongsToCollection) {
        if (belongsToCollection == null ) {
            return null;
        }
        return new dev.angelzhang.mediaservice.dto.details.BelongsToCollection(belongsToCollection.getId(), belongsToCollection.getName(), belongsToCollection.getPoster_path(), belongsToCollection.getBackdrop_path());
    }
}