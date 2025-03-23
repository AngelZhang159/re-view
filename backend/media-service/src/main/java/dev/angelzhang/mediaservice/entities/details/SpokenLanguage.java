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
@Data
public class SpokenLanguage {

    @Id
    private String iso_639_1;
    private String english_name;
    private String name;

    @ManyToMany(mappedBy = "spoken_languages")
    @JsonIgnore
    @ToString.Exclude
    private List<MovieDetails> details;

    public static SpokenLanguage fromEntity(dev.angelzhang.mediaservice.dto.details.SpokenLanguage spokenLanguage) {
        return SpokenLanguage.builder()
                .iso_639_1(spokenLanguage.iso_639_1())
                .english_name(spokenLanguage.english_name())
                .name(spokenLanguage.name())
                .build();
    }

    public static List<SpokenLanguage> fromRequest(List<dev.angelzhang.mediaservice.dto.details.SpokenLanguage> spokenLanguages) {
        return spokenLanguages.stream()
                .map(SpokenLanguage::fromEntity)
                .collect(Collectors.toList());
    }

    public static List<dev.angelzhang.mediaservice.dto.details.SpokenLanguage> toResponse(List<SpokenLanguage> spokenLanguages) {
        return spokenLanguages.stream()
                .map(spokenLanguage -> new dev.angelzhang.mediaservice.dto.details.SpokenLanguage(spokenLanguage.getIso_639_1(), spokenLanguage.getEnglish_name(), spokenLanguage.getName()))
                .collect(Collectors.toList());
    }
}