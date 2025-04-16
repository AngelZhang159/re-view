import {Component, inject} from '@angular/core';
import {ReviewService} from '../../services/review.service';
import {ReviewResponsePage} from '../../models/review';
import {NgOptimizedImage} from '@angular/common';
import {MenuService} from '../../services/menu.service';

@Component({
  selector: 'app-review',
  imports: [
    NgOptimizedImage
  ],
  templateUrl: './review.component.html',
  styleUrl: './review.component.css'
})
export class ReviewComponent {

  reviewService = inject(ReviewService)
  menuService = inject(MenuService)

  reviewResponsePage : ReviewResponsePage | undefined;

  constructor() {
    this.reviewService.getReviews().subscribe(data => {
      this.reviewResponsePage = data;
    })
  }
}
