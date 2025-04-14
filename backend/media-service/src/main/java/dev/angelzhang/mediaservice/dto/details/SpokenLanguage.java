package dev.angelzhang.mediaservice.dto.details;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record SpokenLanguage(
        String englishName,
        @JsonProperty("iso_639_1")
        String iso6391,
        String name
) {
}
