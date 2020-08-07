import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AppRoutesService } from '../router/app-routes.service';


@Injectable({
    providedIn: 'root'
})
export class UserCommunityService {

    constructor(
        protected http: HttpClient,
        private appRoutes: AppRoutesService
      ) { }

    public subscribeToCommunity(uID: string, cID: string): any {
        return this.http.post(this.appRoutes.apiUserCommunitySubscribeEndPoint, {uID, cID});
    }

    public unsubscribeFromCommunity(uID: string, cID: string): any {
        return this.http.post(this.appRoutes.apiUserCommunityUnsubscribeEndPoint, {uID, cID});
    }

    public userSubbedCommunities(uID: string): any {
        return this.http.get(`${this.appRoutes.apiUserSubscribedCommunitiesEndPoint}/${uID}`);
    }
}




    