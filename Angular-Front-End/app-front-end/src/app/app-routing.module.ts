import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CommunitiesListComponent } from './components/communities-list/communities-list.component';
import { HomeComponent } from './components/home/home.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { LoginPageComponent } from './components/login-page/login-page.component';
import { SignUpPageComponent } from './components/sign-up-page/sign-up-page.component';
import { EventsUserComponent } from './components/events-user/events-user.component';
import { AuthGuard } from './services/authentication/auth-guard.service';


const routes: Routes = [
  {
    path: '', 
    redirectTo: 'homePage',
    pathMatch: 'full'
  },
  {
    path: "home",
    component: HomeComponent
  },
  {
    path: "homePage",
    component: HomePageComponent
  },
  {
    path: "communities",
    component: CommunitiesListComponent
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
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
