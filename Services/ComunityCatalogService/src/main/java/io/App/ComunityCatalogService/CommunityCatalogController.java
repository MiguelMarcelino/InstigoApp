package io.App.ComunityCatalogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@RequestMapping("/removeCommunity/{cID}")
	public Community getCommunityNameById(@PathVariable("cID") int cID) {
		return this.cC.getCommunityNameById(cID);
	}
	
}
