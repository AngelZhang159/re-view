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
    private Double vote_average;
    private Integer vote_count;
    private String air_date;
    private Integer episode_number;
    private String episode_type;
    private String production_code;
    private Integer runtime;
    private Integer season_number;
    private Integer show_id;
    private String still_path;

    public static Episode fromRequest(dev.angelzhang.mediaservice.dto.details.Episode episode) {
        if (episode == null) {
            return null;
        }
        return Episode.builder()
                .id(episode.id())
                .name(episode.name())
                .overview(episode.overview())
                .vote_average(episode.vote_average())
                .vote_count(episode.vote_count())
                .air_date(episode.air_date())
                .episode_number(episode.episode_number())
                .episode_type(episode.episode_type())
                .production_code(episode.production_code())
                .runtime(episode.runtime())
                .season_number(episode.season_number())
                .show_id(episode.show_id())
                .still_path(episode.still_path())
                .build();
    }

    public static dev.angelzhang.mediaservice.dto.details.Episode toResponse(Episode episode) {
        if (episode == null) {
            return null;
        }
        return new dev.angelzhang.mediaservice.dto.details.Episode(episode.getId(), episode.getName(), episode.getOverview(), episode.getVote_average(), episode.getVote_count(), episode.getAir_date(), episode.getEpisode_number(), episode.getEpisode_type(), episode.getProduction_code(), episode.getRuntime(), episode.getSeason_number(), episode.getShow_id(), episode.getStill_path());
    }

}