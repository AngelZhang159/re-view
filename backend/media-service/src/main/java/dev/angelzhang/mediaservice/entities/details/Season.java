package dev.angelzhang.mediaservice.entities.details;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Season {

    @Id
    private Integer id;
    private String air_date;
    private Integer episode_count;
    private String name;
    @Column(length = 1024)
    private String overview;
    private String poster_path;
    private Integer season_number;
    private Double vote_average;

    public static Season fromRequest(dev.angelzhang.mediaservice.dto.details.Season season) {
        return Season.builder()
                .id(season.id())
                .air_date(season.air_date())
                .episode_count(season.episode_count())
                .name(season.name())
                .overview(season.overview())
                .poster_path(season.poster_path())
                .season_number(season.season_number())
                .vote_average(season.vote_average())
                .build();
    }

    public static List<Season> fromRequest(List<dev.angelzhang.mediaservice.dto.details.Season> seasons) {
        return seasons.stream()
                .map(Season::fromRequest)
                .collect(Collectors.toList());
    }

}