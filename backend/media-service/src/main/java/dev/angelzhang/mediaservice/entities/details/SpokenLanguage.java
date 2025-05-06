package dev.angelzhang.mediaservice.entities.details;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.List;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SpokenLanguage {

    @Id
    private String iso6391;
    private String englishName;
    private String name;

    @ManyToMany(mappedBy = "spokenLanguages")
    @JsonIgnore
    @ToString.Exclude
    private List<MovieDetails> details;

}