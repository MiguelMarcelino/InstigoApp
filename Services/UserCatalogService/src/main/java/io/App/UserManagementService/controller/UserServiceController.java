package io.App.UserManagementService.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

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

import io.App.UserManagementService.business.Operation;
import io.App.UserManagementService.business.User;
import io.App.UserManagementService.business.UserAuthorizationCheck;
import io.App.UserManagementService.business.catalogs.OperationsCatalog;
import io.App.UserManagementService.business.catalogs.UserCatalog;
import io.App.UserManagementService.business.mappers.OperationMapper;
import io.App.UserManagementService.business.mappers.UserMapper;
import io.App.UserManagementService.dto.Pair;
import io.App.UserManagementService.dto.UserDTO;
import io.App.UserManagementService.dto.UserListWrapper;
import io.App.UserManagementService.dto.UserLoginModel;
import io.App.UserManagementService.exceptions.InternalAppException;
import io.App.UserManagementService.exceptions.UserAlreadyExistsException;
import io.App.UserManagementService.exceptions.UserDoesNotExistException;
import io.App.UserManagementService.exceptions.WrongCredentialsException;

@RestController
@RequestMapping("/userCatalogApi")
public class UserServiceController {

	private static final String INTERNAL_APP_ERROR_MESSAGE = "Internal Application Error";

	@Autowired
	private UserCatalog uC;

	@Autowired
	private OperationsCatalog rC;

	private UserAuthorizationCheck uAC;

	public UserServiceController() {
		// Empty constructor
	}

	@GetMapping(path = "/users")
	public ResponseEntity<Pair<String, UserListWrapper>> userList() {
		UserListWrapper uLW = null;

		try {
			uLW = new UserListWrapper(
					UserMapper.userListToUserDTOListMapper(uC.getUsers()));
		} catch (InternalAppException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(new Pair<>(e.getMessage(), null),
					HttpStatus.OK);
		}

		System.out.println("Successfully got users");
		return new ResponseEntity<>(new Pair<>("Successfully got users", uLW),
				HttpStatus.OK);
	}

	@RequestMapping("getUserById/{uID}")
	public ResponseEntity<Pair<String, UserDTO>> getUserInfo(
			@PathVariable("uID") int uID) {
		UserDTO userDTO = null;

		try {
			userDTO = UserMapper.userToUserDTOMapper(uC.getUserInfo(uID));
		} catch (InternalAppException | UserDoesNotExistException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(new Pair<>(e.getMessage(), null),
					HttpStatus.OK);
		}

		System.out.println("Successfully got user");
		return new ResponseEntity<>(
				new Pair<>("Successfully got user", userDTO), HttpStatus.OK);
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
		UserDTO uDTO = new UserDTO(user.getId(), user.getUserName(),
				user.getFirstName(), user.getLastName(), user.getRoleId(),
				user.getEmail(), currDate);
		System.out.println("Successfully added new User");
		return new ResponseEntity<>(
				new Pair<>("Successfully added new User", uDTO), HttpStatus.OK);
	}

	/**
	 * This method creates a new editor.
	 * 
	 * @return
	 */
	public ResponseEntity<Pair<String, UserDTO>> addEditor() {
		// TODO
		return null;
	}

	@PostMapping("removeUser/user")
	public ResponseEntity<String> removeUser(@RequestBody String userDTO) {
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			UserDTO uDTO = objectMapper.readValue(userDTO, UserDTO.class);

			// check credentials
			User userToRemove = new User(uDTO.getId(), uDTO.getName(),
					uDTO.getFirstName(), uDTO.getLastName(), uDTO.getEmail(),
					uDTO.getRole());
			uC.removeUser(userToRemove.getId());
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

	@PostMapping(path = "login", consumes = { "application/json" })
	public ResponseEntity<Pair<String, UserDTO>> loginUser(
			@RequestBody String userModelJSON) {
		ObjectMapper objectMapper = new ObjectMapper();
		UserLoginModel userLoginData = null;
		User storedUser = null;
		List<Operation> allowedOperations = null;

		try {
			userLoginData = objectMapper.readValue(userModelJSON, UserLoginModel.class);

			storedUser = uC.getUserByName(userLoginData.getUsername());

			// Verify user Login information
			uAC.verifyUserLogin(userLoginData, storedUser);

			// after verification get user allowed operations
			allowedOperations = rC.getOperationsForRoleID(storedUser.getRoleId());

		} catch (JsonParseException | JsonMappingException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(
					new Pair<>(INTERNAL_APP_ERROR_MESSAGE, null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(
					new Pair<>(INTERNAL_APP_ERROR_MESSAGE, null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (UserDoesNotExistException | WrongCredentialsException
				| InternalAppException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(new Pair<>(e.getMessage(), null),
					HttpStatus.OK);
		}

		// after verification of user login
		// create new Date which represents moment from which user is logged in
		Date currDate = new Date();
		UserDTO uDTO = UserMapper.userToUserDTOMapper(storedUser);
		uDTO.setDate(currDate);
		// set Operations

		uDTO.setAllowedOperations(OperationMapper
				.operationListToOperationDTOListMapper(allowedOperations));

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

}
