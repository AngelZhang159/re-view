package dev.angelzhang.mediaservice.repositories;

import dev.angelzhang.mediaservice.entities.details.TVDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TVDetailsRepository extends JpaRepository<TVDetails, Long> {
}
