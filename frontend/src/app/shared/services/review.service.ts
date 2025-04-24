import {inject, Injectable} from '@angular/core';
import {ReviewApiService} from './review-api.service';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  reviewApiService = inject(ReviewApiService);

  createReview(type: string, mediaId: number, review: string, rating: number, seasonsWatched?: number) {
    return this.reviewApiService.createReview({type: type, mediaId: mediaId, review: review, rating: rating, seasonsWatched: seasonsWatched });
  }

  deleteReview(reviewId: number) {
    return this.reviewApiService.deleteReview(reviewId);
  }

  getReview(reviewId: number) {
    return this.reviewApiService.getReview(reviewId);
  }

  updateReview(reviewId: number, type: string, mediaId: number, review: string, rating: number, seasonsWatched?: number) {
    return this.reviewApiService.updateReview(reviewId, {type: type, mediaId: mediaId, review: review, rating: rating, seasonsWatched: seasonsWatched });
  }

  getReviews() {
    return this.reviewApiService.getReviews();
  }

  getReviewsByUser(userId: number) {
    return this.reviewApiService.getReviewsByUser(userId);
  }
}
