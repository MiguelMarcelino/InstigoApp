import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TemplateControllerService } from './template-controller.service';
import { CommunityModel } from 'src/app/models/community.model';
import { AppRoutesService } from '../router/app-routes.service';
import { Observable } from 'rxjs';

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

  public getCommunityLists(uID: string): Observable<any> {
    return this.http.get(`${this.appRoutes.apiCommunityMainInfo}/${uID}`);
  }

  public subscribeToCommunity(uID: string, cID: string): Observable<any> {
    return this.http.post(this.appRoutes.apiUserCommunitySubscribeEndPoint, {uID, cID});
  }

  public unsubscribeFromCommunity(uID: string, cID: string): Observable<any> {
    return this.http.post(this.appRoutes.apiUserCommunityUnsubscribeEndPoint, {uID, cID});
  }

  public userSubbedCommunities(uID: string): Observable<any> {
    return this.http.get(`${this.appRoutes.apiUserSubscribedCommunitiesEndPoint}/${uID}`);
  }

  public userCreatedCommunities(ownerUserName: string): Observable<any> {
    return this.http.get(`${this.appRoutes.apiUserCreatedCommunitiesEndPoint}/${ownerUserName}`);
  }

}
