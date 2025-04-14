package dev.angelzhang.mediaservice.entities.details;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SpokenLanguage {

    @Id
    private String iso6391;
    private String englishName;
    private String name;

    @ManyToMany(mappedBy = "spokenLanguages")
    @JsonIgnore
    @ToString.Exclude
    private List<MovieDetails> details;

    public static SpokenLanguage toEntity(dev.angelzhang.mediaservice.dto.details.SpokenLanguage spokenLanguage) {
        return SpokenLanguage.builder()
                .iso6391(spokenLanguage.iso6391())
                .englishName(spokenLanguage.englishName())
                .name(spokenLanguage.name())
                .build();
    }

    public static List<SpokenLanguage> toEntity(List<dev.angelzhang.mediaservice.dto.details.SpokenLanguage> spokenLanguages) {
        return spokenLanguages.stream()
                .map(SpokenLanguage::toEntity)
                .collect(Collectors.toList());
    }

    public static List<dev.angelzhang.mediaservice.dto.details.SpokenLanguage> toDTO(List<SpokenLanguage> spokenLanguages) {
        return spokenLanguages.stream()
                .map(spokenLanguage -> new dev.angelzhang.mediaservice.dto.details.SpokenLanguage(spokenLanguage.getIso6391(), spokenLanguage.getEnglishName(), spokenLanguage.getName()))
                .collect(Collectors.toList());
    }
}