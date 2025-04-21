import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {SearchMultiResponse} from '../models/search-multi-response';
import {environment} from '../../environments/environment';
import {DetailsResponse} from '../models/details-response';

@Injectable({
  providedIn: 'root'
})
export class MediaApiService {

  http = inject(HttpClient)
  private apiUrl = environment.apiMediaUrl

  searchMulti(query: string) {
    return this.http.get<SearchMultiResponse>(`${this.apiUrl}/media/search/multi?query=${query}`)
  }

  getDetails(mediaType: string, id: number) {
    return this.http.get<DetailsResponse>(`${this.apiUrl}/media/details/${mediaType}/${id}`)
  }

  getTrending(mediaType: string) {
    //type can be 'tv' or 'movie'
    //can add a time window 'day' or 'week', default is 'day'
    return this.http.get<SearchMultiResponse>(`${this.apiUrl}/media/trending/${mediaType}`)
  }
}
