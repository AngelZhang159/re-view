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
public class ProductionCompany {

    @Id
    private Integer id;
    private String logoPath;
    private String name;
    private CountryCode originCountry;

    @ManyToMany(mappedBy = "productionCompanies")
    @JsonIgnore
    @ToString.Exclude
    private List<MovieDetails> movieDetails;

    @ManyToMany(mappedBy = "productionCompanies")
    @JsonIgnore
    @ToString.Exclude
    private List<TVDetails> tvDetails;

}