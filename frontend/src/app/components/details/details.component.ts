import {Component, inject, input, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {DetailsResponse} from '../../models/details-response';
import {MediaService} from '../../services/media.service';
import {NgOptimizedImage} from '@angular/common';

@Component({
  selector: 'app-details',
  imports: [
    NgOptimizedImage
  ],
  templateUrl: './details.component.html',
  styleUrl: './details.component.css'
})
export class DetailsComponent implements OnInit{
  mediaService = inject(MediaService)
  type = input.required<string>()

  id = input.required<number>()

  details: DetailsResponse | undefined;

  constructor() {
    console.log("DETAILS COMPONENT CONSTRUCTOR")
  }

  ngOnInit(): void {
    if (this.id != null && this.type != null) {
      this.mediaService.getDetails(this.type(), this.id()).subscribe(data => {
        console.log(data)
        this.details = data
      })
    }
  }

  protected readonly JSON = JSON;
}
