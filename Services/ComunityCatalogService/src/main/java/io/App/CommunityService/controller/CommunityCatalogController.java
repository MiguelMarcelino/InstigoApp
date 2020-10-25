package io.App.CommunityService.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.App.CommunityService.business.Community;
import io.App.CommunityService.business.exceptions.CommunityAlreadyExistsException;
import io.App.CommunityService.business.exceptions.InternalAppException;
import io.App.CommunityService.business.mappers.CommunityMapper;
import io.App.CommunityService.business.services.CommunityService;
import io.App.CommunityService.dto.CommunityDTO;
import io.App.CommunityService.dto.CommunityListWrapper;
import io.App.CommunityService.dto.Pair;

@RestController
@RequestMapping("/communityCatalogApi")
public class CommunityCatalogController {

	private static final String INTERNAL_APP_ERROR_MESSAGE = "Internal Application Error";

	@Autowired
	private CommunityService cC;

	@GetMapping(path = "/communities")
	@PreAuthorize("hasPermission(#currUserID, READ_COMMUNITY)")
	public ResponseEntity<Pair<String, CommunityListWrapper>> communityList() {
		CommunityListWrapper cLW = null;
		try {
			cLW = new CommunityListWrapper(CommunityMapper
					.communityListToCommunityDTOList(cC.getCommunityList()));
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

	/**
	 * 
	 * @param communityJSON
	 * @return
	 */
	@PostMapping(path = "/community/create", consumes = {
			"application/json" }, produces = { "application/json" })
	@PreAuthorize("hasPermission(#currUserID, ADD_COMMUNITY)")
	public ResponseEntity<String> addCommunity(int currUserID,
			@RequestBody String communityJSON) {
		ObjectMapper objectMapper = new ObjectMapper();
		CommunityDTO cDTO = null;

		try {
			cDTO = objectMapper.readValue(communityJSON, CommunityDTO.class);

			// if no exception is thrown, the new community gets created
			cC.addCommunity(new Community(cDTO.getName(), cDTO.getDescription(),
					currUserID));

		} catch (JsonParseException | JsonMappingException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(INTERNAL_APP_ERROR_MESSAGE,
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(INTERNAL_APP_ERROR_MESSAGE,
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (InternalAppException | CommunityAlreadyExistsException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		System.out.println("Successfully added new Community");
		return new ResponseEntity<>("Successfully added new Community",
				HttpStatus.OK);
	}

	/**
	 * 
	 * @param communityJSON
	 * @return
	 */
	@PostMapping(path = "/community/delete", consumes = { "application/json" })
	@PreAuthorize("hasPermission(#currUserID, DELETE_COMMUNITY) or "
			+ "hasPermission(#currUserID, DELETE_ALL_COMMUNITY)")
	public ResponseEntity<String> removeCommunity(
			int currUserID, @RequestBody String communityJSON) {
		ObjectMapper objectMapper = new ObjectMapper();
		CommunityDTO communityDTO = null;

		try {
			communityDTO = objectMapper.readValue(communityJSON,
					CommunityDTO.class);

			// if no exception is thrown, the new community gets deleted
			this.cC.removeCommunity(new Community(communityDTO.getId(),
					communityDTO.getName(), communityDTO.getDescription(),
					currUserID));
		} catch (JsonParseException | JsonMappingException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(INTERNAL_APP_ERROR_MESSAGE,
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(INTERNAL_APP_ERROR_MESSAGE,
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (InternalAppException  e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}
		System.out.println("Successfully removed Community");
		return new ResponseEntity<>("Successfully removed Community",
				HttpStatus.OK);
	}

	/**
	 * 
	 * @param cName
	 * @return
	 */
	@GetMapping(path = "/community/{cName}")
	@PreAuthorize("hasPermission(#currUserID, READ_COMMUNITY)")
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
		CommunityDTO cDTO = new CommunityDTO(c.getId(), c.getName(),
				c.getDescription(), c.getCommunityOwnerId());
		return new ResponseEntity<>(new Pair<>("Successfull request", cDTO),
				HttpStatus.OK);
	}

	@GetMapping("/communityListWithSubInfo/{uID}")
	@PreAuthorize("hasPermission(#currUserID, READ_COMMUNITY)")
	public ResponseEntity<Pair<String, CommunityListWrapper>> communityListWithSubInfo(
			@PathVariable("uID") String uID) {
		CommunityListWrapper cLW = null;

		try {
			cLW = new CommunityListWrapper(
					CommunityMapper.communityListToCommunityDTOList(
							cC.getCommunityListWithSubInfo(
									Integer.parseInt(uID))));
		} catch (NumberFormatException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(
					new Pair<>(INTERNAL_APP_ERROR_MESSAGE, null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (InternalAppException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(new Pair<>(e.getMessage(), null),
					HttpStatus.OK);
		}

		System.out.println("Successfully got Community lists");
		return new ResponseEntity<>(
				new Pair<>("Successfully got Community list", cLW),
				HttpStatus.OK);
	}

	@GetMapping("/userCreatedCommunities/{ownerUserName}")
	@PreAuthorize("hasPermission(#currUserID, CREATE_COMMUNITY)")
	public ResponseEntity<Pair<String, CommunityListWrapper>> userCreatedCommunities(
			@PathVariable("ownerUserName") String ownerUserName) {
		CommunityListWrapper cLW = null;

		try {
			cLW = new CommunityListWrapper(
					CommunityMapper.communityListToCommunityDTOList(
							cC.userCreatedCommunities(ownerUserName)));
		} catch (NumberFormatException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(
					new Pair<>(INTERNAL_APP_ERROR_MESSAGE, null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (InternalAppException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(new Pair<>(e.getMessage(), null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		System.out.println("Successfully got user created Community list");
		return new ResponseEntity<>(
				new Pair<>("Successfully got Community list", cLW),
				HttpStatus.OK);
	}

}
