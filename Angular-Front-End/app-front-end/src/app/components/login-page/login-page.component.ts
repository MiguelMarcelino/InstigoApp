import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';
import { AppSettings } from 'src/app/appSettings';
import { Router, ActivatedRoute } from '@angular/router';
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
    private route: ActivatedRoute,
    private authenticationService: AuthenticationService
  ) {
    // redirect to home if already logged in
    if (this.authenticationService.currentUserValue) {
      this.router.navigate(['/homePage']);
    }
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      userName: ['', Validators.required],
      password: ['', Validators.required]
    });

    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
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
    this.authenticationService.login(this.form.userName.value, this.form.password.value)
      .pipe(first())
      .subscribe(
        (loginData: LoginModel) => {
          if (loginData.second !== null) {
            this.router.navigate([this.returnUrl]);
          }
          else {
            this.error = loginData.first;
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
