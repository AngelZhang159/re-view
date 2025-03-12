import {Routes} from '@angular/router';
import {HomeComponent} from './components/home/home.component';
import {LoginComponent} from './components/login/login.component';
import {MainAppComponent} from './components/main-app/main-app.component';
import {RegisterComponent} from './components/register/register.component';

export const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'app',
    component: MainAppComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  }
];
