package io.App.EventService.business.catalogs;

import io.App.EventService.business.User;
import io.App.EventService.business.exceptions.InternalAppException;
import io.App.EventService.business.exceptions.UserDoesNotExistException;
import io.App.EventService.databaseConnection.UserDatabaseConnection;

public class UserCatalog {

	private UserDatabaseConnection userDBConnection;

	public UserCatalog() {
		userDBConnection = new UserDatabaseConnection();
	}

	public User getUserById(int uID)
			throws InternalAppException, UserDoesNotExistException {
		return userDBConnection.getUserById(uID);
	}

}
