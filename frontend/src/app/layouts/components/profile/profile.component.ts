import {Component, inject} from '@angular/core';
import {MatDivider} from '@angular/material/divider';
import {MatIcon} from '@angular/material/icon';
import {MatIconButton} from '@angular/material/button';
import {AuthService} from '../../../core/services/auth.service';
import {IftaLabel} from 'primeng/iftalabel';
import {FormsModule} from '@angular/forms';
import {InputText} from 'primeng/inputtext';
import {Button} from 'primeng/button';
import {MatDialogClose, MatDialogRef} from '@angular/material/dialog';
import {Router} from '@angular/router';

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

  constructor(public dialogRef: MatDialogRef<ProfileComponent>) {
  }

  authService = inject(AuthService);
  router = inject(Router)
  usernameForm: any;
  oldPasswordForm: any;
  newPasswordForm: any;

  logOut() {
    this.authService.logOut()
    this.router.navigate(["/"])
    this.dialogRef.close()
  }
}
