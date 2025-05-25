import {HttpInterceptorFn} from '@angular/common/http';
import {catchError, of, timeout} from 'rxjs';
import {inject} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from '../services/auth.service';

export const serviceUpInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(AuthService)
  const router = inject(Router);
  return next(req).pipe(
    timeout(3000),
    catchError((error) => {
      authService.logOut()
      router.navigate(['/down']);
      return of(error);
    })
  );
};
