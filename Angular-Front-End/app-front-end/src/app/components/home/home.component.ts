import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';
import { AngularFireAuth } from '@angular/fire/auth';
import { AppSettings } from 'src/app/appSettings';
import { UserModel } from 'src/app/models/user.model';
import { Router } from '@angular/router';
import { Role } from 'src/app/models/role.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  currentUser: UserModel;
  appIconDirectory = AppSettings.APP_ICON_DIRECTORY;

  constructor(
    private authenticationService: AuthenticationService,
    private router: Router,
  ) {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
  }

  ngOnInit(): void {
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }

  hasPermission(): boolean {
    return (this.currentUser.role === Role.Editor || this.currentUser.role === Role.Admin) ? true : false;
  }

  isAdmin() {
    return (this.currentUser.role === Role.Admin);
  }
}
