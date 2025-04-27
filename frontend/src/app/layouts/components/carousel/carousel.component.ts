import {Component, inject, Input} from '@angular/core';
import {MatCard, MatCardHeader, MatCardTitle} from '@angular/material/card';
import {NgOptimizedImage} from '@angular/common';
import {Carousel} from 'primeng/carousel';
import {SearchMultiBody} from '../../../core/models/search-multi-response';
import {MenuService} from '../../../core/services/menu.service';
import {MatBottomSheet} from '@angular/material/bottom-sheet';
import {DetailsComponent} from '../details/details.component';
import {MediaService} from '../../../core/services/media.service';
import {Skeleton} from 'primeng/skeleton';

@Component({
  selector: 'app-carousel',
  imports: [
    MatCard,
    MatCardHeader,
    MatCardTitle,
    NgOptimizedImage,
    Carousel,
    Skeleton
  ],
  templateUrl: './carousel.component.html',
  styleUrl: './carousel.component.css'
})
export class CarouselComponent {

  menuService = inject(MenuService)
  mediaService = inject(MediaService);
  @Input() data: SearchMultiBody[] | undefined

  private bottomSheet = inject(MatBottomSheet)

  openDrawer(mediaType: any, id: number) {
    if (id != null || id != undefined && mediaType != null || mediaType != undefined) {
      this.mediaService.getDetails(mediaType, id).subscribe(data => {
        this.bottomSheet.open(DetailsComponent, {data: {details: data}, height: '80vh'});
      })
    }
  }

  protected readonly Array = Array;
}
