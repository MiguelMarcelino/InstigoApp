package io.App.UserManagementService.userComponent;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.App.UserManagementService.dto.UserLoginModel;
import io.App.UserManagementService.exceptions.InternalAppException;
import io.App.UserManagementService.exceptions.UserDoesNotExistException;
import io.App.UserManagementService.exceptions.WrongCredentialsException;

@SpringBootApplication
public class UserAuthorizationCheck {

	public UserAuthorizationCheck() {
	}

	public void verifyUserLogin(UserLoginModel uMD, User storedUser) throws InternalAppException,
			UserDoesNotExistException, WrongCredentialsException {
		// use hash + salt for password
		if (!storedUser.getPassword().equals(uMD.getPassword())) {
			throw new WrongCredentialsException();
		}
	}
}
