import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class AppRoutesService {
    private appRoutes: any;
    private ROUTES_FILE_PATH: string = "/assets/routes/appRoutes.json";

    constructor(
        private http: HttpClient
    ) { }

    loadAppConfig(): Promise<void> {
        return this.http.get(this.ROUTES_FILE_PATH)
            .toPromise()
            .then(data => {
                this.appRoutes = data;
            }
        );
    }

    // Communities
    get apiCommunitiesEndPoint(): string {
        this.isLoaded();
        return this.appRoutes.communitiesCatalogApi;
    }

    get apiCommunityEndPoint(): string {
        this.isLoaded();
        return this.appRoutes.communityCatalogApi;
    }

    get apiUserCommunitySubscribeEndPoint(): string {
        this.isLoaded();
        return this.appRoutes.userCommunityApiSubscribe;
    }

    get apiUserCommunityUnsubscribeEndPoint(): string {
        this.isLoaded();
        return this.appRoutes.userCommunityApiUnsubscribe;
    }

    get apiUserSubscribedCommunitiesEndPoint(): string {
        this.isLoaded();
        return this.appRoutes.userCommunityApiSubscribedCommunities;
    }

    get apiUserCreatedCommunitiesEndPoint(): string{
        this.isLoaded();
        return this.appRoutes.userCommunityApiCreatedCommunities;
    }

    // Events
    get apiUserEventsEndPoint(): string {
        this.isLoaded();
        return this.appRoutes.userEventApiAllEvents;
    }

    get apiEventsEndPoint(): string {
        this.isLoaded();
        return this.appRoutes.eventsCatalogApi;
    }

    get apiEventEndPoint(): string {
        this.isLoaded();
        return this.appRoutes.eventCatalogApi;
    }

    // User
    get apiUserCatalogLogin(): string {
        this.isLoaded();
        return this.appRoutes.userCatalogApiLogin;
    }

    get apiUserCatalogAddUser(): string {
        this.isLoaded();
        return this.appRoutes.userCatalogApiAddUser;
    }

    get apiUserFeedbackSend(): string {
        this.isLoaded();
        return this.appRoutes.userFeedbackSendFeedback;
    }

    // Check if routes file was loaded
    private isLoaded() {
        if (!this.appRoutes) {
            throw Error('No routes where loaded.');
        }
    }

}