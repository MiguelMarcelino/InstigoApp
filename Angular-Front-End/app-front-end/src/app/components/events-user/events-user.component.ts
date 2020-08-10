import { Component, OnInit } from '@angular/core';
import { EventModel } from 'src/app/models/event.model';
import { UserEventControllerService } from 'src/app/services/controllers/user-event-controller.service';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';
import { UserModel } from 'src/app/models/user.model';
import { Role } from 'src/app/models/role.model';
import { EventService } from 'src/app/services/controllers/events-controller.service';

@Component({
  selector: 'app-events-user',
  templateUrl: './events-user.component.html',
  styleUrls: ['./events-user.component.scss']
})
export class EventsUserComponent implements OnInit {

  currentUser: UserModel;
  eventList: EventModel[];
  eventsCheck: boolean = true;

  constructor(
    private authenticationService: AuthenticationService,
    private userEventService: UserEventControllerService,
    private eventService: EventService
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
      if(this.eventList.length === 0) {
        this.eventsCheck = false;
      }
    });
  }

  isEventCreatorOrAdmin(event: EventModel) {
    return ((this.currentUser.userName === event.ownerUserName) || (this.currentUser.role === Role.Admin))
  }

  deleteEvent(event) {
    this.eventService.deleteObject(event).subscribe((response) => {
      // TODO: handle response
    })
    const index: number = this.eventList.indexOf(event);
    if(index !== -1){
      this.eventList.splice(index, 1);
    }
  }
}
