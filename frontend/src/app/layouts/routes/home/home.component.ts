import {Component, inject} from '@angular/core';
import {MediaService} from '../../../core/services/media.service';
import {CarouselComponent} from '../../components/carousel/carousel.component';

@Component({
  selector: 'app-content',
  imports: [
    CarouselComponent
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

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
