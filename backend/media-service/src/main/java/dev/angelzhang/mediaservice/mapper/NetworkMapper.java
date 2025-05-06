package dev.angelzhang.mediaservice.mapper;

import dev.angelzhang.mediaservice.entities.details.Network;

import java.util.List;
import java.util.stream.Collectors;

public class NetworkMapper {
    public static Network toEntity(dev.angelzhang.mediaservice.dto.details.Network network) {
        return Network.builder()
                .id(network.id())
                .logoPath(network.logoPath())
                .name(network.name())
                .originCountry(network.originCountry())
                .build();
    }

    public static List<Network> toEntity(List<dev.angelzhang.mediaservice.dto.details.Network> networks) {
        return networks.stream()
                .map(NetworkMapper::toEntity)
                .collect(Collectors.toList());
    }

    public static List<dev.angelzhang.mediaservice.dto.details.Network> toDTO(List<Network> networks) {
        return networks.stream()
                .map(network -> new dev.angelzhang.mediaservice.dto.details.Network(network.getId(), network.getLogoPath(), network.getName(), network.getOriginCountry()))
                .collect(Collectors.toList());
    }
}
