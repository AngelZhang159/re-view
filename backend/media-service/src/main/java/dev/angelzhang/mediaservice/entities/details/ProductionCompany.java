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

    public static ProductionCompany toEntity(dev.angelzhang.mediaservice.dto.details.ProductionCompany productionCompany) {
        return ProductionCompany.builder()
                .id(productionCompany.id())
                .logoPath(productionCompany.logoPath())
                .name(productionCompany.name())
                .originCountry(productionCompany.originCountry() == null || productionCompany.originCountry().isBlank() ? null : CountryCode.valueOf(productionCompany.originCountry()))
                .build();
    }

    public static List<ProductionCompany> toEntity(List<dev.angelzhang.mediaservice.dto.details.ProductionCompany> productionCompanies) {
        return productionCompanies.stream()
                .map(ProductionCompany::toEntity)
                .collect(Collectors.toList());
    }

    public static List<dev.angelzhang.mediaservice.dto.details.ProductionCompany> toDTO(List<ProductionCompany> productionCompanies) {
        return productionCompanies.stream()
                .map(productionCompany -> new dev.angelzhang.mediaservice.dto.details.ProductionCompany(productionCompany.getId(), productionCompany.getLogoPath(), productionCompany.getName(), productionCompany.getOriginCountry() == null ? null : productionCompany.getOriginCountry().name()))
                .collect(Collectors.toList());
    }
}