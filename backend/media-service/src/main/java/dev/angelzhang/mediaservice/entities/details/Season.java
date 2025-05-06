package dev.angelzhang.mediaservice.entities.details;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Season {

    @Id
    private Integer id;
    private String airDate;
    private Integer episodeCount;
    private String name;
    @Column(length = 1024)
    private String overview;
    private String posterPath;
    private Integer seasonNumber;
    private Double voteAverage;

    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    private TVDetails tvDetails;

}