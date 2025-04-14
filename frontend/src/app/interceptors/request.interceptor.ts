import {HttpInterceptorFn} from '@angular/common/http';
import {inject} from '@angular/core';
import {AuthService} from '../services/auth.service';
import {Router} from '@angular/router';
import {catchError, of, switchMap} from 'rxjs';

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

  return next(clonedRequest).pipe(catchError((err) => {
    if (err.status === 403 && !req.url.includes("refresh")) {
      console.error("Failed request: " + JSON.stringify(err))
      return authService.refreshToken().pipe(
        switchMap(response => {
          clonedRequest = req.clone({
            setHeaders: {
              Authorization: `Bearer ${response.accessToken}`
            }
          })
          console.error("Retrying request with new token: " + response.accessToken)
          return next(clonedRequest)
        })
      )
    }
    return of(err)
  }))
};
