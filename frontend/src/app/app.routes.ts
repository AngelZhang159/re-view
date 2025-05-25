import {Routes} from '@angular/router';
import {homeGuard} from './core/guards/home.guard';
import {authGuard} from './core/guards/auth.guard';

export const routes: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./layouts/routes/landing/landing.component').then(m => m.LandingComponent),
    canActivate: [homeGuard],
  },
  {
    path: 'login',
    loadComponent: () =>
      import('./layouts/auth/login/login.component').then(m => m.LoginComponent),
  },
  {
    path: 'register',
    loadComponent: () =>
      import('./layouts/auth/register/register.component').then(m => m.RegisterComponent),
  },
  {
    path: 'app',
    loadComponent: () =>
      import('./layouts/main/body/body.component').then(m => m.BodyComponent),
    canActivate: [authGuard],
    children: [
      {
        path: '',
        loadComponent: () =>
          import('./layouts/routes/home/home.component').then(m => m.HomeComponent),
        outlet: 'content',
      },
      {
        path: 'details/:type/:id',
        loadComponent: () =>
          import('./layouts/components/details/details.component').then(m => m.DetailsComponent),
        outlet: 'content',
      },
      {
        path: 'search',
        loadComponent: () =>
          import('./layouts/routes/search/search.component').then(m => m.SearchComponent),
        outlet: 'content',
      },
      {
        path: 'reviews',
        loadComponent: () =>
          import('./layouts/routes/review/review.component').then(m => m.ReviewComponent),
        outlet: 'content',
      },
    ],
  },
  {
    path: 'down',
    loadComponent: () =>
      import('./layouts/routes/service-down/service-down.component').then(m => m.ServiceDownComponent),
  },
  {
    path: '**',
    loadComponent: () =>
      import('./layouts/components/not-found/not-found.component').then(m => m.NotFoundComponent),
  },
];
