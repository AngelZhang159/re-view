package dev.angelzhang.reviewservice;

import dev.angelzhang.reviewservice.dto.ReviewRequest;
import dev.angelzhang.reviewservice.dto.ReviewResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
@Slf4j
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewResponse> createReview(@RequestHeader("Authorization") String token, @RequestBody @Valid ReviewRequest reviewRequest) {
        log.info("Creating review: {}", reviewRequest);
        return reviewService.createReview(token, reviewRequest);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewResponse> getReview(@RequestHeader("Authorization") String token, @PathVariable @NotNull Long reviewId) {
        return reviewService.getReview(token, reviewId);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<ReviewResponse> deleteReview(@RequestHeader("Authorization") String token, @PathVariable Long reviewId) {
        return reviewService.deleteReview(token, reviewId);
    }

    @PatchMapping("/{reviewId}")
    public ResponseEntity<ReviewResponse> updateReview(@RequestHeader("Authorization") String token, @PathVariable Long reviewId, @RequestBody ReviewRequest reviewRequest) {
        log.info("Updating review: {}", reviewRequest);
        return reviewService.updateReview(token, reviewId, reviewRequest);
    }
//
//    @GetMapping("/{userId}")
//    public ResponseEntity<ReviewResponse> getAllReviews(@RequestHeader("Authorization") String token, @NotNull @PathVariable Long userId) {
//        return ResponseEntity.ok(reviewService.getAllReviews(userId));
//    }
//
//    @GetMapping("/{userId}/{type}")
//    public ResponseEntity<ReviewResponse> getReviewsByType(@NotNull @PathVariable Long userId, @NotBlank @PathVariable String type) {
//        return ResponseEntity.ok(reviewService.getReviewsByType(userId, type));
//    }
}
