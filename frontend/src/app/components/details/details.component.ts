import {Component, Inject} from '@angular/core';
import {DetailsResponse} from '../../models/details-response';
import {MAT_BOTTOM_SHEET_DATA} from '@angular/material/bottom-sheet';
import {NgOptimizedImage, NgStyle} from '@angular/common';

@Component({
  selector: 'app-details',
  imports: [
    NgOptimizedImage,
    NgStyle
  ],
  templateUrl: './details.component.html',
  styleUrl: './details.component.css'
})
export class DetailsComponent {

  constructor(@Inject(MAT_BOTTOM_SHEET_DATA) public data: { details: DetailsResponse }) {
    this.details = data.details
    console.log(JSON.stringify(this.details))
  }

  details: DetailsResponse;

  protected readonly JSON = JSON;
}
