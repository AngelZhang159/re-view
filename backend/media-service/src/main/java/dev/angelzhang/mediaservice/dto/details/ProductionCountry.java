package dev.angelzhang.mediaservice.dto.details;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductionCountry(
        @JsonProperty("iso_3166_1")
        String iso31661,
        String name
) {
}
