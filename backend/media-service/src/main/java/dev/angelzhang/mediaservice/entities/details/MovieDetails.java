package dev.angelzhang.mediaservice.entities.details;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

@Entity
@Getter
@Setter
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

}