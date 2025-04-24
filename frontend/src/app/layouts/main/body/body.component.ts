import {Component, inject} from '@angular/core';
import {AuthService} from '../../../core/services/auth.service';
import {Router, RouterOutlet} from '@angular/router';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatSidenav, MatSidenavContainer, MatSidenavContent} from '@angular/material/sidenav';
import {MenuService} from '../../../core/services/menu.service';
import {MenuComponent} from '../../routes/menu/menu.component';

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
  templateUrl: './body.component.html',
  styleUrl: './body.component.css'
})
export class BodyComponent {

  authService = inject(AuthService)
  menuService = inject(MenuService)

  route = inject(Router)

  logOut() {
    this.authService.logOut()
    this.route.navigate(["/"])
  }
}
