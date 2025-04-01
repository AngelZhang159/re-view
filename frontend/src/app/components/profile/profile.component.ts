import {Component, inject} from '@angular/core';
import {Avatar} from 'primeng/avatar';
import {MatDivider} from '@angular/material/divider';
import {MatIcon} from '@angular/material/icon';
import {MatIconButton} from '@angular/material/button';
import {ProfileService} from '../../services/profile.service';
import {AuthService} from '../../services/auth.service';
import {NgOptimizedImage} from '@angular/common';
import {IftaLabel} from 'primeng/iftalabel';
import {FormsModule} from '@angular/forms';
import {InputText} from 'primeng/inputtext';
import {ButtonGroup} from 'primeng/buttongroup';
import {Button} from 'primeng/button';

@Component({
  selector: 'app-profile',
  imports: [
    MatIconButton,
    Avatar,
    MatDivider,
    MatIcon,
    NgOptimizedImage,
    IftaLabel,
    FormsModule,
    InputText,
    ButtonGroup,
    Button
  ],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {

  profileService = inject(ProfileService);
  authService = inject(AuthService);
  usernameForm: any;
  oldPasswordForm: any;
  newPasswordForm: any;

}
