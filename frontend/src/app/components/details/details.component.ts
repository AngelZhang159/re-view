import {Component, inject, Inject} from '@angular/core';
import {DetailsResponse} from '../../models/details-response';
import {MAT_BOTTOM_SHEET_DATA} from '@angular/material/bottom-sheet';
import {NgOptimizedImage, NgStyle} from '@angular/common';
import {MatChip, MatChipSet} from '@angular/material/chips';
import {Button} from 'primeng/button';
import {MatIcon} from '@angular/material/icon';
import {MatDialog} from '@angular/material/dialog';
import {CreateReviewComponent} from '../create-review/create-review.component';

@Component({
  selector: 'app-details',
  imports: [
    NgOptimizedImage,
    NgStyle,
    MatChipSet,
    MatChip,
    Button,
    MatIcon
  ],
  templateUrl: './details.component.html',
  styleUrl: './details.component.css'
})
export class DetailsComponent {

  dialog = inject(MatDialog)

  constructor(@Inject(MAT_BOTTOM_SHEET_DATA) public data: { details: DetailsResponse }) {
    this.details = data.details
    console.log(JSON.stringify(this.details))
  }

  details: DetailsResponse;

  protected readonly JSON = JSON;
  protected readonly String = String;

  getCountryFlag(country: string): string {
    if (country.length !== 2) return '';
    const offset = 0x1F1E6;
    const firstChar = country.charCodeAt(0) - 65 + offset;
    const secondChar = country.charCodeAt(1) - 65 + offset;
    return String.fromCodePoint(firstChar, secondChar);
  }

  openCreateReviewDialog() {
    this.dialog.open(CreateReviewComponent, {
      data: {
        details: this.details
      },
      height: '600px',
      width: '800px',
      maxHeight: '800px',
      maxWidth: '1200px',
    })
  }
}
