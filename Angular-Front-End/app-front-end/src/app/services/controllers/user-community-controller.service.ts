import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';


@Injectable({
    providedIn: 'root'
})
export class UserCommunityService {

    private httpOptions = {
        headers: new HttpHeaders({ "Content-Type": "application/json" })
    }
    private subscribeToCommunityUrl = '/userCommunityApi/subscribeToCommunity';
    private unsubscribeFromCommunityUrl = '/userCommunityApi/unsubscribeFromCommunity';
    private userSubbedCommunitiesUrl = '/userCommunityApi/userSubbedCommunities';

    constructor(
        protected http: HttpClient
      ) { }

    public subscribeToCommunity(uID: string, cID: string): any {
        return this.http.post(this.subscribeToCommunityUrl, {uID, cID});
    }

    public unsubscribeFromCommunity(uID: string, cID: string): any {
        return this.http.post(this.unsubscribeFromCommunityUrl, {uID, cID});
    }

    public userSubbedCommunities(uID: string): any {
        return this.http.get(`${this.userSubbedCommunitiesUrl}/${uID}`);
    }
}




    