import { Component, OnInit } from '@angular/core';
import { CommunityModel } from 'src/app/models/community.model';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';
import { UserModel } from 'src/app/models/user.model';
import { UserCommunityService } from 'src/app/services/controllers/user-community-controller.service'
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
      if(this.communities.length === 0) {
        this.communitiesCheck = false;
      } else {
        this.communities.forEach(x => {
          if(this.subscribbedCommunities.get(x.id)) {
            x.isSubscribed = true;
          } else {
            x.isSubscribed = false;
          }
        });
      }
    }
  }

  subscribeToCommunity(community: CommunityModel): void {
    this.userCommunityService.subscribeToCommunity(this.currentUser.id, community.id).subscribe( x => {
      console.log(x);
    });
    this.subscribbedCommunities.set(community.id, community);
    community.isSubscribed = true;
  }

  unsubscribeFromCommunity(community: CommunityModel): void {
    this.userCommunityService.unsubscribeFromCommunity(this.currentUser.id, community.id).subscribe();
    this.subscribbedCommunities.delete(community.id);
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
