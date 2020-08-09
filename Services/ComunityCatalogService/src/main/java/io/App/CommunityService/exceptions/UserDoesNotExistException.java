package io.App.CommunityService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="User does not exists")
public class UserDoesNotExistException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserDoesNotExistException() {
		super("User does not exists");
	}
}
