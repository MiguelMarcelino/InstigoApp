import { TemplateControllerService } from './template-controller.service';
import { EventModel } from 'src/app/models/event.model';
import { HttpClient } from '@angular/common/http';
import { AppRoutesService } from '../router/app-routes.service';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EventService extends TemplateControllerService<EventModel> {

    constructor(
      protected http: HttpClient,
      private appRoutes: AppRoutesService
    ) {
      super(http);
    }

    protected getApiUrlAll() {
        return this.appRoutes.apiEventsEndPoint;
    }
    protected getApiUrlObject() {
        return this.appRoutes.apiEventEndPoint;
    }
}