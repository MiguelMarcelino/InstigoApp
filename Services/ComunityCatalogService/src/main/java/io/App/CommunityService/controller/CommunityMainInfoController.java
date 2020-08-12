package io.App.CommunityService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.App.CommunityService.communityComponent.CommunityCatalog;
import io.App.CommunityService.communityComponent.UserCommunityCatalog;
import io.App.CommunityService.dto.CommunityListsWrapper;
import io.App.CommunityService.dto.Pair;
import io.App.CommunityService.exceptions.InternalAppException;

@RestController
@RequestMapping("/communityMainInfoApi")
public class CommunityMainInfoController {

	@Autowired
	private UserCommunityCatalog uCC;

	@Autowired
	private CommunityCatalog cC;
	
	private static final String INTERNAL_APP_ERROR_MESSAGE = "Internal Application Error";

	@GetMapping("/communityLists/{uID}")
	public ResponseEntity<Pair<String, CommunityListsWrapper>> communityLists(
			@PathVariable("uID") String uID) {
		CommunityListsWrapper cLsW = null;

		try {
			cLsW = new CommunityListsWrapper(uCC.userSubbscribedCommunities(Integer.parseInt(uID)), cC.getCommunityList());
		} catch (NumberFormatException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(
					new Pair<>(INTERNAL_APP_ERROR_MESSAGE, null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (InternalAppException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(
					new Pair<>(e.getMessage(), null),
					HttpStatus.OK);
		}

		System.out.println("Successfully got Community lists");
		return new ResponseEntity<>(
				new Pair<>("Successfully got Community list", cLsW),
				HttpStatus.OK);
	}
}
