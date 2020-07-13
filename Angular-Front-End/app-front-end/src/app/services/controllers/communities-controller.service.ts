import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TemplateControllerService } from './template-controller.service';
import { CommunityModel } from 'src/app/models/community.model';

@Injectable({
  providedIn: 'root'
})
export class CommunitiesService extends TemplateControllerService<CommunityModel>{

  constructor(
    protected http: HttpClient
  ) {
    super(http);
  }

  private communitiesUrl = '/communityCatalogApi/communityCatalog/communities';
  private getCommunityById = '/communityCatalogApi/community/';

  // Abstract class methods
  public getApiUrlAll() {
    return this.communitiesUrl;
  }

  public getApiUrlObject() {
    return this.getCommunityById;
  }

}
