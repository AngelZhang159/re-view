import {inject, Injectable} from '@angular/core';
import {map} from 'rxjs';
import {UserLoginResponse} from '../models/user-login-response';
import {AuthApiService} from './auth-api.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private authApiService = inject(AuthApiService)

  login(email: string, password: string, rememberMe: boolean) {
    return this.authApiService.login({email: email, password: password}).pipe(map((response: UserLoginResponse) => {
      return this.processLoginResponse(response, rememberMe)
    }))
  }

  register(username: string, email: string, password: string, profilePictureUrl?: string) {
    return this.authApiService.register({username: username, email: email, password: password, profilePictureUrl: profilePictureUrl})
  }

  private processLoginResponse(userResponse: UserLoginResponse, rememberMe: boolean ) {
    this.saveUserDetails(userResponse, rememberMe)
    return userResponse
  }

  private saveUserDetails(userResponse: UserLoginResponse, rememberMe: boolean) {
    localStorage.clear()
    localStorage.setItem("accessToken", userResponse.accessToken)
    localStorage.setItem("refreshToken", userResponse.refreshToken)
    localStorage.setItem("username", userResponse.user.username)
    localStorage.setItem("email", userResponse.user.email)
    if (userResponse.user.profilePictureUrl != null && userResponse.user.profilePictureUrl !== "" && userResponse.user.profilePictureUrl !== 'null') {
      localStorage.setItem("profilePicture", userResponse.user.profilePictureUrl)
    } else {
      localStorage.setItem("profilePicture", "/icons/user.svg")
    }
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

  public getUsername() {
    return localStorage.getItem("username")
  }

  public getEmail() {
    return localStorage.getItem("email")
  }

  public getProfilePicture() {
    return localStorage.getItem("profilePicture")
  }

  public refreshToken() {
    const refreshToken = localStorage.getItem("refreshToken");
    if (!refreshToken) throw new Error("No auth token found")
    return this.authApiService.refreshToken({refreshToken: `Bearer ${refreshToken}`}).pipe(map((response: UserLoginResponse) => {
      return this.processLoginResponse(response, Boolean(localStorage.getItem("rememberMe")))
    }))
  }
}
