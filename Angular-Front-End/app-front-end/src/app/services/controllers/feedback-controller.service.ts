import { FeedbackModel } from 'src/app/models/feedback.model';
import { Injectable } from '@angular/core';
import { TemplateControllerService } from './template-controller.service';
import { HttpClient } from '@angular/common/http';
import { AppRoutesService } from '../router/app-routes.service';

@Injectable({
    providedIn: 'root'
})
export class FeedbackService extends TemplateControllerService<FeedbackModel> {

    constructor(
        protected http: HttpClient,
        private appRoutes: AppRoutesService
    ) {
        super(http);
    }

    // For later
    protected getApiUrlAll() {
        throw new Error("Method not implemented.");
    }

    // For later
    protected getApiUrlObject() {
        throw new Error("Method not implemented.");
    }
  
    public sendFeedback(username: string, feedback: string) {
        return this.http.post(this.appRoutes.apiUserFeedbackSend, {'username': username, 'feedback': feedback});
    }
}