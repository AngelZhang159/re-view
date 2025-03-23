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
public class ProductionCountry {

    @Id
    private CountryCode iso_3166_1;
    private String name;

    @ManyToMany(mappedBy = "production_countries")
    @JsonIgnore
    @ToString.Exclude
    private List<MovieDetails> details;

    @ManyToMany(mappedBy = "production_countries")
    @JsonIgnore
    @ToString.Exclude
    private List<TVDetails> tvDetails;

    public static ProductionCountry fromEntity(dev.angelzhang.mediaservice.dto.details.ProductionCountry productionCountry) {
        return ProductionCountry.builder()
                .iso_3166_1(productionCountry.iso_3166_1().isBlank() ? null : CountryCode.valueOf(productionCountry.iso_3166_1()))
                .name(productionCountry.name())
                .build();
    }

    public static List<ProductionCountry> fromRequest(List<dev.angelzhang.mediaservice.dto.details.ProductionCountry> productionCountries) {
        return productionCountries.stream()
                .map(ProductionCountry::fromEntity)
                .collect(Collectors.toList());
    }

    public static List<dev.angelzhang.mediaservice.dto.details.ProductionCountry> toResponse(List<ProductionCountry> productionCountries) {
        return productionCountries.stream()
                .map(productionCountry -> new dev.angelzhang.mediaservice.dto.details.ProductionCountry(productionCountry.getIso_3166_1().name(), productionCountry.getName()))
                .collect(Collectors.toList());
    }
}