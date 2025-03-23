package dev.angelzhang.mediaservice.entities.details;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Genre {

    @Id
    private Integer id;
    private String name;

    @ManyToMany(mappedBy = "genres")
    @JsonIgnore
    @ToString.Exclude
    private List<MovieDetails> details;

    @ManyToMany(mappedBy = "genres")
    @JsonIgnore
    @ToString.Exclude
    private List<TVDetails> tvDetails;

    public static Genre fromEntity(dev.angelzhang.mediaservice.dto.details.Genre genre) {
        return Genre.builder()
                .id(genre.id())
                .name(genre.name())
                .build();
    }

    public static List<Genre> fromRequest(List<dev.angelzhang.mediaservice.dto.details.Genre> genres) {
        return genres.stream()
                .map(Genre::fromEntity)
                .collect(Collectors.toList());
    }
}

