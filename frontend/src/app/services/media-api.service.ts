import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {SearchMultiResponse} from '../models/search-multi-response';
import {enviroment} from '../../enviroments/enviroment';
import {DetailsResponse} from '../models/details-response';

@Injectable({
  providedIn: 'root'
})
export class MediaApiService {

  http = inject(HttpClient)
  private apiUrl = enviroment.apiMediaUrl

  searchMulti(query: string) {
    return this.http.get<SearchMultiResponse>(`${this.apiUrl}/media/search/multi?query=${query}`)
  }

  getDetails(type: string, id: number) {
    return this.http.get<DetailsResponse>(`${this.apiUrl}/media/details/${type}/${id}`)
  }

  getTrending(type: string) {
    //type can be 'tv' or 'movie'
    //can add a time window 'day' or 'week', default is 'day'
    return this.http.get<SearchMultiResponse>(`${this.apiUrl}/media/trending/${type}`)
  }
}
