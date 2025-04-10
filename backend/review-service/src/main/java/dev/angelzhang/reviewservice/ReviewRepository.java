package dev.angelzhang.reviewservice;

import dev.angelzhang.reviewservice.entities.Review;
import dev.angelzhang.reviewservice.enums.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
//    Page<Review> findByUserId(Long userId, Pageable pageable);

    Page<Review> findByTypeAndUserId(Type type, Long tokenUserId, Pageable pageable);

    Page<Review> findAllByUserId(Long userId, Pageable pageable);
}
