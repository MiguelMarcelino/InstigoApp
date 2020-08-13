import { Component, OnInit } from '@angular/core';
import { EventModel } from 'src/app/models/event.model';
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
  subscribedCommunitiesEvents: EventModel[];
  createdEvents: EventModel[];
  subscribedEventsCheck: boolean = true;
  createdEventsCheck: boolean = true;

  constructor(
    private authenticationService: AuthenticationService,
    private eventService: EventService
  ) { 
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
  }

  ngOnInit(): void {
    this.getUserEventList();
    this.getListCreatedEvents();
  }

  getUserEventList(): void {
    this.eventService.getEventsFromUser(this.currentUser.id).subscribe((eventsFromUser) => {
      this.subscribedCommunitiesEvents = eventsFromUser.second.listEvents;
      if(this.subscribedCommunitiesEvents ) {
        if(this.subscribedCommunitiesEvents.length === 0){
          this.subscribedEventsCheck = false;
        }
      } else {
        this.subscribedEventsCheck = false;
      }
    });
  }

  getListCreatedEvents(): void {
    this.eventService.getCreatedEvents(this.currentUser.userName).subscribe((createdEvents) => {
      this.createdEvents = createdEvents.second.listEvents;
      if(this.createdEvents) {
        if(this.createdEvents.length === 0){
          this.createdEventsCheck = false;
        }
      } else {
        this.createdEventsCheck = false;
      }
    })
  }

  isEventCreatorOrAdmin(event: EventModel) {
    return ((this.currentUser.userName === event.ownerUserName) || (this.currentUser.role === Role.Admin))
  }

  isAllowed():boolean {
    return ((this.currentUser.role === Role.Admin) || (this.currentUser.role === Role.Editor));
  }

  deleteEvent(event) {
    this.eventService.deleteObject(event).subscribe((response) => {
      // TODO: handle response
    })
    const index: number = this.subscribedCommunitiesEvents.indexOf(event);
    if(index !== -1){
      this.subscribedCommunitiesEvents.splice(index, 1);
    }
  }

}
