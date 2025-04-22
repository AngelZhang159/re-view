import {Component, inject} from '@angular/core';
import {MediaService} from '../../services/media.service';
import {MatCard, MatCardHeader, MatCardTitle} from '@angular/material/card';
import {NgOptimizedImage} from '@angular/common';
import {Carousel} from 'primeng/carousel';
import {MenuService} from '../../services/menu.service';
import {MatBottomSheet} from '@angular/material/bottom-sheet';
import {DetailsComponent} from '../details/details.component';

@Component({
  selector: 'app-content',
  imports: [
    MatCard,
    MatCardHeader,
    MatCardTitle,
    NgOptimizedImage,
    Carousel
  ],
  templateUrl: './main.component.html',
  styleUrl: './main.component.css'
})
export class MainComponent {

  menuService = inject(MenuService);
  mediaService = inject(MediaService);
  private bottomSheet = inject(MatBottomSheet)

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

  openDrawer(mediaType: any, id: number) {
    if (id != null || id != undefined && mediaType != null || mediaType != undefined) {
      this.mediaService.getDetails(mediaType, id).subscribe(data => {
        this.bottomSheet.open(DetailsComponent, {data: {details: data}, height: '80vh'});
      })
    }
  }
}
