import {Routes} from '@angular/router';
import {homeGuard} from './guards/home.guard';
import {authGuard} from './guards/auth.guard';

export const routes: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./core/components/landing/landing.component').then(m => m.LandingComponent),
    canActivate: [homeGuard],
  },
  {
    path: 'login',
    loadComponent: () =>
      import('./core/components/login/login.component').then(m => m.LoginComponent),
  },
  {
    path: 'register',
    loadComponent: () =>
      import('./core/components/register/register.component').then(m => m.RegisterComponent),
  },
  {
    path: 'app',
    loadComponent: () =>
      import('./core/components/body/body.component').then(m => m.BodyComponent),
    canActivate: [authGuard],
    children: [
      {
        path: '',
        loadComponent: () =>
          import('./core/components/main/main.component').then(m => m.MainComponent),
        outlet: 'content',
      },
      {
        path: 'details/:type/:id',
        loadComponent: () =>
          import('./core/components/details/details.component').then(m => m.DetailsComponent),
        outlet: 'content',
      },
      {
        path: 'search',
        loadComponent: () =>
          import('./core/components/search/search.component').then(m => m.SearchComponent),
        outlet: 'content',
      },
      {
        path: 'reviews',
        loadComponent: () =>
          import('./core/components/review/review.component').then(m => m.ReviewComponent),
        outlet: 'content',
      },
    ],
  },
  {
    path: '**',
    loadComponent: () =>
      import('./core/components/not-found/not-found.component').then(m => m.NotFoundComponent),
  },
];
