package io.App.UserManagementService.controller;

import java.io.IOException;
import java.util.Date;

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

import io.App.UserManagementService.dto.Pair;
import io.App.UserManagementService.dto.UserDTO;
import io.App.UserManagementService.dto.UserListWrapper;
import io.App.UserManagementService.dto.UserLoginModel;
import io.App.UserManagementService.exceptions.InternalAppException;
import io.App.UserManagementService.exceptions.UserAlreadyExistsException;
import io.App.UserManagementService.exceptions.UserDoesNotExistException;
import io.App.UserManagementService.userComponent.User;
import io.App.UserManagementService.userComponent.UserCatalog;

@RestController
@RequestMapping("/userCatalogApi")
public class UserManagementController {

	@Autowired
	private UserCatalog uC;

	private static final String INTERNAL_APP_ERROR_MESSAGE = "Internal Application Error";

	public UserManagementController() {
		// Empty constructor
	}

	@GetMapping(path = "/users")
	public UserListWrapper userList() {
		return uC.getUsers();
	}

	@PostMapping(path = "addUser", consumes = { "application/json" })
	public ResponseEntity<Pair<String, UserDTO>> addUser(
			@RequestBody String userJSON) {
		ObjectMapper objectMapper = new ObjectMapper();
		User user = null;
		try {
			user = objectMapper.readValue(userJSON, User.class);

			uC.addUser(user);
		} catch (JsonParseException | JsonMappingException
				| InternalAppException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(
					new Pair<>(INTERNAL_APP_ERROR_MESSAGE, null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(
					new Pair<>(INTERNAL_APP_ERROR_MESSAGE, null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (UserAlreadyExistsException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(new Pair<>(e.getMessage(), null),
					HttpStatus.OK);
		}
		Date currDate = new Date();
		UserDTO uDTO = new UserDTO(String.valueOf(user.getId()),
				user.getUserName(), user.getFirstName(), user.getLastName(),
				user.getRoleName(), user.getEmail(), currDate);
		System.out.println("Successfully added new User");
		return new ResponseEntity<>(
				new Pair<>("Successfully added new User", uDTO), HttpStatus.OK);
	}

	@PostMapping(path = "login", consumes = { "application/json" })
	public ResponseEntity<Pair<String, UserDTO>> loginUser(
			@RequestBody String userModelJSON) {
		ObjectMapper objectMapper = new ObjectMapper();
		UserLoginModel uMD = null;
		User user = null;
		try {
			uMD = objectMapper.readValue(userModelJSON, UserLoginModel.class);

			// check User information (going to change after implementation of
			// security features)
			user = uC.getUserByName(uMD.getUsername());

			if (!user.getPassword().equals(uMD.getPassword())) {
				return new ResponseEntity<>(new Pair<>(
						"The inserted password doesn't match that users password",
						null), HttpStatus.CONFLICT);
			}
		} catch (JsonParseException | JsonMappingException
				| InternalAppException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(
					new Pair<>(INTERNAL_APP_ERROR_MESSAGE, null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(
					new Pair<>(INTERNAL_APP_ERROR_MESSAGE, null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (UserDoesNotExistException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(new Pair<>(e.getMessage(), null),
					HttpStatus.OK);
		}

		// if password matches
		// create new Date which represents moment from which user is logged in
		Date currDate = new Date();
		UserDTO uDTO = new UserDTO(String.valueOf(user.getId()),
				user.getUserName(), user.getFirstName(), user.getLastName(),
				user.getRoleName(), user.getEmail(), currDate);
		return new ResponseEntity<>(new Pair<>("Successfull request", uDTO),
				HttpStatus.OK);
	}

	// Update User
	@PostMapping(path = "updateUser", consumes = { "application/json" })
	public ResponseEntity<String> updateUser(
			@RequestBody String userModelJSON) {
		// TODO
		return null;
	}

	@PostMapping("removeUser/user")
	public ResponseEntity<String> removeUser(@RequestBody String userDTO) {
		ObjectMapper objectMapper = new ObjectMapper();
		UserDTO uDTO = null;
		User userToRemove = null;

		try {
			uDTO = objectMapper.readValue(userDTO, UserDTO.class);

			// user can only be removed if he was previously logged in, so no
			// need to check password again
			userToRemove = new User(Integer.parseInt(uDTO.getId()),
					uDTO.getName(), uDTO.getFirstName(), uDTO.getLastName(),
					uDTO.getRole(), uDTO.getEmail(), null);
			uC.removeUser(userToRemove);
		} catch (JsonParseException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>("Internal Application Error",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (JsonMappingException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>("Internal Application Error",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>("Internal Application Error",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (InternalAppException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		System.out.println("Successfully removed User");
		return new ResponseEntity<>("Successfully removed User", HttpStatus.OK);
	}

	@RequestMapping("getUserInfo/{uID}")
	public void getUserInfo(@PathVariable("uID") int uID) {
		uC.getUserInfo(uID);
	}

}
