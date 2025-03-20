import {inject, Injectable} from '@angular/core';
import {MediaApiService} from './media-api.service';
import {SearchMultiResponse} from '../models/searchMultiResponse';

@Injectable({
  providedIn: 'root'
})
export class MediaService {

  private mediaApiService = inject(MediaApiService)

  searchMulti(query: string) {
    return this.mediaApiService.searchMulti(query)
  }

}
