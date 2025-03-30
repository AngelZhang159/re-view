import {Component, inject} from '@angular/core';
import {SearchMultiResponse} from '../../models/search-multi-response';
import {MediaService} from '../../services/media.service';
import {Subscription} from 'rxjs';
import {MatCard, MatCardContent, MatCardFooter, MatCardHeader, MatCardTitle} from '@angular/material/card';
import {NgOptimizedImage} from '@angular/common';
import {MatChip, MatChipSet} from '@angular/material/chips';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-content',
  imports: [
    MatCard,
    MatCardHeader,
    MatCardTitle,
    NgOptimizedImage,
    MatCardContent,
    MatCardFooter,
    MatChipSet,
    MatChip,
    RouterLink
  ],
  templateUrl: './content.component.html',
  styleUrl: './content.component.css'
})
export class ContentComponent {

  mediaService = inject(MediaService);
  trendingSeriesToday: SearchMultiResponse | undefined;
  trendingMoviesToday: SearchMultiResponse | undefined;

  constructor() {
    this.mediaService.getTrending('tv').subscribe(data => {
      this.trendingSeriesToday = data;
    })

    this.mediaService.getTrending('movie').subscribe(data => {
      this.trendingMoviesToday = data;
    })
  }

}
