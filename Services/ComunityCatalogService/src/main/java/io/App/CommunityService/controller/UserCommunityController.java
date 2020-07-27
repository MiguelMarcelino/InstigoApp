package io.App.CommunityService.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.App.CommunityService.communityComponent.UserCommunityCatalog;
import io.App.CommunityService.dto.CommunityListWrapper;
import io.App.CommunityService.dto.CommunitySubscribeDTO;
import io.App.CommunityService.exceptions.AlreadySubscribedException;
import io.App.CommunityService.exceptions.InternalAppException;

@RestController
@RequestMapping("/userCommunityApi")
public class UserCommunityController {

	@Autowired
	private UserCommunityCatalog uCC;

	private static final String INTERNAL_APP_ERROR_MESSAGE = "Internal Application Error";

	// For later
	@GetMapping("/userSubbedCommunities/{uID}")
	public CommunityListWrapper userSubbedCommunities(
			@PathVariable("uID") int uID) {
		return uCC.userSubbscribedCommunities(uID);
	}

	@GetMapping("isRegistered/{uID}/{cID}")
	public void isRegisteredToCommunity(@PathVariable("uID") int uID,
			@PathVariable("cID") int cID) {
		uCC.isRegToCommunity(uID, cID);
	}

	@PostMapping(path = "/subscribeToCommunity", consumes = {
			"application/json" })
	public ResponseEntity<String> subscribeToCommunity(
			@RequestBody String communitySubscribeJSON) {
		ObjectMapper objectMapper = new ObjectMapper();
		CommunitySubscribeDTO cDTO = null;

		try {
			cDTO = objectMapper.readValue(communitySubscribeJSON,
					CommunitySubscribeDTO.class);

			uCC.subscribeToCommunity(Integer.parseInt(cDTO.getuID()),
					Integer.parseInt(cDTO.getcID()));
		} catch (AlreadySubscribedException | InternalAppException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (JsonParseException | JsonMappingException
				| NumberFormatException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(INTERNAL_APP_ERROR_MESSAGE,
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(INTERNAL_APP_ERROR_MESSAGE,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		System.out.println("Successfully subscribed user to Community");
		return new ResponseEntity<>("Successfully subscribed user to Community",
				HttpStatus.OK);
	}
}
