package dev.angelzhang.reviewservice.dto;

import java.util.List;

public record AllReviewsResponse(
        Long page,
        List<ReviewResponse> reviews,
        Long totalReviews,
        Long totalPages
) {
}
