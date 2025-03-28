import {Component, effect, inject} from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {Router, RouterOutlet} from '@angular/router';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatSidenav, MatSidenavContainer, MatSidenavContent} from '@angular/material/sidenav';
import {MenuService} from '../../services/menu.service';
import {MenuComponent} from '../menu/menu.component';

@Component({
  selector: 'app-main-app',
  imports: [
    FormsModule,
    ReactiveFormsModule,
    MatSidenavContainer,
    MatSidenavContent,
    MatSidenav,
    MenuComponent,
    RouterOutlet,
  ],
  templateUrl: './main-app.component.html',
  styleUrl: './main-app.component.css'
})
export class MainAppComponent {

  authService = inject(AuthService)
  menuService = inject(MenuService)

  route = inject(Router)
  opened = true;


  constructor() {
    effect(() => {
      this.opened = this.menuService.opened();
    })
  }


  logOut() {
    this.authService.logOut()
    this.route.navigate(["/"])
  }
}
