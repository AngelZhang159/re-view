package dev.angelzhang.mediaservice.mapper;

import dev.angelzhang.mediaservice.entities.details.CountryCode;

import java.util.List;
import java.util.stream.Collectors;

public class CountryCodeMapper {
    public static List<CountryCode> toEntity(List<String> strings) {
        return strings.stream()
                .map(CountryCode::valueOf)
                .collect(Collectors.toList());
    }

    public static List<String> toDTO(List<CountryCode> countryCodes) {
        return countryCodes.stream()
                .map(Enum::toString)
                .collect(Collectors.toList());
    }
}
