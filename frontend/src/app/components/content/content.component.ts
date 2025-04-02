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
  templateUrl: './content.component.html',
  styleUrl: './content.component.css'
})
export class ContentComponent {

  mediaService = inject(MediaService);
  menuService = inject(MenuService);
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

  openDrawer(media_type: any, id: number) {
    if (id != null || id != undefined && media_type != null || media_type != undefined) {
      this.mediaService.getDetails(media_type, id).subscribe(data => {
        this.bottomSheet.open(DetailsComponent, {data: {details: data}, height: '80vh'});
      })
    }
  }
}
