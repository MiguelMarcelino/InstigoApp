package io.App.UserManagementService.business.catalogs;

import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.App.UserManagementService.business.User;
import io.App.UserManagementService.databaseConnection.UserDatabaseConnection;
import io.App.UserManagementService.exceptions.InternalAppException;
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
	 * 
	 * @return
	 * @throws InternalAppException
	 */
	public List<User> getUsers() throws InternalAppException {
		return uDC.getUsersFromDatabase();
	}

	/**
	 * Add a user to the system
	 * 
	 * @param user - user to add
	 * @throws UserAlreadyExistsException - in case the user already exists
	 * @throws InternalAppException - in case there is an internal error with
	 * the Application
	 */
	public void addUser(User user)
			throws UserAlreadyExistsException, InternalAppException {
		uDC.addUser(user);
	}

	/**
	 * Remove a user from the system
	 * 
	 * @param user - user to remove
	 * @throws UserDoesNotExistException - in case the user doesn't exist
	 * @throws InternalAppException - in case there is an internal error with
	 * the Application
	 */
	public void removeUser(int userId) throws InternalAppException {
		uDC.removeUser(userId);
	}

	/**
	 * Gets a user with a given id
	 * 
	 * @param uID - id of the user to get
	 * @return - the user with the given id
	 * @throws UserDoesNotExistException
	 * @throws InternalAppException
	 */
	public User getUserInfo(int id)
			throws InternalAppException, UserDoesNotExistException {
		return uDC.getUserById(id);
	}

	/**
	 * Gets a user with a given name
	 * 
	 * @param uName - the name of the user to get
	 * @return - the user with the given name
	 * @throws InternalAppException - in case there is an internal error with
	 * the Application
	 * @throws UserDoesNotExistException
	 */
	public User getUserByName(String uName)
			throws InternalAppException, UserDoesNotExistException {
		return uDC.getUserByName(uName);
	}

}
