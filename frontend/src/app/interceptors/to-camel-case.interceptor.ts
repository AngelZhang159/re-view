import {HttpInterceptorFn, HttpResponse} from '@angular/common/http';
import {map} from 'rxjs';

export const toCamelCaseInterceptor: HttpInterceptorFn = (req, next) => {
  return next(req).pipe(
    map(response => {
      if (response instanceof HttpResponse && response.body) {
        response = response.clone({ body: transformKeysToCamelCase(response.body) });
      }
      return response;
    })
  );
};

function snakeToCamel(s: string): string {
  return s.replace(/_([a-z])/g, (g) => g[1].toUpperCase());
}

function transformKeysToCamelCase(obj: any): any {
  if (Array.isArray(obj)) {
    return obj.map(item => transformKeysToCamelCase(item));
  } else if (obj !== null && obj && typeof obj === 'object') {
    return Object.keys(obj).reduce((acc, key) => {
      const camelKey = snakeToCamel(key);
      acc[camelKey] = transformKeysToCamelCase(obj[key]);
      return acc;
    }, {} as any);
  }
  return obj;
}
