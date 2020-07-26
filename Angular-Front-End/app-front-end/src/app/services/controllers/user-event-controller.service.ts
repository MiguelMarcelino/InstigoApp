import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: "root"
})
export class UserEventControllerService {

    private httpOptions = {
        headers: new HttpHeaders({ "Content-Type": "application/json" })
    }

    constructor(
        protected http: HttpClient
    ) {
    }

    private getEventsByUserId = '/userEventApi/eventsFromSubbedCommunities';

    getEventsFromUser(id: string): Observable<any> {
        let url = `${this.getEventsByUserId}/${id}`
        return this.http.get(url);
    }

}
