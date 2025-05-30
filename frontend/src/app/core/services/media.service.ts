import {inject, Injectable} from '@angular/core';
import {MediaApiService} from './media-api.service';

@Injectable({
  providedIn: 'root'
})
export class MediaService {

  private mediaApiService = inject(MediaApiService)

  searchMulti(query: string) {
    return this.mediaApiService.searchMulti(query)
  }

  getDetails(mediaType: string, id: number) {
    return this.mediaApiService.getDetails(mediaType, id)
  }

  getTrending(tv: string) {
    return this.mediaApiService.getTrending(tv)
  }
}
