import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/authentication/login.service';
import { AngularFireAuth } from '@angular/fire/auth';
import { AppSettings } from 'src/app/appSettings';
import { UserModel } from 'src/app/models/user.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  // app user
  user: UserModel;
  // app title
  title = AppSettings.APP_NAME;

  constructor(
    private service: LoginService,
    private angularFireAuth: AngularFireAuth,
  ) { }

  ngOnInit(): void {
    // this.service.getLoggedInUser()
    //   .subscribe (user => {
    //     this.user = user;
    //   })
    this.user = this.service.getLoggedInUser();
  }

  logout(): void {
    this.service.logout();
  }

}
