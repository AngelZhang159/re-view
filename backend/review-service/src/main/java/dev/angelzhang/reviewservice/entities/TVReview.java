package dev.angelzhang.reviewservice.entities;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TVReview extends Review {

    @NotNull(message = "TV ID cannot be null")
    private Long tvId;
    private Integer seasonsWatched;

}