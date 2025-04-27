import {Component, inject} from '@angular/core';
import {MediaService} from '../../../core/services/media.service';
import {MatCard, MatCardHeader, MatCardTitle} from '@angular/material/card';
import {NgOptimizedImage} from '@angular/common';
import {Carousel} from 'primeng/carousel';
import {MenuService} from '../../../core/services/menu.service';
import {MatBottomSheet} from '@angular/material/bottom-sheet';
import {DetailsComponent} from '../../components/details/details.component';
import {CarouselComponent} from '../../components/carousel/carousel.component';

@Component({
  selector: 'app-content',
  imports: [
    MatCard,
    MatCardHeader,
    MatCardTitle,
    NgOptimizedImage,
    Carousel,
    CarouselComponent
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  menuService = inject(MenuService);
  mediaService = inject(MediaService);
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
