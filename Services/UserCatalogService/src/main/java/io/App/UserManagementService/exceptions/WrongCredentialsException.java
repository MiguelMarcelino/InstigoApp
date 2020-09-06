package io.App.UserManagementService.exceptions;

public class WrongCredentialsException extends Exception {

	private static final long serialVersionUID = 1L;

	public WrongCredentialsException() {
		super("The credentials don't match.");
	}
}
