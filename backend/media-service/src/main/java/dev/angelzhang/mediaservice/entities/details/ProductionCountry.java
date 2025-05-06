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
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductionCountry {

    @Id
    private CountryCode iso31661;
    private String name;

    @ManyToMany(mappedBy = "productionCountries")
    @JsonIgnore
    @ToString.Exclude
    private List<MovieDetails> details;

    @ManyToMany(mappedBy = "productionCountries")
    @JsonIgnore
    @ToString.Exclude
    private List<TVDetails> tvDetails;

}