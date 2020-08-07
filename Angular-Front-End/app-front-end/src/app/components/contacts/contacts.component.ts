import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';
import { UserModel } from 'src/app/models/user.model';
import { FeedbackService } from 'src/app/services/controllers/feedback-controller.service';

@Component({
  selector: 'contacts',
  templateUrl: './contacts.component.html',
  styleUrls: ['./contacts.component.scss']
})
export class ContactsComponent implements OnInit {

  feedbackForm: FormGroup;
  currentUser: UserModel;
  message: string;
  error: string;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthenticationService,
    private feedbackService: FeedbackService
  ) { }

  ngOnInit(): void {
    this.feedbackForm = this.formBuilder.group({
      feedback: new FormControl(''),
    });
    this.authService.currentUser.subscribe(user => this.currentUser = user)
  }

  onSubmit() {
    const feedback = this.feedbackForm.get('feedback').value;
    const username = this.currentUser.userName;
    console.log(username);
    this.feedbackService.sendFeedback(username, feedback).subscribe((message: string) => {
      this.message = message;
    });
    // ,
    // error => {
    //   this.error = error;
    // });
  }

}
