import { Component, OnInit } from '@angular/core';
import { CommunityModel } from 'src/app/models/community.model';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';
import { UserModel } from 'src/app/models/user.model';
import { UserCommunityService } from 'src/app/services/controllers/user-community-controller.service'
import { CommunitiesService } from 'src/app/services/controllers/communities-controller.service';

@Component({
  selector: 'app-communities-list',
  templateUrl: './communities-list.component.html',
  styleUrls: ['./communities-list.component.scss']
})
export class CommunitiesListComponent implements OnInit {

  currentUser: UserModel;
  communities: CommunityModel[];
  selectedCommunity: CommunityModel;

  constructor(
    private communitiesService: CommunitiesService,
    private userCommunityService: UserCommunityService,
    private authenticationService: AuthenticationService,
    ) 
  {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
  }

  ngOnInit(): void {
    this.getCommunityList();
  }

  getCommunityList(): void {
    this.communitiesService.getAll().subscribe((communitiesList) => {
      this.communities = communitiesList.second.list;
    })
  }

  subscribeToCommunity(cID: string): void {
    this.userCommunityService.subscribeToCommunity(this.currentUser.id, cID).subscribe();
  }
}
