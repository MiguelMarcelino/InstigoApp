import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { AngularFireAuth } from '@angular/fire/auth';
import { LoginService } from 'src/app/services/authentication/login.service';
import { AppSettings } from 'src/app/appSettings';
import { UserModel } from 'src/app/models/user.model';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent implements OnInit {
  
  // app user
  user: UserModel;
  // check if user is logged in
  isLoggedIn = false;
  //google Icon
  googlePhoto = "https://cdn.freebiesupply.com/logos/large/2x/google-icon-logo-png-transparent.png"
  //login form
  loginForm: FormGroup;
  //sign up form
  signUpForm: FormGroup;
  //app name
  serverName = AppSettings.APP_NAME;
  //Change between sign up and login
  signUpValue = false;

  constructor(
    private service: LoginService,
    private angularFireAuth: AngularFireAuth,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {
    this.user = this.service.getLoggedInUser();
    this.loginForm = this.formBuilder.group({
      email: new FormControl(''),
      password: new FormControl(''),
    });
    this.signUpForm = this.formBuilder.group({
      userName: new FormControl(''),
      email: new FormControl(''),
      password: new FormControl(''),
    });
  }

  onSubmitSignUp() {
    let user = {
      'userName': this.signUpForm.get('userName').value, 
      'email': this.signUpForm.get('email').value,
      'password': this.signUpForm.get('password').value,
    };
    this.isLoggedIn = this.service.signUp(user);
  }

  onSubmitLogin() {
    let user = {
      'userName': "", 
      'email': this.signUpForm.get('email').value,
      'password': this.signUpForm.get('password').value,
    };
    this.isLoggedIn = this.service.login(user);
  }

  signUp() {
    this.signUpValue = !this.signUpValue;
  }
}
