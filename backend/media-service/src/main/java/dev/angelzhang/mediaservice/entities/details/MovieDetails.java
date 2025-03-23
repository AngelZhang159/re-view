package dev.angelzhang.mediaservice.entities.details;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class MovieDetails {

    @Id
    private Integer id;
    private Boolean adult;
    private String backdrop_path;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private BelongsToCollection belongs_to_collection;
    private Integer budget;
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private List<Genre> genres;
    private String homepage;
    private String imdb_id;
    @ElementCollection(targetClass = CountryCode.class,fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<CountryCode> origin_country;
    private String original_language;
    private String original_title;
    @Column(length = 1024)
    private String overview;
    private Double popularity;
    private String poster_path;
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private List<ProductionCompany> production_companies;
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private List<ProductionCountry> production_countries;
    private String release_date;
    private Double revenue;
    private Integer runtime;
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private List<SpokenLanguage> spoken_languages;
    private String status;
    private String tagline;
    private String title;
    private Boolean video;
    private Double vote_average;
    private Integer vote_count;

    public static MovieDetails fromRequest(DetailsAPIRequest movie) {

        log.info("Building movie details from request: {}", movie);

        MovieDetails build = MovieDetails.builder()
                .id(movie.id())
                .adult(movie.adult())
                .backdrop_path(movie.backdrop_path())
                .belongs_to_collection(BelongsToCollection.fromRequest(movie.belongs_to_collection()))
                .budget(movie.budget())
                .genres(Genre.fromRequest(movie.genres()))
                .homepage(movie.homepage())
                .imdb_id(movie.imdb_id())
                .origin_country(CountryCode.fromRequest(movie.origin_country()))
                .original_language(movie.original_language())
                .original_title(movie.original_title())
                .overview(movie.overview())
                .popularity(movie.popularity())
                .poster_path(movie.poster_path())
                .production_companies(ProductionCompany.fromRequest(movie.production_companies()))
                .production_countries(ProductionCountry.fromRequest(movie.production_countries()))
                .release_date(movie.release_date())
                .revenue(movie.revenue())
                .runtime(movie.runtime())
                .spoken_languages(SpokenLanguage.fromRequest(movie.spoken_languages()))
                .status(movie.status())
                .tagline(movie.tagline())
                .title(movie.title())
                .video(movie.video())
                .vote_average(movie.vote_average())
                .vote_count(movie.vote_count())
                .build();

        log.info("Resulting movie details: {}", build);

        return build;
    }
}