import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
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

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private communityService: CommunitiesService //TODO
  ) { }

  ngOnInit(): void {
    this.createEventFrom = this.formBuilder.group({
      eventName: ['', Validators.required],
      start: ['', Validators.required],
      end: ['', Validators.required],
      community: ['', Validators.required], // make dropdown to sellect communities
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

    this.loading = true;
    
    const community = {'name': this.createEventFrom.get('communityName').value, 
      'description': this.createEventFrom.get('communityDescription').value}
    this.communityService.addObject(community).subscribe((response) => {
      this.response = response;
    },
    (error) => {
      this.error = error;
      this.loading = false;
    });
  }

}
