import {inject, Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {ReviewRequest, ReviewResponse, ReviewResponsePage} from '../models/review';

@Injectable({
  providedIn: 'root'
})
export class ReviewApiService {

  http = inject(HttpClient)
  private apiUrl = environment.apiReviewUrl

  createReview(reviewRequest: ReviewRequest) {
    return this.http.post(this.apiUrl + '/review', reviewRequest)
  }

  deleteReview(reviewId: number) {
    return this.http.delete(this.apiUrl + '/review/' + reviewId)
  }

  getReview(reviewId: number) {
    return this.http.get(this.apiUrl + '/review/' + reviewId)
  }

  updateReview(reviewId: number, reviewRequest: ReviewRequest) {
    return this.http.patch(this.apiUrl + '/review/' + reviewId, reviewRequest)
  }

  getReviews() {
    return this.http.get<ReviewResponsePage>(this.apiUrl + '/review/list')
  }

  getReviewsByUser(userId: number) {
    return this.http.get(this.apiUrl + '/review/list/' + userId)
  }

  getReviewByParameters(reviewParams: HttpParams) {
    return this.http.get<ReviewResponse>(this.apiUrl + '/review', { params : reviewParams } )
  }
}
