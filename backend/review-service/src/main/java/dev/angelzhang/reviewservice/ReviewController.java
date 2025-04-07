package dev.angelzhang.reviewservice;

import dev.angelzhang.reviewservice.clients.MediaClient;
import dev.angelzhang.reviewservice.dto.ReviewRequest;
import dev.angelzhang.reviewservice.dto.ReviewResponse;
import dev.angelzhang.reviewservice.dto.media.details.DetailsAPIResponse;
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
    private final MediaClient mediaClient;

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

    @GetMapping("/test/details")
    public DetailsAPIResponse test() {
        return mediaClient.details("Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbWyJVU0VSIl1dLCJ0b2tlbl90eXBlIjoiYWNjZXNzIiwic3ViIjoiMSIsImlhdCI6MTc0NDA0MjUxMSwiZXhwIjoxNzQ0MDQ2MTExLCJpc3MiOiJhbmdlbHpoYW5nLmRldiJ9.HVrkR6ZIvdmoP-GU2UhZFFhdYR-n7wq4VOxCSyfF39XU9L-JV2udCaurqKUF4W2s-nH_dwAu0FPpGA-HDP5iWw", "movie", 1999999L);
    }
}
