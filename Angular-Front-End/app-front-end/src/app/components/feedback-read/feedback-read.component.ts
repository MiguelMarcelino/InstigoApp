import { Component, OnInit } from '@angular/core';
import { FeedbackService } from 'src/app/services/controllers/feedback-controller.service';
import { FeedbackModel } from 'src/app/models/feedback.model';
import { UserModel } from 'src/app/models/user.model';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';

@Component({
  selector: 'app-feedback-read',
  templateUrl: './feedback-read.component.html',
  styleUrls: ['./feedback-read.component.scss']
})
export class FeedbackReadComponent implements OnInit {

  feedbacks: FeedbackModel[];
  currentUser: UserModel;
  checkFeedbacks: boolean = true;

  constructor(
    private feedbackService: FeedbackService,
    private authenticationService: AuthenticationService
  ) { 
    this.authenticationService.currentUser.subscribe((user) => {
      this.currentUser = user;
    });
  }

  ngOnInit(): void {
    this.getAllFeedback();
  }

  getAllFeedback(): void {
    this.feedbackService.getAllFeedback(this.currentUser.id).subscribe( (allFeedbacks:any) => {
      this.feedbacks = allFeedbacks.second.listFeedbacks;
      console.log(allFeedbacks);
      if(!this.feedbacks || this.feedbacks.length === 0) {
        this.checkFeedbacks = false;
      }
    });
  }

}
