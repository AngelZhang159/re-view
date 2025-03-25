import {Component, inject, OnInit} from '@angular/core';
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
  route = inject(ActivatedRoute)
  mediaService = inject(MediaService)
  type = this.route.snapshot.paramMap.get("type")

  id = this.route.snapshot.paramMap.get("id")

  details: DetailsResponse | undefined;

  ngOnInit(): void {
    if (this.id != null && this.type != null) {
      this.mediaService.getDetails(this.type, this.id).subscribe(data => {
        console.log(data)
        this.details = data
      })
    }
  }

  protected readonly JSON = JSON;
}
