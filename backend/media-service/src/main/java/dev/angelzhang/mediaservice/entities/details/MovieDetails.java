package dev.angelzhang.mediaservice.entities.details;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.angelzhang.mediaservice.dto.details.DetailsAPIRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class MovieDetails extends MediaDetails {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private BelongsToCollection belongsToCollection;

    private Integer budget;
    private String imdbId;
    private String originalTitle;
    private String releaseDate;
    private Double revenue;
    private Integer runtime;
    private String title;
    private Boolean video;

    public static MovieDetails toEntity(DetailsAPIRequest movie) {

        log.info("Building movie details from request: {}", movie);

        MovieDetails build = MovieDetails.builder()
                .id(movie.id())
                .adult(movie.adult())
                .backdropPath(movie.backdropPath())
                .belongsToCollection(BelongsToCollection.toEntity(movie.belongsToCollection()))
                .budget(movie.budget())
                .genres(movie.genres() != null ? Genre.toEntity(movie.genres()) : null)
                .homepage(movie.homepage())
                .originCountry(movie.originCountry() != null ? CountryCode.toEntity(movie.originCountry()) : null)
                .imdbId(movie.imdbId())
                .originalLanguage(movie.originalLanguage())
                .originalTitle(movie.originalTitle())
                .overview(movie.overview())
                .popularity(movie.popularity())
                .posterPath(movie.posterPath())
                .productionCompanies(movie.productionCompanies() != null ? ProductionCompany.toEntity(movie.productionCompanies()) : null)
                .productionCountries(movie.productionCountries() != null ? ProductionCountry.toEntity(movie.productionCountries()) : null)
                .releaseDate(movie.releaseDate())
                .revenue(movie.revenue())
                .runtime(movie.runtime())
                .spokenLanguages(movie.spokenLanguages() != null ? SpokenLanguage.toEntity(movie.spokenLanguages()) : null)
                .status(movie.status())
                .tagline(movie.tagline())
                .title(movie.title())
                .video(movie.video())
                .voteAverage(movie.voteAverage())
                .voteCount(movie.voteCount())
                .build();


        log.info("Resulting movie details: {}", build);

        return build;
    }
}