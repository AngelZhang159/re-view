import {Routes} from '@angular/router';
import {homeGuard} from './guards/home.guard';
import {authGuard} from './guards/auth.guard';

export const routes: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./components/landing/landing.component').then(m => m.LandingComponent),
    canActivate: [homeGuard],
  },
  {
    path: 'login',
    loadComponent: () =>
      import('./components/login/login.component').then(m => m.LoginComponent),
  },
  {
    path: 'register',
    loadComponent: () =>
      import('./components/register/register.component').then(m => m.RegisterComponent),
  },
  {
    path: 'app',
    loadComponent: () =>
      import('./components/body/body.component').then(m => m.BodyComponent),
    canActivate: [authGuard],
    children: [
      {
        path: '',
        loadComponent: () =>
          import('./components/main/main.component').then(m => m.MainComponent),
        outlet: 'content',
      },
      {
        path: 'details/:type/:id',
        loadComponent: () =>
          import('./components/details/details.component').then(m => m.DetailsComponent),
        outlet: 'content',
      },
      {
        path: 'search',
        loadComponent: () =>
          import('./components/search/search.component').then(m => m.SearchComponent),
        outlet: 'content',
      },
      {
        path: 'reviews',
        loadComponent: () =>
          import('./components/review/review.component').then(m => m.ReviewComponent),
        outlet: 'content',
      },
    ],
  },
  {
    path: '**',
    loadComponent: () =>
      import('./components/not-found/not-found.component').then(m => m.NotFoundComponent),
  },
];
