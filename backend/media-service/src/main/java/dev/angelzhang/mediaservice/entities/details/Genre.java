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
@Getter
@Setter
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

    public static Genre toEntity(dev.angelzhang.mediaservice.dto.details.Genre genre) {
        return Genre.builder()
                .id(genre.id())
                .name(genre.name())
                .build();
    }

    public static List<Genre> toEntity(List<dev.angelzhang.mediaservice.dto.details.Genre> genres) {
        return genres.stream()
                .map(Genre::toEntity)
                .collect(Collectors.toList());
    }

    public static List<dev.angelzhang.mediaservice.dto.details.Genre> toDTO(List<Genre> genres) {
        return genres.stream()
                .map(genre -> new dev.angelzhang.mediaservice.dto.details.Genre(genre.getId(), genre.getName()))
                .collect(Collectors.toList());
    }
}

