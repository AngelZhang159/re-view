package dev.angelzhang.reviewservice.entities;

import dev.angelzhang.reviewservice.dto.ReviewRequest;
import dev.angelzhang.reviewservice.enums.Type;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovieReview extends Review{

    private Long movieId;

    public static MovieReview fromRequest(Long userId, ReviewRequest reviewRequest) {
        return MovieReview.builder()
                .userId(userId)
                .type(Type.fromString(reviewRequest.type()))
                .review(reviewRequest.review())
                .rating(reviewRequest.rating())
                .movieId(reviewRequest.mediaId())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }
}
