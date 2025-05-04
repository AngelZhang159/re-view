import {Component, inject, Inject} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {DetailsResponse} from '../../../core/models/details-response';
import {DIALOG_DATA} from '@angular/cdk/dialog';
import {Step, StepList, StepPanel, StepPanels, Stepper} from 'primeng/stepper';
import {Button} from 'primeng/button';
import {Rating} from 'primeng/rating';
import {MatDialogClose} from '@angular/material/dialog';
import {MatFormField, MatInput, MatLabel} from '@angular/material/input';
import {MatSnackBar} from '@angular/material/snack-bar';
import {ReviewService} from '../../../core/services/review.service';
import {ReviewResponse} from '../../../core/models/review';
import {MatBottomSheet} from '@angular/material/bottom-sheet';

@Component({
  selector: 'app-create-review',
  imports: [
    FormsModule,
    Stepper,
    StepList,
    Step,
    StepPanels,
    StepPanel,
    Button,
    Rating,
    MatDialogClose,
    MatFormField,
    MatLabel,
    MatInput,
  ],
  templateUrl: './create-review.component.html',
  styleUrl: './create-review.component.css'
})
export class CreateReviewComponent {

  snackBar = inject(MatSnackBar)
  reviewService = inject(ReviewService)
  private bottomSheet = inject(MatBottomSheet)

  constructor(@Inject(DIALOG_DATA) public data: {
    details: DetailsResponse,
    currentReview: ReviewResponse | undefined
  }) {
    this.details = data.details
    this.currentReview = data.currentReview
    if (this.currentReview) {
      this.rating = this.currentReview.rating
      this.reviewText = this.currentReview.review
      this.updateReview = false
    } else {
      this.rating = 10
      this.reviewText = ''
      this.updateReview = true
    }
  }

  details: DetailsResponse;
  currentReview: ReviewResponse | undefined;

  rating: number;
  reviewText: string;
  updateReview: boolean;

  closeCreateReviewDialog() {
    this.bottomSheet.dismiss()

    if (this.updateReview) {
      this.reviewService.createReview(this.details.mediaType, this.details.id, this.reviewText, this.rating, undefined).subscribe({
        next: (_) => {
          this.snackBar.open('Review created successfully', 'Close', {
            duration: 2000,
          });
        },
        error: (error) => {
          this.snackBar.open('Error creating review: ' + JSON.stringify(error), 'Close', {
            duration: 2000,
            politeness: "assertive"
          });
        }
      })
    } else {
      if (this.currentReview) {
        this.reviewService.updateReview(this.currentReview.id, this.currentReview.mediaType, this.currentReview.mediaId, this.reviewText, this.rating).subscribe({
          next: (_) => {
            this.snackBar.open('Review updated successfully', 'Close', {
              duration: 2000,
            });
          },
          error: (error) => {
            this.snackBar.open('Error updated review: ' + JSON.stringify(error), 'Close', {
              duration: 2000,
              politeness: "assertive"
            });
          }
        })
      } else {
        this.snackBar.open('Unknown error, should never happen (updating review when there is no previous review)', 'Close', {
          duration: 2000
        })
      }
    }
  }
}
