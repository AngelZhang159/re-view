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
@Data
public class Network {

    @Id
    private Integer id;
    private String logo_path;
    private String name;
    private String origin_country;

    @ManyToMany(mappedBy = "networks")
    @JsonIgnore
    @ToString.Exclude
    private List<TVDetails> tvDetails;

    public static Network fromRequest(dev.angelzhang.mediaservice.dto.details.Network network) {
        return Network.builder()
                .id(network.id())
                .logo_path(network.logo_path())
                .name(network.name())
                .origin_country(network.origin_country())
                .build();
    }

    public static List<Network> fromRequest(List<dev.angelzhang.mediaservice.dto.details.Network> networks) {
        return networks.stream()
                .map(Network::fromRequest)
                .collect(Collectors.toList());
    }
}