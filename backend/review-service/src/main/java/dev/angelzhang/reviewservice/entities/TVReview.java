package dev.angelzhang.reviewservice.entities;

import dev.angelzhang.reviewservice.dto.ReviewRequest;
import dev.angelzhang.reviewservice.enums.Type;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TVReview extends Review {

    @NotNull(message = "TV ID cannot be null")
    private Long tvId;
    private Integer seasonsWatched;

    public static TVReview fromRequest(Long userId, ReviewRequest request) {
        return TVReview.builder()
                .userId(userId)
                .type(Type.fromString(request.type()))
                .review(request.review())
                .rating(request.rating())
                .seasonsWatched(request.seasonsWatched())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .tvId(request.mediaId())
                .build();
    }

}