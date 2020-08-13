import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';
import { UserModel } from 'src/app/models/user.model';
import { CommunitiesService } from 'src/app/services/controllers/communities-controller.service';
import { Role } from 'src/app/models/role.model';
import { CommunityModel } from 'src/app/models/community.model';

@Component({
  selector: 'app-user-profile-page',
  templateUrl: './user-profile-page.component.html',
  styleUrls: ['./user-profile-page.component.scss']
})
export class UserProfilePageComponent implements OnInit {

  currentUser: UserModel;
  subCommunities: any[];
  cCommunities: CommunityModel[];
  checkCommunities: boolean = true;
  checkCreatedCommunities: boolean = true;
  error: any;

  constructor(
    private authenticationService: AuthenticationService,
    private communitiesService: CommunitiesService,
  ) { 
    this.authenticationService.currentUser.subscribe(user => this.currentUser = user);
  }

  ngOnInit(): void {
    this.subscribedCommunities();
    this.createdCommunities();
  }

  subscribedCommunities(): void {
    this.communitiesService.userSubbedCommunities(this.currentUser.id).subscribe((communityList: any) => {
      this.subCommunities = communityList.second.list;
      if(communityList.second.list) {
        if(communityList?.second.list.length === 0) {
          this.checkCommunities = false;
        }
      } else {
        this.checkCommunities = false;
      }
    });
  }

  createdCommunities(): void {
    this.communitiesService.userCreatedCommunities(this.currentUser.userName).subscribe((createdCommunities) => {
      this.cCommunities = createdCommunities.second.list;
      if(this.cCommunities){
        if(this.cCommunities.length === 0) {
          this.checkCreatedCommunities = false;
        }
      } else {
        this.checkCreatedCommunities = false;
      }
    });
  }

  unsubscribeFromCommunity(id: string): void {
    this.communitiesService.unsubscribeFromCommunity(this.currentUser.id, id).subscribe();

    this.subCommunities.splice(this.subCommunities.indexOf(id), 1);

    // check if there are still communities
    if(!this.subCommunities || this.subCommunities?.length === 0) {
      this.checkCommunities = false;
    }
  }

  isAllowed(): boolean {
    return ((this.currentUser.role === Role.Admin) || (this.currentUser.role === Role.Editor));
  }

  deleteCommunity(community: CommunityModel) {
    this.communitiesService.deleteObject(community).subscribe((response)=> {
      // TODO: Do something with response
    })
    const index: number = this.cCommunities.indexOf(community);
    if(index !== -1) {
      this.cCommunities.splice(index, 1);
    }
  }

}
