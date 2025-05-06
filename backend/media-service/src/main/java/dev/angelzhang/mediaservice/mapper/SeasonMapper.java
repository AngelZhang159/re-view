package dev.angelzhang.mediaservice.mapper;

import dev.angelzhang.mediaservice.entities.details.Season;

import java.util.List;
import java.util.stream.Collectors;

public class SeasonMapper {
    public static Season toEntity(dev.angelzhang.mediaservice.dto.details.Season season) {
        return Season.builder()
                .id(season.id())
                .airDate(season.airDate())
                .episodeCount(season.episodeCount())
                .name(season.name())
                .overview(season.overview())
                .posterPath(season.posterPath())
                .seasonNumber(season.seasonNumber())
                .voteAverage(season.voteAverage())
                .build();
    }

    public static List<Season> toEntity(List<dev.angelzhang.mediaservice.dto.details.Season> seasons) {
        return seasons.stream()
                .map(SeasonMapper::toEntity)
                .collect(Collectors.toList());
    }

    public static List<dev.angelzhang.mediaservice.dto.details.Season> toDTO(List<Season> seasons) {
        return seasons.stream()
                .map(season -> new dev.angelzhang.mediaservice.dto.details.Season(season.getId(), season.getAirDate(), season.getEpisodeCount(), season.getName(), season.getOverview(), season.getPosterPath(), season.getSeasonNumber(), season.getVoteAverage()))
                .collect(Collectors.toList());
    }
}
