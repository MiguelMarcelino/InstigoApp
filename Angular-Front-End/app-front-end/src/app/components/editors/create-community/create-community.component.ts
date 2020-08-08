import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { CommunitiesService } from 'src/app/services/controllers/communities-controller.service';

@Component({
  selector: 'app-create-community',
  templateUrl: './create-community.component.html',
  styleUrls: ['./create-community.component.scss']
})
export class CreateCommunityComponent implements OnInit {

  error: string;
  response: string;
  createCommunityForm: FormGroup;
  loading = false;
  submitted = false;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private communityService: CommunitiesService
  ) { }

  ngOnInit(): void {
    this.createCommunityForm = this.formBuilder.group({
      communityName: ['', Validators.required],
      communityDescription: ['', Validators.required]
    });
  }

  get form() {
    return this.createCommunityForm.controls;
  }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.form.invalid) {
      return;
    }

    this.loading = true;
    
    const community = {'name': this.createCommunityForm.get('communityName').value, 
      'description': this.createCommunityForm.get('communityDescription').value}
    this.communityService.addObject(community).subscribe((response) => {
      this.response = response;
    },
    (error) => {
      this.error = error;
      this.loading = false;
    });
  }
}
