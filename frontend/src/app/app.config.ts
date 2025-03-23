import {ApplicationConfig, provideZoneChangeDetection} from '@angular/core';
import {provideRouter, withComponentInputBinding} from '@angular/router';

import {routes} from './app.routes';
import {provideHttpClient, withFetch, withInterceptors} from '@angular/common/http';
import {requestInterceptor} from './interceptors/request.interceptor';

export const appConfig: ApplicationConfig = {
  providers: [provideHttpClient(withFetch(), withInterceptors([requestInterceptor])), provideZoneChangeDetection({eventCoalescing: true}), provideRouter(routes, withComponentInputBinding())]
};
