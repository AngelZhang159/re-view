package dev.angelzhang.reviewservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class MovieReview extends Review{

    @Id
    private Long movieId;
    private String movieTitle;

}
