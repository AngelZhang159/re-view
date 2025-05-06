package dev.angelzhang.mediaservice.mapper;

import dev.angelzhang.mediaservice.entities.details.CountryCode;
import dev.angelzhang.mediaservice.entities.details.ProductionCountry;

import java.util.List;
import java.util.stream.Collectors;

public class ProductionCountryMapper {
    public static ProductionCountry toEntity(dev.angelzhang.mediaservice.dto.details.ProductionCountry productionCountry) {
        return ProductionCountry.builder()
                .iso31661(productionCountry.iso31661().isBlank() ? null : CountryCode.valueOf(productionCountry.iso31661()))
                .name(productionCountry.name())
                .build();
    }

    public static List<ProductionCountry> toEntity(List<dev.angelzhang.mediaservice.dto.details.ProductionCountry> productionCountries) {
        return productionCountries.stream()
                .map(ProductionCountryMapper::toEntity)
                .collect(Collectors.toList());
    }

    public static List<dev.angelzhang.mediaservice.dto.details.ProductionCountry> toDTO(List<ProductionCountry> productionCountries) {
        return productionCountries.stream()
                .map(productionCountry -> new dev.angelzhang.mediaservice.dto.details.ProductionCountry(productionCountry.getIso31661().name(), productionCountry.getName()))
                .collect(Collectors.toList());
    }
}
