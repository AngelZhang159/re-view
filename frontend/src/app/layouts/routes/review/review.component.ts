import {Component, inject} from '@angular/core';
import {ReviewService} from '../../../core/services/review.service';
import {ReviewResponse, ReviewResponsePage} from '../../../core/models/review';
import {NgOptimizedImage} from '@angular/common';
import {MatSidenav, MatSidenavContainer, MatSidenavContent} from "@angular/material/sidenav";
import {MatIcon} from '@angular/material/icon';
import {MatIconButton} from '@angular/material/button';
import {Tooltip} from 'primeng/tooltip';

@Component({
  selector: 'app-review',
  imports: [
    NgOptimizedImage,
    MatSidenav,
    MatSidenavContainer,
    MatSidenavContent,
    MatIcon,
    MatIconButton,
    Tooltip
  ],
  templateUrl: './review.component.html',
  styleUrl: './review.component.css'
})
export class ReviewComponent {

  reviewService = inject(ReviewService)

  reviewResponsePage : ReviewResponsePage | undefined;
  isOpen: boolean = false;
  currentReview : ReviewResponse | undefined;

  constructor() {
    this.reviewService.getReviews().subscribe(data => {
      this.reviewResponsePage = data;
    })
  }

  toggleSideNav(closeable: boolean) {
    this.isOpen = !closeable;
  }

  openSideNav(review: ReviewResponse) {
    this.toggleSideNav(false)
    this.currentReview = review;
  }
}
