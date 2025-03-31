import {Component, inject} from '@angular/core';
import {Avatar} from 'primeng/avatar';
import {MatDivider} from '@angular/material/divider';
import {MatIcon} from '@angular/material/icon';
import {MatIconButton} from '@angular/material/button';
import {ProfileService} from '../../services/profile.service';
import {AuthService} from '../../services/auth.service';

@Component({
  selector: 'app-profile',
  imports: [
    MatIconButton,
    Avatar,
    MatDivider,
    MatIcon
  ],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {

  profileService = inject(ProfileService);
  authService = inject(AuthService);

}
