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
public class ProductionCompany {

    @Id
    private Integer id;
    private String logo_path;
    private String name;
    private CountryCode origin_country;

    @ManyToMany(mappedBy = "production_companies")
    @JsonIgnore
    @ToString.Exclude
    private List<MovieDetails> movieDetails;

    @ManyToMany(mappedBy = "production_companies")
    @JsonIgnore
    @ToString.Exclude
    private List<TVDetails> tvDetails;

    public static ProductionCompany fromEntity(dev.angelzhang.mediaservice.dto.details.ProductionCompany productionCompany) {
        return ProductionCompany.builder()
                .id(productionCompany.id())
                .logo_path(productionCompany.logo_path())
                .name(productionCompany.name())
                .origin_country(productionCompany.origin_country())
                .build();
    }

    public static List<ProductionCompany> fromRequest(List<dev.angelzhang.mediaservice.dto.details.ProductionCompany> productionCompanies) {
        return productionCompanies.stream()
                .map(ProductionCompany::fromEntity)
                .collect(Collectors.toList());
    }
}