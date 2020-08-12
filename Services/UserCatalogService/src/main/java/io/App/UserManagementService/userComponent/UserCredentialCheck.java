package io.App.UserManagementService.userComponent;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.App.UserManagementService.databaseConnection.UserDatabaseConnection;
import io.App.UserManagementService.exceptions.InternalAppException;
import io.App.UserManagementService.exceptions.UserDoesNotExistException;
import io.App.UserManagementService.exceptions.UserNotAllowedException;

@SpringBootApplication
public class UserCredentialCheck {

	private UserDatabaseConnection uDC;

	public UserCredentialCheck() {
		uDC = new UserDatabaseConnection();
	}

	public void checkCredentialsAdmin(int userId)
			throws InternalAppException, UserDoesNotExistException, UserNotAllowedException {
		// check user info with information in database
		// in the future also check JWT
		User u = uDC.getUserById(userId);
		if(!u.getRoleName().equals(Role.ADMIN.name()) ) {
			throw new UserNotAllowedException();
		}
	}
}
