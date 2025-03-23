package dev.angelzhang.mediaservice.dto.details;

import dev.angelzhang.mediaservice.entities.details.CountryCode;

public record ProductionCompany(
         Integer id,
         String logo_path,
         String name,
         CountryCode origin_country
) {
}
