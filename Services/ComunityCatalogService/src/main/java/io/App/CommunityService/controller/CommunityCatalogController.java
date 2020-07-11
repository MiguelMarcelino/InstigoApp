package io.App.CommunityService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.App.CommunityService.communityComponent.Community;
import io.App.CommunityService.communityComponent.CommunityCatalog;
import io.App.CommunityService.dto.CommunityDTO;
import io.App.CommunityService.dto.CommunityListWrapper;
import io.App.CommunityService.exceptions.CommunityAlreadyExistsException;
import io.App.CommunityService.exceptions.CommunityDoesNotExistException;

@RestController
public class CommunityCatalogController {

	@Autowired
	private CommunityCatalog cC;

	@GetMapping(path = "/communities")
	public CommunityListWrapper communityList() {
		return this.cC.getCommunityList();
	}

	@PostMapping(path = "community/create", consumes = { "application/json" })
	public void addCommunity(@RequestBody int cID, @RequestBody String cName) throws CommunityAlreadyExistsException {
		Community c = new Community(cID, cName);
		this.cC.addCommunity(c);
	}

	@PostMapping(path = "community/delete", consumes = { "application/json" })
	public void removeCommunity(@RequestBody int cID, @RequestBody String cName) throws CommunityDoesNotExistException {
		Community c = new Community(cID, cName);
		this.cC.removeCommunity(c);
	}

	@GetMapping(path = "/community/{cID}")
	public CommunityDTO getCommunityById(@PathVariable("cID") int cID) throws CommunityDoesNotExistException {
		Community c = this.cC.getCommunityById(cID);
		CommunityDTO cDTO = new CommunityDTO(c.getId(), c.getName());
		return cDTO;
	}

}
