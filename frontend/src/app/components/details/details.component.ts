import {Component, Inject} from '@angular/core';
import {CountryCode, DetailsResponse, ProductionCountry} from '../../models/details-response';
import {MAT_BOTTOM_SHEET_DATA} from '@angular/material/bottom-sheet';
import {NgOptimizedImage, NgStyle} from '@angular/common';
import {MatChip, MatChipSet} from '@angular/material/chips';
import {Button} from 'primeng/button';
import {MatIcon} from '@angular/material/icon';

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

  areCountriesEqual(origin_country: CountryCode[], production_countries: ProductionCountry[]) {
    return origin_country.length === production_countries.length &&
      origin_country.every((country, index) => {
        return country.toString() === production_countries[index].iso_3166_1;
      });
  }
}
