import {Component, inject} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AbstractControl, FormBuilder, ReactiveFormsModule, ValidationErrors, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthService} from '../../../core/services/auth.service';

@Component({
  selector: 'app-register',
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  http = inject(HttpClient)
  fb = inject(FormBuilder)
  router = inject(Router)
  authService = inject(AuthService)

  registerForm = this.fb.group({
    username: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, this.passwordValidator]],
    repeatPassword: ['', [Validators.required, this.matchPasswordValidator.bind(this)]]
  })

  matchPasswordValidator(control: AbstractControl): ValidationErrors | null {
    const password = this.registerForm?.get('password')?.value;
    const repeatPassword = control.value;
    return password === repeatPassword ? null : {passwordMismatch: true};
  }

  passwordValidator(control: AbstractControl): ValidationErrors | null {
    const password = control.value;
    const hasNumber = /\d/.test(password);
    const hasLettersCase = /[a-zA-Z]/.test(password);
    const hasMinLength = password.length >= 8;

    const valid = hasNumber && hasLettersCase && hasMinLength;
    return valid ? null : {invalidPassword: true};
  }

  onSubmit() {
    console.log("Sending form: ", this.registerForm.value)
    this.authService.register(this.registerForm.value.username!, this.registerForm.value.email!, this.registerForm.value.password!).subscribe({
      next: (async (response) => {
        console.log("Register successful: ", response)
        await this.router.navigate(["/login"])
      }),
      error: ((err) => {
        console.log("Registration failed, error: ", err.error.message)
      })
    })
  }

  getError(controlName: string): string | null {
    const control = this.registerForm.get(controlName);
    if (control?.touched && control.errors) {
      if (control.errors['required']) return 'This field is required';
      if (control.errors['email']) return 'Invalid email';
      if (control.errors['minlength']) return `Minimum ${control.errors['minlength'].requiredLength} characters`;
      if (control.errors['invalidPassword']) return `Password doesn't meet conditions: 8 characters, 1 uppercase, 1 lowercase, 1 number`
      if (control.errors['passwordMismatch']) return `Passwords don't match`
    }
    return null;
  }
}
