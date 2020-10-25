package io.App.CommunityService.business.services;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.App.CommunityService.business.User;
import io.App.CommunityService.business.exceptions.InternalAppException;
import io.App.CommunityService.business.exceptions.UserDoesNotExistException;
import io.App.CommunityService.databaseAccess.UserDatabaseConnection;

@SpringBootApplication
public class UserService {

	private UserDatabaseConnection userDBConnection;

	public UserService() {
		userDBConnection = new UserDatabaseConnection();
	}

	public User getUserById(int uID)
			throws InternalAppException, UserDoesNotExistException {
		return userDBConnection.getUserById(uID);
	}

}
