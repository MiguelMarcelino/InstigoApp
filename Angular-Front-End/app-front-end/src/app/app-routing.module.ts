import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CommunitiesListComponent } from './components/communities-list/communities-list.component';
import { HomeComponent } from './components/home/home.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { LoginPageComponent } from './components/login-page/login-page.component';


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
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
