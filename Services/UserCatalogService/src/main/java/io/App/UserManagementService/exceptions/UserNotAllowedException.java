package io.App.UserManagementService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.FORBIDDEN, reason="User does not exists")
public class UserNotAllowedException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserNotAllowedException() {
		super("This operation is not allowed by this user");
	}
}
