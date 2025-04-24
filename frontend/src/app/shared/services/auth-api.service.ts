import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {LoginRequest, RegisterRequest} from '../../models/auth';
import {UserLoginResponse, UserRegisterResponse} from '../../models/user-login-response';
import {environment} from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthApiService {

  private http = inject(HttpClient)
  private apiUrl = environment.apiUserUrl

  login(loginRequest: LoginRequest) {
    return this.http.post<UserLoginResponse>(`${this.apiUrl}/user/login`, loginRequest)
  }

  register(registerRequest: RegisterRequest) {
    return this.http.post<UserRegisterResponse>(`${this.apiUrl}/user/register`, registerRequest)
  }

  refreshToken(refreshToken: { refreshToken: string }) {
    return this.http.post<UserLoginResponse>(`${this.apiUrl}/user/refresh`, refreshToken)
  }
}
