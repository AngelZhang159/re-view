import {Component, inject, Inject, ViewEncapsulation} from '@angular/core';
import {DetailsResponse} from '../../../core/models/details-response';
import {MAT_BOTTOM_SHEET_DATA} from '@angular/material/bottom-sheet';
import {MatChip, MatChipSet} from '@angular/material/chips';
import {Button} from 'primeng/button';
import {MatIcon} from '@angular/material/icon';
import {MatDialog} from '@angular/material/dialog';
import {CreateReviewComponent} from '../create-review/create-review.component';
import {Skeleton} from 'primeng/skeleton';

@Component({
  selector: 'app-details',
  imports: [
    MatChipSet,
    MatChip,
    Button,
    MatIcon,
    Skeleton
  ],
  templateUrl: './details.component.html',
  styleUrl: './details.component.css',
  encapsulation: ViewEncapsulation.None
})
export class DetailsComponent {

  dialog = inject(MatDialog)

  constructor(@Inject(MAT_BOTTOM_SHEET_DATA) public data: { details: DetailsResponse }) {
    this.details = data.details
  }

  details: DetailsResponse;

  protected readonly String = String;
  showPoster: boolean = false;
  showBackground: boolean = false;

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

  loadPoster() {
    this.showPoster = true
  }

  loadBackground() {
    this.showBackground = true
  }
}
