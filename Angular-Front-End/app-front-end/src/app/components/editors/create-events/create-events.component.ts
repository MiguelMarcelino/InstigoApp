import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CommunityModel } from 'src/app/models/community.model';
import { EventService } from 'src/app/services/controllers/events-controller.service';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';
import { UserModel } from 'src/app/models/user.model';
import { first } from 'rxjs/operators';
import { CommunitiesService } from 'src/app/services/controllers/communities-controller.service';

@Component({
  selector: 'app-create-events',
  templateUrl: './create-events.component.html',
  styleUrls: ['./create-events.component.scss']
})
export class CreateEventsComponent implements OnInit {
  error: string;
  response: string;
  createEventFrom: FormGroup;
  loading = false;
  submitted = false;
  currentUser: UserModel;
  communities: CommunityModel[];

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private communityService: CommunitiesService,
    private eventService: EventService,
    private authenticationService: AuthenticationService
  ) { 
    this.authenticationService.currentUser.subscribe(user => this.currentUser = user);
  }

  ngOnInit(): void {
    this.createEventFrom = this.formBuilder.group({
      eventName: ['', Validators.required],
      start: ['', Validators.required],
      end: ['', Validators.required],
      community: ['', Validators.required],
    });
    this.communityService.userCreatedCommunities(this.currentUser.userName).subscribe((communities) => {
      this.communities = communities.second.list;
    });
  }

  get form() {
    return this.createEventFrom.controls;
  }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.form.invalid) {
      return;
    }

    if(!this.checkCommunities()) {
      return;
    }

    this.loading = true;

    const event = {
      'name': this.createEventFrom.get('eventName').value, 
      'start': this.createEventFrom.get('start').value,
      'end': this.createEventFrom.get('end').value,
      'cID': this.createEventFrom.get('community').value.id,
      'cName': this.createEventFrom.get('community').value.name,
      'ownerUserName': this.currentUser.userName};
    this.eventService.addObject(event).pipe(first()).subscribe((response)=> {
      this.response = response;
      this.loading = false;
    },
    (error) => {
      this.error = error;
      this.loading = false;
    });
  }

  checkCommunities() {
    return (this.communities && this.communities.length !== 0);
  } 

}
