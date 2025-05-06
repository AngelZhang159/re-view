package dev.angelzhang.mediaservice.entities.details;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Episode {

    @Id
    private Integer id;
    private String name;
    @Column(length = 1024)
    private String overview;
    private Double voteAverage;
    private Integer voteCount;
    private String airDate;
    private Integer episodeNumber;
    private String episodeType;
    private String productionCode;
    private Integer runtime;
    private Integer seasonNumber;
    private Integer showId;
    private String stillPath;

}