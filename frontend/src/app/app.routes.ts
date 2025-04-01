import {Routes} from '@angular/router';
import {HomeComponent} from './components/home/home.component';
import {LoginComponent} from './components/login/login.component';
import {MainAppComponent} from './components/main-app/main-app.component';
import {RegisterComponent} from './components/register/register.component';
import {homeGuard} from './guards/home.guard';
import {DetailsComponent} from './components/details/details.component';
import {NotFoundComponent} from './components/not-found/not-found.component';
import {SearchComponent} from './components/search/search.component';
import {ContentComponent} from './components/content/content.component';
import {authGuard} from './guards/auth.guard';

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
    canActivate: [authGuard],
    children: [
      {
        path: '',
        component: ContentComponent,
        outlet: 'content'
      },
      {
        path: 'details/:type/:id',
        component: DetailsComponent,
        outlet: 'content'
      },
      {
        path: 'search',
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
