import {Component, inject} from '@angular/core';
import {MatDivider} from '@angular/material/divider';
import {MatIcon} from '@angular/material/icon';
import {MatIconButton} from '@angular/material/button';
import {AuthService} from '../../../shared/services/auth.service';
import {IftaLabel} from 'primeng/iftalabel';
import {FormsModule} from '@angular/forms';
import {InputText} from 'primeng/inputtext';
import {Button} from 'primeng/button';
import {MatDialogClose} from '@angular/material/dialog';

@Component({
  selector: 'app-profile',
  imports: [
    MatIconButton,
    MatDivider,
    MatIcon,
    IftaLabel,
    FormsModule,
    InputText,
    Button,
    MatDialogClose
  ],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {

  authService = inject(AuthService);
  usernameForm: any;
  oldPasswordForm: any;
  newPasswordForm: any;

}
