import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {loginRequest} from '../models/login';
import {UserResponse} from '../models/userResponse';
import {enviroment} from '../components/enviroments/enviroment';

@Injectable({
  providedIn: 'root'
})
export class AuthApiService {

  private http = inject(HttpClient)
  private apiUrl = enviroment.apiUrl

  login(loginRequest: loginRequest) {
    return this.http.post<UserResponse>(`${this.apiUrl}/user/login`, loginRequest)
  }

  refreshToken(refreshToken: { refreshToken: string }) {
    return this.http.post<UserResponse>(`${this.apiUrl}/user/refresh`, refreshToken)
  }
}
