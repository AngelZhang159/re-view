package dev.angelzhang.mediaservice.repositories;

import dev.angelzhang.mediaservice.entities.details.MovieDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieDetailsRepository extends JpaRepository<MovieDetails, Integer> {
}
