import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppRoutesService } from '../router/app-routes.service';

@Injectable({
    providedIn: "root"
})
export class UserEventControllerService {

    constructor(
        protected http: HttpClient,
        private appRoutes: AppRoutesService
    ) {
    }

    getEventsFromUser(id: string): Observable<any> {
        let url = `${this.appRoutes.apiUserEventsEndPoint}/${id}`
        return this.http.get(url);
    }

}
