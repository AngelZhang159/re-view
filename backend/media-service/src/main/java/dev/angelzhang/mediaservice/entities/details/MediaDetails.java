package dev.angelzhang.mediaservice.entities.details;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@MappedSuperclass
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MediaDetails {

    @Id
    private Long id;

    private Boolean adult;
    private String backdropPath;
    private String homepage;

    @ElementCollection(targetClass = CountryCode.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<CountryCode> originCountry;

    private String originalLanguage;
    @Column(length = 1024)
    private String overview;
    private Double popularity;
    private String posterPath;
    private String status;
    private String tagline;
    private Double voteAverage;
    private Integer voteCount;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    private List<Genre> genres;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    private List<ProductionCompany> productionCompanies;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    private List<ProductionCountry> productionCountries;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    private List<SpokenLanguage> spokenLanguages;

}