import {Component, inject} from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-main-app',
  imports: [],
  templateUrl: './main-app.component.html',
  styleUrl: './main-app.component.css'
})
export class MainAppComponent {
  authService = inject(AuthService)
  route = inject(Router)

  logOut() {
    this.authService.logOut()
    this.route.navigate(["/"])
  }
}
