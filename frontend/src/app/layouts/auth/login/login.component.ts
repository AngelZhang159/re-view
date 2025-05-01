import {Component, inject} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {AuthService} from '../../../core/services/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  private fb = inject(FormBuilder)
  private auth = inject(AuthService)
  private router = inject(Router)

  errorMsg = ""

  loginForm: FormGroup = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required]],
    rememberMe: [false]
  })

  onSubmit(): void {
    if (this.loginForm.valid) {
      const loginData = this.loginForm.value;

      this.auth.login(loginData.email, loginData.password, loginData.rememberMe).subscribe({
        next: user => {
          this.router.navigate(["/app"]).then(() => console.log("User logged in successfully: " + user))
        },
        error: err => {
          alert("ERROR: " + err.error.message)
        }
      })
    }
  }

  getError(controlName: string): string | null {
    const control = this.loginForm.get(controlName);
    if (control?.touched && control.errors) {
      if (control.errors['required']) return 'This field is required';
      if (control.errors['email']) return 'Invalid email';
    }
    return null;
  }
}
