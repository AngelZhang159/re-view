import {HttpInterceptorFn} from '@angular/common/http';
import {inject} from '@angular/core';
import {AuthService} from '../services/auth.service';
import {Router} from '@angular/router';

export const requestInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  if (authService.getAccessToken() == null || authService.getRefreshToken() == null) {
    router.navigate(["/login"]);
  }

  let clonedRequest = req;
  const accessToken = authService.getAccessToken();
  if (accessToken) {
    clonedRequest = req.clone({
      setHeaders: {
        Authorization: `Bearer ${accessToken}`,
      },
    });
  }


  return next(clonedRequest)
};
