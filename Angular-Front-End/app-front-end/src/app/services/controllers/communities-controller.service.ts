import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TemplateControllerService } from './template-controller.service';
import { CommunityModel } from 'src/app/models/community.model';
import { AppRoutesService } from '../router/app-routes.service';

@Injectable({
  providedIn: 'root'
})
export class CommunitiesService extends TemplateControllerService<CommunityModel>{

  constructor(
    protected http: HttpClient,
    private appRoutes: AppRoutesService
  ) {
    super(http);
  }

  // Abstract class methods
  public getApiUrlAll() {
    return this.appRoutes.apiCommunitiesEndPoint;
  }

  public getApiUrlObject() {
    return this.appRoutes.apiCommunityEndPoint;
  }

}
