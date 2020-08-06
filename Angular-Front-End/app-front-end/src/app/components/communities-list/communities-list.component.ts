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
  subscribbedCommunities: Map<string, CommunityModel>;

  constructor(
    private communitiesService: CommunitiesService,
    private userCommunityService: UserCommunityService,
    private authenticationService: AuthenticationService,
    ) 
  {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
  }

  ngOnInit(): void {
    this.getFinalCommunityList();
  }

  getAllCommunities() {
    return this.communitiesService.getAll().toPromise();
  }

  getSubscribbedCommunities() {
    return this.userCommunityService.userSubbedCommunities(this.currentUser.id).toPromise();
  }

  async getFinalCommunityList() {
    const communityList = await this.getAllCommunities();
    this.communities = communityList.second.list;
    
    this.subscribbedCommunities = new Map();
    const subbedCommunities = await this.getSubscribbedCommunities();
    subbedCommunities.second.list.forEach(x => this.subscribbedCommunities.set(x.id, x));

    if(this.communities && this.subscribbedCommunities) {
      this.communities.forEach(x => {
        if(this.subscribbedCommunities.get(x.id)) {
          x.isSubscribed = true;
        } else {
          x.isSubscribed = false;
        }
      });
    }
  }

  subscribeToCommunity(community: CommunityModel): void {
    this.userCommunityService.subscribeToCommunity(this.currentUser.id, community.id).subscribe();
    this.subscribbedCommunities.set(community.id, community);
    community.isSubscribed = true;
  }

  unsubscribeFromCommunity(community: CommunityModel): void {
    this.userCommunityService.unsubscribeFromCommunity(this.currentUser.id, community.id).subscribe();
    this.subscribbedCommunities.delete(community.id);
    community.isSubscribed = false;
  }

  applyStyle(element) {

  }
}
