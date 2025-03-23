package dev.angelzhang.mediaservice.dto.details;

import dev.angelzhang.mediaservice.entities.details.CountryCode;

public record ProductionCountry(
         CountryCode iso_3166_1,
         String name
) {
}
