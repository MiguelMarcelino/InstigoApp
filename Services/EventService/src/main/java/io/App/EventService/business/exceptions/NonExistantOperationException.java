package io.App.EventService.business.exceptions;

public class NonExistantOperationException extends Exception {

	private static final long serialVersionUID = 1L;

	public NonExistantOperationException() {
		super("The operation you are trying to get does not exist");
	}
}
