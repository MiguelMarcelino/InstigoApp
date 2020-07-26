import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';
import { UserModel } from 'src/app/models/user.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit {

  currentUser: UserModel;

  constructor(
    private authenticationService: AuthenticationService,
    private router: Router
    ) {
      this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
    }

  ngOnInit(): void {
    if(this.currentUser) {
      this.router.navigate(['homePage']);
    }
  }

}
