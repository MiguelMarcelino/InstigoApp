import { Component, OnInit } from '@angular/core';
import { AppSettings } from 'src/app/appSettings';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';

@Component({
  selector: 'app-sign-up-page',
  templateUrl: './sign-up-page.component.html',
  styleUrls: ['./sign-up-page.component.scss']
})
export class SignUpPageComponent implements OnInit {

  serverName = AppSettings.APP_NAME;
  signUpForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private authenticationService: AuthenticationService
  ) { }

  ngOnInit(): void {
    this.signUpForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  onSubmitSignUp() {
    let user = {
      'userName': this.signUpForm.get('userName').value,
      'email': this.signUpForm.get('email').value,
      'password': this.signUpForm.get('password').value,
    };
  }
}
