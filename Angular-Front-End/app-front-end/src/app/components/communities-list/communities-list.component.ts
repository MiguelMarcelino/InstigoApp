import { Component, OnInit } from '@angular/core';
import { CommunityModel } from 'src/app/models/community.model';
import { CommunitiesService } from 'src/app/services/controllers/communities-controller.service';

@Component({
  selector: 'app-communities-list',
  templateUrl: './communities-list.component.html',
  styleUrls: ['./communities-list.component.scss']
})
export class CommunitiesListComponent implements OnInit {

  communities: CommunityModel[];
  menuShow: false;

  constructor(
    private communitiesService: CommunitiesService
    ) 
  { }

  ngOnInit(): void {
    this.getCommunityList();
  }

  getCommunityList(): void {
    this.communitiesService.getAll().subscribe((communitiesList) => {
      this.communities = communitiesList.second.list;
    })
  }

  showMenu():void {
    this.menuShow = ! this.menuShow;
  }
}
