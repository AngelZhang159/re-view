package dev.angelzhang.mediaservice.entities.details;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Network {

    @Id
    private Integer id;
    private String logoPath;
    private String name;
    private String originCountry;

    @ManyToMany(mappedBy = "networks")
    @JsonIgnore
    @ToString.Exclude
    private List<TVDetails> tvDetails;

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
                .map(Network::toEntity)
                .collect(Collectors.toList());
    }

    public static List<dev.angelzhang.mediaservice.dto.details.Network> toDTO(List<Network> networks) {
        return networks.stream()
                .map(network -> new dev.angelzhang.mediaservice.dto.details.Network(network.getId(), network.getLogoPath(), network.getName(), network.getOriginCountry()))
                .collect(Collectors.toList());
    }
}