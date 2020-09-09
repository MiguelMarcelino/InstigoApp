package io.App.CommunityService.business.catalogs;

import io.App.CommunityService.business.User;
import io.App.CommunityService.business.exceptions.InternalAppException;
import io.App.CommunityService.business.exceptions.UserDoesNotExistException;
import io.App.CommunityService.databaseAccess.UserDatabaseConnection;

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
