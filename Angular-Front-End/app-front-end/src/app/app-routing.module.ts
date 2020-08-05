import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CommunitiesListComponent } from './components/communities-list/communities-list.component';
import { HomeComponent } from './components/home/home.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { LoginPageComponent } from './components/login-page/login-page.component';
import { SignUpPageComponent } from './components/sign-up-page/sign-up-page.component';
import { EventsUserComponent } from './components/events-user/events-user.component';
import { AuthGuard } from './services/authentication/auth-guard.service';
import { HomePageAfterLoginComponent } from './components/home-page-after-login/home-page-after-login.component';
import { ContactsComponent } from './components/contacts/contacts.component';
import { UserProfilePageComponent } from './components/user-profile-page/user-profile-page.component';


const routes: Routes = [
  {
    path: '', 
    redirectTo: 'welcome',
    pathMatch: 'full'
  },
  {
    path: "home",
    component: HomeComponent
  },
  {
    path: "welcome",
    component: HomePageComponent
  },
  {
    path: "homePage",
    component: HomePageAfterLoginComponent,
    canActivate: [AuthGuard]
  },
  {
    path: "communities",
    component: CommunitiesListComponent,
    canActivate: [AuthGuard]
  },
  {
    path: "login",
    component: LoginPageComponent
  },
  {
    path: "signup",
    component: SignUpPageComponent
  },
  {
    path: "events",
    component: EventsUserComponent,
    canActivate: [AuthGuard]
  },
  {
    path: "contacts",
    component: ContactsComponent
  },
  {
    path: "userProfile",
    component: UserProfilePageComponent,
    canActivate: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
