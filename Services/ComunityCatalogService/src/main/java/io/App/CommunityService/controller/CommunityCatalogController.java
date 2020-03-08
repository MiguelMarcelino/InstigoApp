package io.App.CommunityService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.App.CommunityService.communityComponent.Community;
import io.App.CommunityService.communityComponent.CommunityCatalog;
import io.App.CommunityService.dto.CommunityListWrapper;

@RestController
public class CommunityCatalogController {

	@Autowired
	private CommunityCatalog cC;
	
	@RequestMapping("/communityCatalogList")
	public CommunityListWrapper communityList() {
		return this.cC.getCommunityList();
	}
	
	@RequestMapping("/addCommunity/{cID}/{cName}")
	public void addCommunity(@PathVariable("cID") int cID, @PathVariable("cName")String cName) {
		Community c = new Community(cID, cName);
		this.cC.addCommunity(c);
	}
	
	@RequestMapping("/removeCommunity/{cID}/{cName}")
	public void removeCommunity(@PathVariable("cID") int cID, @PathVariable("cName")String cName) {
		Community c = new Community(cID, cName);
		this.cC.removeCommunity(c);
	}
	
	@RequestMapping("/getCommunityById/{cID}")
	public Community getCommunityById(@PathVariable("cID") int cID) {
		return this.cC.getCommunityById(cID);
	}
	
}
