import {Component, inject, Inject} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {DetailsResponse} from '../../../models/details-response';
import {DIALOG_DATA} from '@angular/cdk/dialog';
import {Step, StepList, StepPanel, StepPanels, Stepper} from 'primeng/stepper';
import {Button} from 'primeng/button';
import {Rating} from 'primeng/rating';
import {MatDialogClose} from '@angular/material/dialog';
import {MatFormField, MatInput, MatLabel} from '@angular/material/input';
import {MatSnackBar} from '@angular/material/snack-bar';
import {ReviewService} from '../../../shared/services/review.service';

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

  constructor(@Inject(DIALOG_DATA) public data: { details: DetailsResponse }) {
    this.details = data.details
    console.log(JSON.stringify(this.details))
  }

  details: DetailsResponse;

  rating = 10;
  reviewText = '';

  closeCreateReviewDialog() {

    console.log("Sending info from :" + JSON.stringify(this.details))

    //TODO show modal on complete or error (snackbar maybe)
    this.reviewService.createReview(this.details.mediaType, this.details.id, this.reviewText, this.rating, undefined).subscribe({
      next: (response) => {
        console.log('Review created successfully:', response);
        this.snackBar.open('Review created successfully', 'Close', {
          duration: 2000,
        });
      },
      error: (error) => {
        console.error('Error creating review:', error);
        this.snackBar.open('Error creating review: ' + JSON.stringify(error), 'Close', {
          duration: 2000,
          politeness: "assertive"
        });
      }
    })
  }

  isRated: boolean = this.rating == null;

  checkRated() {
    this.isRated = this.rating == null;
  }
}
