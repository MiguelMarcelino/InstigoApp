package io.App.CommunityService.communityComponent;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.App.CommunityService.databaseConnection.UserDatabaseConnection;
import io.App.CommunityService.exceptions.InternalAppException;
import io.App.CommunityService.exceptions.UserDoesNotExistException;

@SpringBootApplication
public class UserCredentialCheck {
	
	private UserDatabaseConnection uDC;
	
	public UserCredentialCheck() {
		uDC = new UserDatabaseConnection();
	}

	public Role getUserRole(String username) throws InternalAppException, UserDoesNotExistException {
		return uDC.getUserRole(username);
	}
}
