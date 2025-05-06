package dev.angelzhang.mediaservice.mapper;

import dev.angelzhang.mediaservice.entities.details.CountryCode;
import dev.angelzhang.mediaservice.entities.details.ProductionCompany;

import java.util.List;
import java.util.stream.Collectors;

public class ProductionCompanyMapper {
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
                .map(ProductionCompanyMapper::toEntity)
                .collect(Collectors.toList());
    }

    public static List<dev.angelzhang.mediaservice.dto.details.ProductionCompany> toDTO(List<ProductionCompany> productionCompanies) {
        return productionCompanies.stream()
                .map(productionCompany -> new dev.angelzhang.mediaservice.dto.details.ProductionCompany(productionCompany.getId(), productionCompany.getLogoPath(), productionCompany.getName(), productionCompany.getOriginCountry() == null ? null : productionCompany.getOriginCountry().name()))
                .collect(Collectors.toList());
    }
}
