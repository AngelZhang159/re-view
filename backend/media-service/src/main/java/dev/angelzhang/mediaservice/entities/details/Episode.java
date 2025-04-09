package dev.angelzhang.mediaservice.entities.details;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
public class Episode {

    @Id
    private Integer id;
    private String name;
    @Column(length = 1024)
    private String overview;
    private Double voteAverage;
    private Integer voteCount;
    private String airDate;
    private Integer episodeNumber;
    private String episodeType;
    private String productionCode;
    private Integer runtime;
    private Integer seasonNumber;
    private Integer showId;
    private String stillPath;

    public static Episode toEntity(dev.angelzhang.mediaservice.dto.details.Episode episode) {
        if (episode == null) {
            return null;
        }
        return Episode.builder()
                .id(episode.id())
                .name(episode.name())
                .overview(episode.overview())
                .voteAverage(episode.voteAverage())
                .voteCount(episode.voteCount())
                .airDate(episode.airDate())
                .episodeNumber(episode.episodeNumber())
                .episodeType(episode.episodeType())
                .productionCode(episode.productionCode())
                .runtime(episode.runtime())
                .seasonNumber(episode.seasonNumber())
                .showId(episode.showId())
                .stillPath(episode.stillPath())
                .build();
    }

    public static dev.angelzhang.mediaservice.dto.details.Episode toDTO(Episode episode) {
        if (episode == null) {
            return null;
        }
        return new dev.angelzhang.mediaservice.dto.details.Episode(episode.getId(), episode.getName(), episode.getOverview(), episode.getVoteAverage(), episode.getVoteCount(), episode.getAirDate(), episode.getEpisodeNumber(), episode.getEpisodeType(), episode.getProductionCode(), episode.getRuntime(), episode.getSeasonNumber(), episode.getShowId(), episode.getStillPath());
    }

}