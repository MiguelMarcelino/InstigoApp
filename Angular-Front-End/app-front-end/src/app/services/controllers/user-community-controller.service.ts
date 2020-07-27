import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';


@Injectable({
    providedIn: 'root'
})
export class UserCommunityService {

    private httpOptions = {
        headers: new HttpHeaders({ "Content-Type": "application/json" })
    }
    private subscribeToCommunityUrl = '/userCommunityApi/subscribeToCommunity'

    constructor(
        protected http: HttpClient
      ) { }

    public subscribeToCommunity(uID: string, cID: string) {
        return this.http.post(this.subscribeToCommunityUrl, {uID, cID});
    }
}




    