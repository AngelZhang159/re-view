package dev.angelzhang.mediaservice.entities.details;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class TVDetails extends MediaDetails {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CreatedBy> createdBy;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Integer> episodeRunTime;

    private String firstAirDate;
    private Boolean inProduction;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> languages;

    private String lastAirDate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Episode lastEpisodeToAir;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Episode nextEpisodeToAir;

    private String name;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    private List<Network> networks;

    private Integer numberOfEpisodes;
    private Integer numberOfSeasons;
    private String originalName;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    private List<Season> seasons;

    private String type;

}
