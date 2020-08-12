import { Component, OnInit } from '@angular/core';
import { CommunityModel } from 'src/app/models/community.model';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';
import { UserModel } from 'src/app/models/user.model';
import { CommunitiesService } from 'src/app/services/controllers/communities-controller.service';
import { Role } from 'src/app/models/role.model';

@Component({
  selector: 'app-communities-list',
  templateUrl: './communities-list.component.html',
  styleUrls: ['./communities-list.component.scss']
})
export class CommunitiesListComponent implements OnInit {

  currentUser: UserModel;
  communities: CommunityModel[];
  communitiesCheck: boolean = true;

  constructor(
    private communitiesService: CommunitiesService,
    private authenticationService: AuthenticationService,
    ) 
  {
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
  }

  ngOnInit(): void {
    this.getCommunityLists();
  }

  getCommunityLists(): void {
    this.communitiesService.getCommunityLists(this.currentUser.id).subscribe( (communityLists:any) => {
      this.communities = communityLists.second.allCommunities;

      const subscribedCommunities: Map<string, CommunityModel> = new Map();
      communityLists.second.userSubscribedCommunities.forEach(x => subscribedCommunities.set(x.id, x));

      if(this.communities && subscribedCommunities) {
        if(this.communities.length === 0) {
          this.communitiesCheck = false;
        } else {
          this.communities.forEach(x => {
            if(subscribedCommunities.get(x.id)) {
              x.isSubscribed = true;
            } else {
              x.isSubscribed = false;
            }
          });
        }
      }
    });
  }

  subscribeToCommunity(community: CommunityModel): void {
    this.communitiesService.subscribeToCommunity(this.currentUser.id, community.id).subscribe( x => {
      console.log(x);
    });
    community.isSubscribed = true;
  }

  unsubscribeFromCommunity(community: CommunityModel): void {
    this.communitiesService.unsubscribeFromCommunity(this.currentUser.id, community.id).subscribe();
    community.isSubscribed = false;
  }

  isCommunityOwnerOrAdmin(community: CommunityModel) {
    return ((this.currentUser && (community.ownerUserName === this.currentUser.userName)) ||
            this.currentUser.role === Role.Admin);
  }

  deleteCommunity(community: CommunityModel) {
    this.communitiesService.deleteObject(community).subscribe((response)=> {
      // TODO: Do something with response
    })
    const index: number = this.communities.indexOf(community);
    if(index !== -1) {
      this.communities.splice(index, 1);
    }
  }
}
