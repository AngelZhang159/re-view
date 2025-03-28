import {Routes} from '@angular/router';
import {HomeComponent} from './components/home/home.component';
import {LoginComponent} from './components/login/login.component';
import {MainAppComponent} from './components/main-app/main-app.component';
import {RegisterComponent} from './components/register/register.component';
import {authGuard} from './guards/auth.guard'
import {homeGuard} from './guards/home.guard';
import {DetailsComponent} from './components/details/details.component';
import {NotFoundComponent} from './components/not-found/not-found.component';
import {SearchComponent} from './components/search/search.component';

export const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    canActivate: [homeGuard]
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'app',
    component: MainAppComponent,
    // canActivate: [authGuard],
    children: [
      {
        path: 'details/:type/:id',
        component: DetailsComponent,
        outlet: 'content'
      },
      {
        path: 'search:query',
        component: SearchComponent,
        outlet: 'content'
      },
    ]
  },
  {
    path: '**',
    component: NotFoundComponent
  }
];
