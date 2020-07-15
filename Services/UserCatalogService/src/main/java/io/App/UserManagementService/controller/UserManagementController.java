package io.App.UserManagementService.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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

import io.App.UserManagementService.dto.Pair;
import io.App.UserManagementService.dto.UserDTO;
import io.App.UserManagementService.databaseConnection.UserCommunityEvent;
import io.App.UserManagementService.dto.CommunityListWrapper;
import io.App.UserManagementService.dto.EventListWrapper;
import io.App.UserManagementService.dto.UserListWrapper;
import io.App.UserManagementService.exceptions.UserAlreadyExistsException;
import io.App.UserManagementService.exceptions.UserDoesNotExistException;
import io.App.UserManagementService.userComponent.User;
import io.App.UserManagementService.userComponent.UserCatalog;

@RestController
@EnableAutoConfiguration
@RequestMapping("/userCatalogApi")
public class UserManagementController {

	@Autowired
	private UserCatalog uC;
	@Autowired
	private UserCommunityEvent uCE;

	public UserManagementController() {
		// Empty constructor
	}

	@GetMapping(path = "/users")
	public UserListWrapper userList() {
		return uC.getUsers();
	}

	@PostMapping(path = "addUser", consumes = { "application/json" })
	public ResponseEntity<Pair<String, UserDTO>> addUser(@RequestBody String userJSON) throws UserAlreadyExistsException {
		System.out.println(userJSON);
		ObjectMapper objectMapper = new ObjectMapper();
		User user = null;
		try {
			user = objectMapper.readValue(userJSON, User.class);
		} catch (JsonParseException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(new Pair<>("Internal Application Error", null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (JsonMappingException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(new Pair<>("Internal Application Error", null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(new Pair<>("Internal Application Error", null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		uC.addUser(user);
		UserDTO uDTO = new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getPassword());
		return new ResponseEntity<>(new Pair<>("Successfull request", uDTO), HttpStatus.OK);
	}

	// Update User
	
	// Login User

	/*
	 * VER ISTO!
	 */
	@RequestMapping("/test")
	public void test() throws IOException {
		URL url = new URL("http://localhost:8081/addUser/user");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		OutputStream os = conn.getOutputStream();

		ObjectMapper objectMapper = new ObjectMapper();
		User user = new User(1, "Ola", "test@test.com", "password");
		System.out.println(objectMapper.writeValueAsString(user));
	}

	@PostMapping("removeUser/user")
	public void removeUser(@RequestBody User user) throws UserDoesNotExistException {
		uC.removeUser(user);
	}

	@RequestMapping("isRegistered/{uID}/{cID}")
	public void isRegistered(@PathVariable("uID") int uID, @PathVariable("cID") int cID) {
		uCE.isRegisteredToCommunity(uID, cID);
	}

	@RequestMapping("getUserInfo/{uID}")
	public void getUserInfo(@PathVariable("uID") int uID) {
		uC.getUserInfo(uID);
	}

	///////////// UserCommunityEvent
	@RequestMapping("/userSubbedCommunities/{uID}")
	public CommunityListWrapper userSubbedCommunities(@PathVariable("uID") int uID) {
		UserCommunityEvent uCE = new UserCommunityEvent();
		return uCE.userSubCommunities(uID);
	}

	@RequestMapping("/eventsFromSubbedCommunities/{uID}")
	public EventListWrapper eventsFromSubbedCommunities(@PathVariable("uID") int uID) {
		return uCE.eventsFromSubCommunities(uID);
	}

}
