package dev.angelzhang.reviewservice;

import dev.angelzhang.reviewservice.dto.ReviewRequest;
import dev.angelzhang.reviewservice.dto.ReviewResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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

    @GetMapping("/list/{userId}")
    public ResponseEntity<Page<ReviewResponse>> getAllReviewsById(
            @RequestHeader("Authorization") String token,
            @PathVariable Long userId,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "30") Integer size,
            @RequestParam(value = "type", required = false) String type
    ) {
        return reviewService.getAllReviews(token, userId, type, page, size);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<ReviewResponse>> getAllReviews(
            @RequestHeader("Authorization") String token,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "30") Integer size,
            @RequestParam(value = "type", required = false) String type
    ) {
        return reviewService.getAllReviews(token, null, type, page, size);
    }
//
//    @GetMapping("/{userId}/{type}")
//    public ResponseEntity<ReviewResponse> getReviewsByType(@NotNull @PathVariable Long userId, @NotBlank @PathVariable String type) {
//        return ResponseEntity.ok(reviewService.getReviewsByType(userId, type));
//    }
}
