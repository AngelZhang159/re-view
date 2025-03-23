package dev.angelzhang.mediaservice.entities.details;

import dev.angelzhang.mediaservice.dto.details.DetailsAPIRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class TVDetails {

    private Boolean adult;
    private String backdrop_path;

    @OneToMany(mappedBy = "tvDetails", cascade = CascadeType.ALL)
    private List<CreatedBy> created_by;

    @ElementCollection
    private List<Integer> episode_run_time;

    private String first_air_date;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Genre> genres;

    private String homepage;

    @Id
    private Integer id;

    private Boolean in_production;

    @ElementCollection
    private List<String> languages;

    private String last_air_date;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Episode last_episode_to_air;

    private String name;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Episode next_episode_to_air;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Network> networks;

    private Integer number_of_episodes;
    private Integer number_of_seasons;

    @ElementCollection
    private List<CountryCode> origin_country;

    private String original_language;
    private String original_name;
    @Column(length = 1024)
    private String overview;

    private Double popularity;
    private String poster_path;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<ProductionCompany> production_companies;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<ProductionCountry> production_countries;

    @OneToMany(mappedBy = "tvDetails", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Season> seasons;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<SpokenLanguage> spoken_languages;

    private String status;
    private String tagline;
    private String type;

    private Double vote_average;
    private Integer vote_count;

    public static TVDetails fromRequest(DetailsAPIRequest request) {

        log.info("Building TV details from request: Network:{}\n, Season: {}\n", request.networks(), request.seasons());

        TVDetails build = TVDetails.builder()
                .adult(request.adult())
                .backdrop_path(request.backdrop_path())
                .created_by(CreatedBy.fromRequest(request.created_by()))
                .episode_run_time(request.episode_run_time())
                .first_air_date(request.first_air_date())
                .genres(Genre.fromRequest(request.genres()))
                .homepage(request.homepage())
                .id(request.id())
                .in_production(request.in_production())
                .languages(request.languages())
                .last_air_date(request.last_air_date())
                .last_episode_to_air(Episode.fromRequest(request.last_episode_to_air()))
                .name(request.name())
                .next_episode_to_air(Episode.fromRequest(request.next_episode_to_air()))
                .networks(Network.fromRequest(request.networks()))
                .number_of_episodes(request.number_of_episodes())
                .number_of_seasons(request.number_of_seasons())
                .origin_country(request.origin_country())
                .original_language(request.original_language())
                .original_name(request.original_name())
                .overview(request.overview())
                .popularity(request.popularity())
                .poster_path(request.poster_path())
                .production_companies(ProductionCompany.fromRequest(request.production_companies()))
                .production_countries(ProductionCountry.fromRequest(request.production_countries()))
                .seasons(Season.fromRequest(request.seasons()))
                .spoken_languages(SpokenLanguage.fromRequest(request.spoken_languages()))
                .status(request.status())
                .tagline(request.tagline())
                .type(request.type())
                .vote_average(request.vote_average())
                .vote_count(request.vote_count())
                .build();

        log.info("Resulting TV details: Network:{}\n, Season: {}\n", build.getNetworks(), build.getSeasons());
        return build;
    }
}
