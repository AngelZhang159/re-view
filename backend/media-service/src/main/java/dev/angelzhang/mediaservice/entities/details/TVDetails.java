package dev.angelzhang.mediaservice.entities.details;

import dev.angelzhang.mediaservice.dto.details.DetailsAPIRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
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

    public static TVDetails toEntity(DetailsAPIRequest request) {

        log.info("Building TV details from request: Network:{}\n, Season: {}\n", request.networks(), request.seasons());

        TVDetails build = TVDetails.builder()
                .adult(request.adult())
                .backdropPath(request.backdropPath())
                .genres(request.genres() != null ? Genre.toEntity(request.genres()) : null)
                .homepage(request.homepage())
                .id(request.id())
                .originCountry(request.originCountry() != null ? CountryCode.toEntity(request.originCountry()) : null)
                .originalLanguage(request.originalLanguage())
                .overview(request.overview())
                .popularity(request.popularity())
                .posterPath(request.posterPath())
                .productionCompanies(request.productionCompanies() != null ? ProductionCompany.toEntity(request.productionCompanies()) : null)
                .productionCountries(request.productionCountries() != null ? ProductionCountry.toEntity(request.productionCountries()) : null)
                .spokenLanguages(request.spokenLanguages() != null ? SpokenLanguage.toEntity(request.spokenLanguages()) : null)
                .status(request.status())
                .tagline(request.tagline())
                .voteAverage(request.voteAverage())
                .voteCount(request.voteCount())
                .createdBy(request.createdBy() != null ? CreatedBy.toEntity(request.createdBy()) : null)
                .episodeRunTime(request.episodeRunTime())
                .firstAirDate(request.firstAirDate())
                .inProduction(request.inProduction())
                .languages(request.languages())
                .lastAirDate(request.lastAirDate())
                .lastEpisodeToAir(Episode.toEntity(request.lastEpisodeToAir()))
                .nextEpisodeToAir(Episode.toEntity(request.nextEpisodeToAir()))
                .name(request.name())
                .networks(request.networks() != null ? Network.toEntity(request.networks()) : null)
                .numberOfEpisodes(request.numberOfEpisodes())
                .numberOfSeasons(request.numberOfSeasons())
                .originalName(request.originalName())
                .seasons(request.seasons() != null ? Season.toEntity(request.seasons()) : null)
                .type(request.type())
                .build();

        log.info("Resulting TV details: Network:{}\n, Season: {}\n", build.getNetworks(), build.getSeasons());
        return build;
    }
}
