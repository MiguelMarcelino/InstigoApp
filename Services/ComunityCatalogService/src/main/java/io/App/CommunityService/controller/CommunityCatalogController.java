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

import io.App.CommunityService.communityComponent.Community;
import io.App.CommunityService.communityComponent.CommunityCatalog;
import io.App.CommunityService.communityComponent.Role;
import io.App.CommunityService.communityComponent.UserCredentialCheck;
import io.App.CommunityService.dto.CommunityDTO;
import io.App.CommunityService.dto.CommunityListWrapper;
import io.App.CommunityService.dto.Pair;
import io.App.CommunityService.exceptions.CommunityAlreadyExistsException;
import io.App.CommunityService.exceptions.InternalAppException;
import io.App.CommunityService.exceptions.UserDoesNotExistException;

@RestController
@RequestMapping("/communityCatalogApi")
public class CommunityCatalogController {

	@Autowired
	private CommunityCatalog cC;
	@Autowired
	private UserCredentialCheck uCC;

	private static final String INTERNAL_APP_ERROR_MESSAGE = "Internal Application Error";

	@GetMapping(path = "/communities")
	public ResponseEntity<Pair<String, CommunityListWrapper>> communityList() {
		CommunityListWrapper cLW = null;
		try {
			cLW = this.cC.getCommunityList();
		} catch (InternalAppException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(
					new Pair<>("Internal Application Error", null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		System.out.println("Successfull communities request");
		return new ResponseEntity<>(
				new Pair<>("Successfull communities request", cLW),
				HttpStatus.OK);
	}

	@PostMapping(path = "/community/create", consumes = { "application/json" }, produces = { "application/json" })
	public ResponseEntity<String> addCommunity(
			@RequestBody String communityJSON) {
		ObjectMapper objectMapper = new ObjectMapper();
		CommunityDTO cDTO = null;

		try {
			cDTO = objectMapper.readValue(communityJSON, CommunityDTO.class);

			// check user Role
			Role userRole = uCC.getUserRole(cDTO.getOwnerUserName());
			if ((userRole.equals(Role.EDITOR))
					|| (userRole.equals(Role.ADMIN))) {
				cC.addCommunity(new Community(cDTO.getName(),
						cDTO.getDescription(), cDTO.getOwnerUserName()));
			} else {
				System.err.println("No role provided");
				return new ResponseEntity<>("No role provided",
						HttpStatus.OK);
			}
		} catch (JsonParseException | JsonMappingException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(INTERNAL_APP_ERROR_MESSAGE,
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(INTERNAL_APP_ERROR_MESSAGE,
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (InternalAppException | CommunityAlreadyExistsException
				| UserDoesNotExistException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		System.out.println("Successfully added new Community");
		return new ResponseEntity<>("Successfully added new Community",
				HttpStatus.OK);
	}

	@PostMapping(path = "/community/delete", consumes = { "application/json" })
	public ResponseEntity<String> removeCommunity(
			@RequestBody String communityJSON) {
		ObjectMapper objectMapper = new ObjectMapper();
		CommunityDTO cDTO = null;

		try {
			cDTO = objectMapper.readValue(communityJSON, CommunityDTO.class);
			this.cC.removeCommunity(new Community(Integer.parseInt(cDTO.getcID()), cDTO.getName(),
					cDTO.getDescription(), cDTO.getOwnerUserName()));
		} catch (JsonParseException | JsonMappingException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(INTERNAL_APP_ERROR_MESSAGE,
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(INTERNAL_APP_ERROR_MESSAGE,
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (InternalAppException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
		System.out.println("Successfully removed Community");
		return new ResponseEntity<>("Successfully removed Community",
				HttpStatus.OK);

	}

	@GetMapping(path = "/community/{cName}")
	public ResponseEntity<Pair<String, CommunityDTO>> getCommunityByName(
			@PathVariable("cName") String cName) {
		Community c;
		try {
			c = this.cC.getCommunityByName(cName);
		} catch (InternalAppException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(new Pair<>(e.getMessage(), null),
					HttpStatus.CONFLICT);
		}
		CommunityDTO cDTO = new CommunityDTO(String.valueOf(c.getId()), c.getName(),
				c.getDescription(), c.getOwnerUserName());
		return new ResponseEntity<>(new Pair<>("Successfull request", cDTO),
				HttpStatus.OK);
	}

}
