package dev.angelzhang.mediaservice.mapper;

import dev.angelzhang.mediaservice.entities.details.Episode;

public class EpisodeMapper {
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
