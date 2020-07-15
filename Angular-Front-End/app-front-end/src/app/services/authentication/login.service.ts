import { Injectable, OnInit, Optional } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UserModel } from 'src/app/models/user.model';
// import { AngularFireAuth } from '@angular/fire/auth';
// import { auth } from 'firebase';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private signUpUrl = '/userCatalogApi/addUser';
  private currentUser: UserModel;
  private loggedInValue = false;

  private httpOptions = {
    headers: new HttpHeaders({ "Content-Type": "application/json" })
  }

  constructor(
    // private angularFireAuth: AngularFireAuth,
    protected http: HttpClient
  ) { }

  login(user: UserModel) {
    let data;
    this.http.post(this.signUpUrl, user).subscribe(response => {
      data = response;
    });
    // if user is present
    if(data.second != null) {
      this.loggedInValue = true;
    }
    return this.loggedInValue;
  }

  signUp(user: UserModel) {
    let data;
    this.http.post(this.signUpUrl, user).subscribe(response => {
      data = response;
    });
    // if user was created in the system
    if(data.second != null) {
      this.loggedInValue = true;
    }
    return this.loggedInValue;
  }

  getLoggedInUser() {
    return this.currentUser;
  }

  isLoggedIn() {
    return this.loggedInValue;
  }

  logout() {
    this.loggedInValue = false;
    this.currentUser = null;
  }

}
