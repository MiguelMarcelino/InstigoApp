import { Component, OnInit } from '@angular/core';
import { AppSettings } from 'src/app/appSettings';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';
import { first } from 'rxjs/operators';
import { LoginModel } from 'src/app/models/login.model';

@Component({
  selector: 'app-sign-up-page',
  templateUrl: './sign-up-page.component.html',
  styleUrls: ['./sign-up-page.component.scss']
})
export class SignUpPageComponent implements OnInit {

  serverName = AppSettings.APP_NAME;
  signUpForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  error: string;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private authenticationService: AuthenticationService
  ) { }

  ngOnInit(): void {
    this.signUpForm = this.formBuilder.group({
      userName: ['', Validators.required],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', Validators.required],
      passwordRetyped: ['', Validators.required]
    });
    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  // easy access to form fields
  get form() {
    return this.signUpForm.controls;
  }

  onSubmitSignUp() {
    this.submitted = true;

    if (this.form.invalid) {
      return;
    }

    if(this.signUpForm.get('password').value !== 
      this.signUpForm.get('passwordRetyped').value) {
        return;
    }

    let user = {
      'userName': this.signUpForm.get('userName').value,
      'firstName': this.signUpForm.get('firstName').value,
      'lastName': this.signUpForm.get('lastName').value,
      'email': this.signUpForm.get('email').value,
      'password': this.signUpForm.get('password').value,
    };

    this.loading = true;

    this.authenticationService.signUp(user)
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
          console.log(loginData.first);
        },
        error => {
          this.error = error;
          this.loading = false;
        }
      );
  }
}
