import {Component, inject} from '@angular/core';
import {MediaService} from '../../services/media.service';
import {MatCard, MatCardHeader, MatCardTitle} from '@angular/material/card';
import {NgOptimizedImage} from '@angular/common';
import {RouterLink} from '@angular/router';
import {Carousel} from 'primeng/carousel';
import {MenuService} from '../../services/menu.service';
import {MatIcon} from '@angular/material/icon';
import {MatIconButton} from '@angular/material/button';

@Component({
  selector: 'app-content',
  imports: [
    MatCard,
    MatCardHeader,
    MatCardTitle,
    NgOptimizedImage,
    RouterLink,
    Carousel,
    MatIcon,
    MatIconButton
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
