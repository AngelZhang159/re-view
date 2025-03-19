import {inject, Injectable} from '@angular/core';
import {map} from 'rxjs';
import {UserResponse} from '../models/userResponse';
import {AuthApiService} from './auth-api.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiService = inject(AuthApiService)

  login(email: string, password: string, rememberMe: boolean) {
    return this.apiService.login({email: email, password: password}).pipe(map((response: UserResponse) => {
      return this.processLoginResponse(response, rememberMe)
    }))
  }

  private processLoginResponse(userResponse: UserResponse, rememberMe: boolean ) {
    this.saveUserDetails(userResponse, rememberMe)
    return userResponse
  }

  private saveUserDetails(userResponse: UserResponse, rememberMe: boolean) {
    localStorage.clear()
    localStorage.setItem("accessToken", userResponse.accessToken)
    localStorage.setItem("refreshToken", userResponse.refreshToken)
    localStorage.setItem("username", userResponse.user.username)
    localStorage.setItem("email", userResponse.user.email)
    localStorage.setItem("profilePicture", userResponse.user.profilePicture)
    localStorage.setItem("expiresIn", String((userResponse.expiresIn * 1000) + Date.now()))
    localStorage.setItem("rememberMe", String(rememberMe))
  }

  public isTokenExpired() {
    return Date.now() >= Number(localStorage.getItem("expiresIn"));
  }

  public logOut() {
    localStorage.clear()
  }

  public getAccessToken() {
    return localStorage.getItem("accessToken")
  }

  public getRefreshToken() {
    return localStorage.getItem("refreshToken")
  }

  public refreshToken() {
    const refreshToken = localStorage.getItem("refreshToken");
    if (!refreshToken) throw new Error("No auth token found")
    return this.apiService.refreshToken({refreshToken: `Bearer ${refreshToken}`}).pipe(map((response: UserResponse) => {
      return this.processLoginResponse(response, Boolean(localStorage.getItem("rememberMe")))
    }))
  }
}
