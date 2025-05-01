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
    if (rememberMe) {
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
    } else {
      sessionStorage.setItem("accessToken", userResponse.accessToken)
      sessionStorage.setItem("refreshToken", userResponse.refreshToken)
      sessionStorage.setItem("username", userResponse.user.username)
      sessionStorage.setItem("email", userResponse.user.email)
      if (userResponse.user.profilePictureUrl != null && userResponse.user.profilePictureUrl !== "" && userResponse.user.profilePictureUrl !== 'null') {
        sessionStorage.setItem("profilePicture", userResponse.user.profilePictureUrl)
      } else {
        sessionStorage.setItem("profilePicture", "/icons/user.svg")
      }
      sessionStorage.setItem("expiresIn", String((userResponse.expiresIn * 1000) + Date.now()))
    }

    if (rememberMe) localStorage.setItem("rememberMe", String(rememberMe))
  }

  public isTokenExpired() {
    if (localStorage.getItem("rememberMe")) {
      return Date.now() >= Number(localStorage.getItem("expiresIn"));
    } else {
      return Date.now() >= Number(sessionStorage.getItem("expiresIn"));
    }
  }

  public logOut() {
    localStorage.clear()
    sessionStorage.clear()
  }

  public getAccessToken() {
    if (localStorage.getItem("rememberMe")) {
      return localStorage.getItem("accessToken")
    } else {
      return sessionStorage.getItem("accessToken")
    }
  }

  public getRefreshToken() {
    if (localStorage.getItem("rememberMe")) {
      return localStorage.getItem("refreshToken")
    } else {
      return sessionStorage.getItem("refreshToken")
    }
  }

  public getUsername() {
    if (localStorage.getItem("rememberMe")) {
      return localStorage.getItem("username")
    } else {
      return sessionStorage.getItem("username")
    }
  }

  public getEmail() {
    if (localStorage.getItem("rememberMe")) {
      return localStorage.getItem("email")
    } else {
      return sessionStorage.getItem("email")
    }
  }

  public getProfilePicture() {
    if (localStorage.getItem("rememberMe")) {
      return localStorage.getItem("profilePicture")
    } else {
      return sessionStorage.getItem("profilePicture")
    }
  }

  public refreshToken() {

    const refreshToken = localStorage.getItem("refreshToken") ? localStorage.getItem("refreshToken") : sessionStorage.getItem("refreshToken");

    if (!refreshToken) throw new Error("No auth token found")
    return this.authApiService.refreshToken({refreshToken: `Bearer ${refreshToken}`}).pipe(map((response: UserLoginResponse) => {
      return this.processLoginResponse(response, Boolean(localStorage.getItem("rememberMe")))
    }))
  }
}
