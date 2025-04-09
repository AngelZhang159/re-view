package dev.angelzhang.mediaservice.dto.details;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SpokenLanguage(
        String englishName,
        @JsonProperty("iso_639_1")
        String iso6391,
        String name
) {
}
