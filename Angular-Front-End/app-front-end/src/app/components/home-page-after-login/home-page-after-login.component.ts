import { Component, OnInit } from '@angular/core';
import { UserModel } from 'src/app/models/user.model';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';

@Component({
  selector: 'app-home-page-after-login',
  templateUrl: './home-page-after-login.component.html',
  styleUrls: ['./home-page-after-login.component.scss']
})
export class HomePageAfterLoginComponent implements OnInit {

  currentUser: UserModel;
  
  constructor(
    private authenticationService: AuthenticationService
  ) { }

  ngOnInit(): void {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
  }

}
