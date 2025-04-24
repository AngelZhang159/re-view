import {CanActivateFn, Router} from '@angular/router';
import {inject} from '@angular/core';
import {AuthService} from '../services/auth.service';

export const homeGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService)
  const router = inject(Router)

  if (authService.isTokenExpired()) return true
  //TODO Add relogin with refresh token before navigating to app

  router.navigate(["/app"])
  return false;
};
