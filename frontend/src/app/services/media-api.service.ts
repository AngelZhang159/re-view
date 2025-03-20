import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {SearchMultiResponse} from '../models/searchMultiResponse';
import {enviroment} from '../components/enviroments/enviroment';

@Injectable({
  providedIn: 'root'
})
export class MediaApiService {

  http = inject(HttpClient)
  private apiUrl = enviroment.apiMediaUrl

  searchMulti(query: string) {
    return this.http.get<SearchMultiResponse>(`${this.apiUrl}/media/search/multi?query=${query}`)
  }
}
