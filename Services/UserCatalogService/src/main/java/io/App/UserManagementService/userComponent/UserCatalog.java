package io.App.UserManagementService.userComponent;

import io.App.UserManagementService.databaseConnection.UserDatabaseConnection;
import io.App.UserManagementService.dto.UserListWrapper;
import io.App.UserManagementService.exceptions.UserAlreadyExistsException;
import io.App.UserManagementService.exceptions.UserDoesNotExistException;

public class UserCatalog {

	private UserDatabaseConnection uDC;

	public UserCatalog() {
		uDC = new UserDatabaseConnection();
	}

	public UserListWrapper getUsers() {
		return uDC.getUsersFromDatabase();
	}

	public void addUser(User user) throws UserAlreadyExistsException {
		if(uDC.getUserByName(user.getName()) != null) {
			throw new UserAlreadyExistsException();
		}
		uDC.addUser(user);
	}

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
