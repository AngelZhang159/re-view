import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {SearchMultiResponse} from '../models/search-multi-response';
import {enviroment} from '../components/enviroments/enviroment';
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

  getDetails(type: string, id: string) {
    return this.http.get<DetailsResponse>(`${this.apiUrl}/media/details/${type}/${id}`)
  }
}
