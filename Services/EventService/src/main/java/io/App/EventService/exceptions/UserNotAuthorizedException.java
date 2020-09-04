package io.App.EventService.exceptions;

public class UserNotAuthorizedException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserNotAuthorizedException() {
		super("The user is unauthorized for that operation");
	}
}
