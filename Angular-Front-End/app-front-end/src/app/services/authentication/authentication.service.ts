import { Injectable, OnInit, Optional } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable, BehaviorSubject } from 'rxjs';

import { UserModel } from 'src/app/models/user.model';
import { LoginModel } from 'src/app/models/login.model';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private signUpUrl = '/userCatalogApi/addUser';
  private loginUrl = '/userCatalogApi/login';
  private static USER_STORAGE_FIELD_NAME = "currentUser";

  private currentUserSubject: BehaviorSubject<any>;
  public currentUser: Observable<any>;

  private httpOptions = {
    headers: new HttpHeaders({ "Content-Type": "application/json" })
  }

  constructor(
    // private angularFireAuth: AngularFireAuth,
    protected http: HttpClient
  ) {
    this.currentUserSubject = new BehaviorSubject<any>(
      JSON.parse(localStorage.getItem(AuthenticationService.USER_STORAGE_FIELD_NAME)));
    console.log(localStorage.getItem(AuthenticationService.USER_STORAGE_FIELD_NAME));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue() {
    return this.currentUserSubject.value;
  }

  login(username: String, password: String) {
    return this.http.post<any>(this.loginUrl, { username, password })
      .pipe(map((loginData: LoginModel) => {
        // store user details and jwt token in local storage to keep user logged in between page refreshes
        // if(loginData.login_start_date) {
        localStorage.setItem(AuthenticationService.USER_STORAGE_FIELD_NAME, JSON.stringify(loginData.second));
        this.currentUserSubject.next(loginData.second);
        // }
        console.log(loginData);
        return loginData;
      }));
  }

  signUp(user: UserModel) {

  }

  logout() {
    // remove user from local storage and set current user to null
    localStorage.removeItem(AuthenticationService.USER_STORAGE_FIELD_NAME);
    this.currentUserSubject.next(null);
  }
}
