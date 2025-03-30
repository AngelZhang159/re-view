import {Component, inject} from '@angular/core';
import {SearchMultiBody, SearchMultiResponse} from '../../models/search-multi-response';
import {MediaService} from '../../services/media.service';
import {Subscription} from 'rxjs';
import {MatCard, MatCardContent, MatCardFooter, MatCardHeader, MatCardTitle} from '@angular/material/card';
import {NgOptimizedImage} from '@angular/common';
import {MatChip, MatChipSet} from '@angular/material/chips';
import {RouterLink} from '@angular/router';
import {Carousel} from 'primeng/carousel';
import {MenuService} from '../../services/menu.service';

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
    RouterLink,
    Carousel
  ],
  templateUrl: './content.component.html',
  styleUrl: './content.component.css'
})
export class ContentComponent {

  mediaService = inject(MediaService);
  menuService = inject(MenuService);
  trendingSeriesToday: any;
  trendingMoviesToday: any;

  constructor() {
    this.mediaService.getTrending('tv').subscribe(data => {
        this.trendingSeriesToday = data.results;
      }
    )

    this.mediaService.getTrending('movie').subscribe(data => {
      this.trendingMoviesToday = data.results;
    })
  }

}
