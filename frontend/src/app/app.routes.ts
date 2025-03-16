import {Routes} from '@angular/router';
import {HomeComponent} from './components/home/home.component';
import {LoginComponent} from './components/login/login.component';
import {MainAppComponent} from './components/main-app/main-app.component';
import {RegisterComponent} from './components/register/register.component';
import {authGuard} from './guards/auth.guard'
import {homeGuard} from './guards/home.guard';

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
    path: 'app',
    component: MainAppComponent,
    canActivate: [authGuard]
  },
  {
    path: 'register',
    component: RegisterComponent
  }
];
