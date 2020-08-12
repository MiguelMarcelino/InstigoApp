import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';
import { UserModel } from 'src/app/models/user.model';
import { FeedbackService } from 'src/app/services/controllers/feedback-controller.service';

@Component({
  selector: 'feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.scss']
})
export class FeedbackComponent implements OnInit {

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
    const timeSent = new Date();
    console.log(username);
    this.feedbackService.sendFeedback(username, timeSent.toISOString(), feedback).subscribe((message: string) => {
      this.message = message;
    });
    // ,
    // error => {
    //   this.error = error;
    // });
  }

}
