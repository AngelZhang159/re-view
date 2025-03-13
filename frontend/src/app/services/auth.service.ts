import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {enviroment} from '../components/enviroments/enviroment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private http = inject(HttpClient)
  private apiUrl = enviroment.apiUrl

  login(email: string, password: string, rememberMe?: boolean) {
    return this.http.post(`${this.apiUrl}/user/login`, {email: email, password: password})
  }

}
