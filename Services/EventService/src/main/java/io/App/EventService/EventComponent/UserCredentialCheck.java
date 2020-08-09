package io.App.EventService.EventComponent;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.App.EventService.databaseConnection.UserDatabaseConnection;
import io.App.EventService.exceptions.InternalAppException;
import io.App.EventService.exceptions.UserDoesNotExistException;

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
