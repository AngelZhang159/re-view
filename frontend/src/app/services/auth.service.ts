import {inject, Injectable} from '@angular/core';
import {map} from 'rxjs';
import {UserResponse} from '../models/userResponse';
import {AuthApiService} from './auth-api.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiService = inject(AuthApiService)

  login(email: string, password: string, rememberMe?: boolean) {
    return this.apiService.login({email: email, password: password}).pipe(map((response: UserResponse) => {
      return this.processLoginResponse(response, rememberMe)
    }))
  }

  private processLoginResponse(userResponse: UserResponse, rememberMe: boolean | undefined) {
    this.saveUserDetails(userResponse)
    return userResponse.user
  }

  private saveUserDetails(userResponse: UserResponse) {
    localStorage.clear()
    localStorage.setItem("accessToken", userResponse.accessToken)
    localStorage.setItem("refreshToken", userResponse.refreshToken)
    localStorage.setItem("username", userResponse.user.username)
    localStorage.setItem("email", userResponse.user.email)
    localStorage.setItem("profilePicture", userResponse.user.profilePicture)
    localStorage.setItem("expiresIn", String((userResponse.expiresIn  * 1000) + Date.now()))
  }

  public isTokenExpired() {
    return Date.now() >= Number(localStorage.getItem("expiresIn"));
  }

  public logOut() {
    localStorage.clear()
  }
}
