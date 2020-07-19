import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { AngularFireAuth } from '@angular/fire/auth';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';
import { AppSettings } from 'src/app/appSettings';
import { UserModel } from 'src/app/models/user.model';
import { Router } from '@angular/router';
import { first } from 'rxjs/operators';
import { LoginModel } from 'src/app/models/login.model';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent implements OnInit {

  serverName = AppSettings.APP_NAME;
  googlePhoto = "https://cdn.freebiesupply.com/logos/large/2x/google-icon-logo-png-transparent.png"
  loginForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  error: string;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private authenticationService: AuthenticationService
  ) {
    // redirect to home if already logged in
    if (this.authenticationService.currentUserValue) {
      this.router.navigate(['/homePage']);
    }
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  // easy access to form fields
  get form() {
    return this.loginForm.controls;
  }

  onSubmitLogin() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.form.invalid) {
      return;
    }

    this.loading = true;
    this.authenticationService.login(this.form.username.value, this.form.password.value)
      .pipe(first())
      .subscribe(
        (loginData: LoginModel) => {
          // check if token is still valid
          if (loginData.user.dateLogin) {
            this.router.navigate([this.returnUrl]);
          }
          else {
            this.error = loginData.msg;
            this.loading = false;
          }
        },
        error => {
          this.error = error;
          this.loading = false;
        }
      );
  }
}
