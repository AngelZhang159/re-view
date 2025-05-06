package dev.angelzhang.mediaservice.mapper;

import dev.angelzhang.mediaservice.dto.details.DetailsAPIRequest;
import dev.angelzhang.mediaservice.entities.details.MovieDetails;
import dev.angelzhang.mediaservice.entities.details.TVDetails;

public class MediaMapper {
    public static TVDetails toTVDetailsEntity(DetailsAPIRequest request) {
        return TVDetails.builder()
                .adult(request.adult())
                .backdropPath(request.backdropPath())
                .genres(request.genres() != null ? GenreMapper.toEntity(request.genres()) : null)
                .homepage(request.homepage())
                .id(request.id())
                .originCountry(request.originCountry() != null ? CountryCodeMapper.toEntity(request.originCountry()) : null)
                .originalLanguage(request.originalLanguage())
                .overview(request.overview())
                .popularity(request.popularity())
                .posterPath(request.posterPath())
                .productionCompanies(request.productionCompanies() != null ? ProductionCompanyMapper.toEntity(request.productionCompanies()) : null)
                .productionCountries(request.productionCountries() != null ? ProductionCountryMapper.toEntity(request.productionCountries()) : null)
                .spokenLanguages(request.spokenLanguages() != null ? SpokenLanguageMapper.toEntity(request.spokenLanguages()) : null)
                .status(request.status())
                .tagline(request.tagline())
                .voteAverage(request.voteAverage())
                .voteCount(request.voteCount())
                .createdBy(request.createdBy() != null ? CreatedByMapper.toEntity(request.createdBy()) : null)
                .episodeRunTime(request.episodeRunTime())
                .firstAirDate(request.firstAirDate())
                .inProduction(request.inProduction())
                .languages(request.languages())
                .lastAirDate(request.lastAirDate())
                .lastEpisodeToAir(EpisodeMapper.toEntity(request.lastEpisodeToAir()))
                .nextEpisodeToAir(EpisodeMapper.toEntity(request.nextEpisodeToAir()))
                .name(request.name())
                .networks(request.networks() != null ? NetworkMapper.toEntity(request.networks()) : null)
                .numberOfEpisodes(request.numberOfEpisodes())
                .numberOfSeasons(request.numberOfSeasons())
                .originalName(request.originalName())
                .seasons(request.seasons() != null ? SeasonMapper.toEntity(request.seasons()) : null)
                .type(request.type())
                .build();
    }

    public static MovieDetails toMovieDetailsEntity(DetailsAPIRequest movie) {
        return MovieDetails.builder()
                .id(movie.id())
                .adult(movie.adult())
                .backdropPath(movie.backdropPath())
                .belongsToCollection(BelongsToCollectionMapper.toEntity(movie.belongsToCollection()))
                .budget(movie.budget())
                .genres(movie.genres() != null ? GenreMapper.toEntity(movie.genres()) : null)
                .homepage(movie.homepage())
                .originCountry(movie.originCountry() != null ? CountryCodeMapper.toEntity(movie.originCountry()) : null)
                .imdbId(movie.imdbId())
                .originalLanguage(movie.originalLanguage())
                .originalTitle(movie.originalTitle())
                .overview(movie.overview())
                .popularity(movie.popularity())
                .posterPath(movie.posterPath())
                .productionCompanies(movie.productionCompanies() != null ? ProductionCompanyMapper.toEntity(movie.productionCompanies()) : null)
                .productionCountries(movie.productionCountries() != null ? ProductionCountryMapper.toEntity(movie.productionCountries()) : null)
                .releaseDate(movie.releaseDate())
                .revenue(movie.revenue())
                .runtime(movie.runtime())
                .spokenLanguages(movie.spokenLanguages() != null ? SpokenLanguageMapper.toEntity(movie.spokenLanguages()) : null)
                .status(movie.status())
                .tagline(movie.tagline())
                .title(movie.title())
                .video(movie.video())
                .voteAverage(movie.voteAverage())
                .voteCount(movie.voteCount())
                .build();
    }
}
