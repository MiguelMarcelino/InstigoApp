import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { CommunitiesService } from 'src/app/services/controllers/communities-controller.service';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';
import { UserModel } from 'src/app/models/user.model';

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
  currentUser: UserModel;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private communityService: CommunitiesService,
    private authenticationService: AuthenticationService
  ) { 
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
  }

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
    
    const community = {
      'name': this.createCommunityForm.get('communityName').value, 
      'description': this.createCommunityForm.get('communityDescription').value,
      'ownerUserName': this.currentUser.userName};
    this.communityService.addObject(community).subscribe((response) => {
      this.response = response;
      this.router.navigate(['/']);
    },
    (error) => {
      this.error = error;
      this.loading = false;
    });
  }
}
