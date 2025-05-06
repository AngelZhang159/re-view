package dev.angelzhang.mediaservice.mapper;

import dev.angelzhang.mediaservice.entities.details.SpokenLanguage;

import java.util.List;
import java.util.stream.Collectors;

public class SpokenLanguageMapper {
    public static SpokenLanguage toEntity(dev.angelzhang.mediaservice.dto.details.SpokenLanguage spokenLanguage) {
        return SpokenLanguage.builder()
                .iso6391(spokenLanguage.iso6391())
                .englishName(spokenLanguage.englishName())
                .name(spokenLanguage.name())
                .build();
    }

    public static List<SpokenLanguage> toEntity(List<dev.angelzhang.mediaservice.dto.details.SpokenLanguage> spokenLanguages) {
        return spokenLanguages.stream()
                .map(SpokenLanguageMapper::toEntity)
                .collect(Collectors.toList());
    }

    public static List<dev.angelzhang.mediaservice.dto.details.SpokenLanguage> toDTO(List<SpokenLanguage> spokenLanguages) {
        return spokenLanguages.stream()
                .map(spokenLanguage -> new dev.angelzhang.mediaservice.dto.details.SpokenLanguage(spokenLanguage.getIso6391(), spokenLanguage.getEnglishName(), spokenLanguage.getName()))
                .collect(Collectors.toList());
    }
}
