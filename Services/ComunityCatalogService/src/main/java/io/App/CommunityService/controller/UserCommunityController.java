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
import io.App.CommunityService.dto.CommunitySubscriptionDTO;
import io.App.CommunityService.dto.Pair;
import io.App.CommunityService.exceptions.AlreadySubscribedException;
import io.App.CommunityService.exceptions.InternalAppException;

@RestController
@RequestMapping("/userCommunityApi")
public class UserCommunityController {

	@Autowired
	private UserCommunityCatalog uCC;

	private static final String INTERNAL_APP_ERROR_MESSAGE = "Internal Application Error";


	@GetMapping("/userSubbedCommunities/{uID}")
	public ResponseEntity<Pair<String, CommunityListWrapper>> userSubbedCommunities(
			@PathVariable("uID") String uID) {
		CommunityListWrapper cLW = null;

		try {
			cLW = uCC.userSubbscribedCommunities(Integer.parseInt(uID));
		} catch (NumberFormatException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(new Pair<>(INTERNAL_APP_ERROR_MESSAGE, null), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (InternalAppException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(new Pair<>(e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		System.out.println("Successfully got Community list");
		return new ResponseEntity<>(new Pair<>("Successfully got Community list", cLW), HttpStatus.OK);
	}
	
	@GetMapping("/userCreatedCommunities/{ownerUserName}")
	public ResponseEntity<Pair<String, CommunityListWrapper>> userCreatedCommunities(
			@PathVariable("ownerUserName") String ownerUserName) {
		CommunityListWrapper cLW = null;

		try {
			cLW = uCC.userCreatedCommunities(ownerUserName);
		} catch (NumberFormatException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(new Pair<>(INTERNAL_APP_ERROR_MESSAGE, null), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (InternalAppException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(new Pair<>(e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		System.out.println("Successfully got user created Community list");
		return new ResponseEntity<>(new Pair<>("Successfully got Community list", cLW), HttpStatus.OK);
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
		CommunitySubscriptionDTO cDTO = null;

		try {
			cDTO = objectMapper.readValue(communitySubscribeJSON,
					CommunitySubscriptionDTO.class);

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

	@PostMapping(path = "/unsubscribeFromCommunity", consumes = {
			"application/json" })
	public ResponseEntity<String> unsubscribeFromCommunity(
			@RequestBody String communityUnsubscribeJSON) {
		ObjectMapper objectMapper = new ObjectMapper();
		CommunitySubscriptionDTO cSDTO = null;

		try {
			cSDTO = objectMapper.readValue(communityUnsubscribeJSON,
					CommunitySubscriptionDTO.class);

			uCC.unsubscribeFromCommunity(Integer.parseInt(cSDTO.getuID()),
					Integer.parseInt(cSDTO.getcID()));
		} catch (InternalAppException e) {
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
