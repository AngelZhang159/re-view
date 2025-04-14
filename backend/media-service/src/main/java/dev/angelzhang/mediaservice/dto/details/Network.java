package dev.angelzhang.mediaservice.dto.details;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record Network(
        Integer id,
        String logoPath,
        String name,
        String originCountry
) {
}
