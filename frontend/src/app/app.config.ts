import {ApplicationConfig, provideZoneChangeDetection} from '@angular/core';
import {provideRouter, withComponentInputBinding} from '@angular/router';

import {routes} from './app.routes';
import {provideHttpClient, withFetch, withInterceptors} from '@angular/common/http';
import {requestInterceptor} from './interceptors/request.interceptor';
import {provideAnimationsAsync} from '@angular/platform-browser/animations/async';
import {providePrimeNG} from 'primeng/config';
import Material from '@primeng/themes/material';
import {toCamelCaseInterceptor} from './interceptors/to-camel-case.interceptor';

export const appConfig: ApplicationConfig = {
  providers: [
    provideHttpClient(
      withFetch(),
      withInterceptors(
        [requestInterceptor, toCamelCaseInterceptor]
      )),
    provideZoneChangeDetection(
      {eventCoalescing: true}
    ),
    provideRouter(
      routes,
      withComponentInputBinding()
    ),
    provideAnimationsAsync(),
    providePrimeNG({
      theme: {
        preset: Material
      }
    })
  ]
};
