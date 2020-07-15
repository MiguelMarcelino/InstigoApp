package io.App.UserManagementService.userComponent;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.App.UserManagementService.databaseConnection.UserDatabaseConnection;
import io.App.UserManagementService.dto.UserListWrapper;
import io.App.UserManagementService.exceptions.UserAlreadyExistsException;
import io.App.UserManagementService.exceptions.UserDoesNotExistException;

@SpringBootApplication
public class UserCatalog {

	private UserDatabaseConnection uDC;

	public UserCatalog() {
		uDC = new UserDatabaseConnection();
	}

	/**
	 * Get all users registered
	 * @return
	 */
	public UserListWrapper getUsers() {
		return uDC.getUsersFromDatabase();
	}

	/**
	 * Add a user to the system
	 * @param user - user to add
	 * @throws UserAlreadyExistsException - in case the user already exists
	 */
	public void addUser(User user) throws UserAlreadyExistsException {
		if(uDC.getUserByName(user.getName()) != null) {
			throw new UserAlreadyExistsException();
		}
		uDC.addUser(user);
	}

	/**
	 * Remove a user from the system
	 * @param user - user to remove
	 * @throws UserDoesNotExistException
	 */
	public void removeUser(User user) throws UserDoesNotExistException {
		if(uDC.getUserByName(user.getName()) == null) {
			throw new UserDoesNotExistException();
		}
		uDC.removeUser(user);
	}

	public void getUserInfo(int uID) {
		// TODO
	}
	
	

}
