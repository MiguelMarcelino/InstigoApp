import { Component, OnInit } from '@angular/core';
import { EventModel } from 'src/app/models/event.model';
import { UserEventControllerService } from 'src/app/services/controllers/user-event-controller.service';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';
import { UserModel } from 'src/app/models/user.model';

@Component({
  selector: 'app-events-user',
  templateUrl: './events-user.component.html',
  styleUrls: ['./events-user.component.scss']
})
export class EventsUserComponent implements OnInit {

  currentUser: UserModel;
  eventList: EventModel[];

  constructor(
    private authenticationService: AuthenticationService,
    private userEventService: UserEventControllerService
  ) { 
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
  }

  ngOnInit(): void {
    this.getUserEventList();
  }

  getCurrentUserId(): string {
    return this.currentUser.id;
  }

  getUserEventList(): void {
    this.userEventService.getEventsFromUser(this.getCurrentUserId()).subscribe((eventsFromUser) => {
      this.eventList = eventsFromUser.second.listEvents;
    });
  }

}
