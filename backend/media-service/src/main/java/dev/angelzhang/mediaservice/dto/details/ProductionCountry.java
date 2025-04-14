package dev.angelzhang.mediaservice.dto.details;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ProductionCountry(
        @JsonProperty("iso_3166_1")
        String iso31661,
        String name
) {
}
