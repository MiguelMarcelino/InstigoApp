import { BrowserModule } from '@angular/platform-browser';
import { NgModule, APP_INITIALIZER } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CommunitiesListComponent } from './components/communities-list/communities-list.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeComponent } from './components/home/home.component';
import { LoginPageComponent } from './components/login-page/login-page.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from 'src/shared/material.module';
import { environment } from '../environments/environment'; 
import { fakeBackendProvider } from './_helpers';
import { SignUpPageComponent } from './components/sign-up-page/sign-up-page.component';
import { EventsUserComponent } from './components/events-user/events-user.component';
import { JwtInterceptor } from './services/authentication/jwt.interceptor';
import { ErrorInterceptor } from './services/authentication/error.interceptor';
import { HomePageAfterLoginComponent } from './components/home-page-after-login/home-page-after-login.component';
import { ContactsComponent } from './components/contacts/contacts.component';
import { UserProfilePageComponent } from './components/user-profile-page/user-profile-page.component';
import { AppRoutesService } from './services/router/app-routes.service';
import { CreateCommunityComponent } from './components/editors/create-community/create-community.component';
import { CreateEventsComponent } from './components/editors/create-events/create-events.component';
import { MatInputModule } from '@angular/material/input';
import { NotFoundPageComponent } from './components/not-found-page/not-found-page.component';

@NgModule({
  declarations: [
    AppComponent,
    CommunitiesListComponent,
    HomeComponent,
    LoginPageComponent,
    HomePageComponent,
    SignUpPageComponent,
    EventsUserComponent,
    HomePageAfterLoginComponent,
    ContactsComponent,
    UserProfilePageComponent,
    CreateCommunityComponent,
    CreateEventsComponent,
    NotFoundPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MaterialModule,
    MatInputModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
  ],
  providers: [
    fakeBackendProvider,
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
    {
      provide: APP_INITIALIZER,
      multi: true,
      deps: [AppRoutesService],
      useFactory: (appRoutesService: AppRoutesService) => {
        return () => {
          // Make sure to return a promise!
          return appRoutesService.loadAppConfig();
        };
      }
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
