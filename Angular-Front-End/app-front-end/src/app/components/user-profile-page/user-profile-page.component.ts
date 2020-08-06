import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';
import { UserModel } from 'src/app/models/user.model';
import { UserCommunityService } from 'src/app/services/controllers/user-community-controller.service';

@Component({
  selector: 'app-user-profile-page',
  templateUrl: './user-profile-page.component.html',
  styleUrls: ['./user-profile-page.component.scss']
})
export class UserProfilePageComponent implements OnInit {

  currentUser: UserModel;
  communities: any[];
  checkCommunities: boolean = true;
  error: any;

  constructor(
    private authenticationService: AuthenticationService,
    private userCommunityService: UserCommunityService,
  ) { 
    this.authenticationService.currentUser.subscribe(user => this.currentUser = user);
  }

  ngOnInit(): void {
    this.userCommunityService.userSubbedCommunities(this.currentUser.id).subscribe((communityList: any) => {
      this.communities = communityList.second.list;
      if(!communityList.second.list || communityList?.second.list.length === 0) {
        this.checkCommunities = false;
      }
    });
  }

  unsubscribeFromCommunity(id: string): void {
    this.userCommunityService.unsubscribeFromCommunity(this.currentUser.id, id).subscribe();

    this.communities.splice(this.communities.indexOf(id), 1);

    // check if there are still communities
    if(!this.communities || this.communities?.length === 0) {
      this.checkCommunities = false;
    }
  }

}
