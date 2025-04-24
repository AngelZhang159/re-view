import {Component, inject} from '@angular/core';
import {ReviewService} from '../../services/review.service';
import {ReviewResponsePage} from '../../models/review';
import {NgOptimizedImage} from '@angular/common';
import {MatSidenav, MatSidenavContainer, MatSidenavContent} from "@angular/material/sidenav";

@Component({
  selector: 'app-review',
  imports: [
    NgOptimizedImage,
    MatSidenav,
    MatSidenavContainer,
    MatSidenavContent
  ],
  templateUrl: './review.component.html',
  styleUrl: './review.component.css'
})
export class ReviewComponent {

  reviewService = inject(ReviewService)

  reviewResponsePage : ReviewResponsePage | undefined;
  isOpen: boolean = false;

  constructor() {
    this.reviewService.getReviews().subscribe(data => {
      this.reviewResponsePage = data;
    })
  }

  toggleSideNav(closeable: boolean) {
    this.isOpen = !closeable;
  }
}
