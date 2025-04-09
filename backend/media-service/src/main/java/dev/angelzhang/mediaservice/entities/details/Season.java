package dev.angelzhang.mediaservice.entities.details;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Season {

    @Id
    private Integer id;
    private String airDate;
    private Integer episodeCount;
    private String name;
    @Column(length = 1024)
    private String overview;
    private String posterPath;
    private Integer seasonNumber;
    private Double voteAverage;

    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    private TVDetails tvDetails;

    public static Season toEntity(dev.angelzhang.mediaservice.dto.details.Season season) {
        return Season.builder()
                .id(season.id())
                .airDate(season.airDate())
                .episodeCount(season.episodeCount())
                .name(season.name())
                .overview(season.overview())
                .posterPath(season.posterPath())
                .seasonNumber(season.seasonNumber())
                .voteAverage(season.voteAverage())
                .build();
    }

    public static List<Season> toEntity(List<dev.angelzhang.mediaservice.dto.details.Season> seasons) {
        return seasons.stream()
                .map(Season::toEntity)
                .collect(Collectors.toList());
    }

    public static List<dev.angelzhang.mediaservice.dto.details.Season> toDTO(List<Season> seasons) {
        return seasons.stream()
                .map(season -> new dev.angelzhang.mediaservice.dto.details.Season(season.getId(), season.getAirDate(), season.getEpisodeCount(), season.getName(), season.getOverview(), season.getPosterPath(), season.getSeasonNumber(), season.getVoteAverage()))
                .collect(Collectors.toList());
    }

}