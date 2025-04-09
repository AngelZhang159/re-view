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

    public static ProductionCountry toEntity(dev.angelzhang.mediaservice.dto.details.ProductionCountry productionCountry) {
        return ProductionCountry.builder()
                .iso31661(productionCountry.iso31661().isBlank() ? null : CountryCode.valueOf(productionCountry.iso31661()))
                .name(productionCountry.name())
                .build();
    }

    public static List<ProductionCountry> toEntity(List<dev.angelzhang.mediaservice.dto.details.ProductionCountry> productionCountries) {
        return productionCountries.stream()
                .map(ProductionCountry::toEntity)
                .collect(Collectors.toList());
    }

    public static List<dev.angelzhang.mediaservice.dto.details.ProductionCountry> toDTO(List<ProductionCountry> productionCountries) {
        return productionCountries.stream()
                .map(productionCountry -> new dev.angelzhang.mediaservice.dto.details.ProductionCountry(productionCountry.getIso31661().name(), productionCountry.getName()))
                .collect(Collectors.toList());
    }
}