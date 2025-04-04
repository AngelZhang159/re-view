import {Component, Inject} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {DetailsResponse} from '../../models/details-response';
import {DIALOG_DATA} from '@angular/cdk/dialog';
import {Step, StepList, StepPanel, StepPanels, Stepper} from 'primeng/stepper';
import {Button} from 'primeng/button';
import {Rating} from 'primeng/rating';
import {Editor} from 'primeng/editor';

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
    Editor,
  ],
  templateUrl: './create-review.component.html',
  styleUrl: './create-review.component.css'
})
export class CreateReviewComponent {
  constructor(@Inject(DIALOG_DATA) public data: { details: DetailsResponse }) {
    this.details = data.details
    console.log(JSON.stringify(this.details))
  }

  details: DetailsResponse;

  rating = 10;
  reviewText: any;

  closeCreateReviewDialog() {

  }

  printText() {
    console.log(this.reviewText)
  }
}
