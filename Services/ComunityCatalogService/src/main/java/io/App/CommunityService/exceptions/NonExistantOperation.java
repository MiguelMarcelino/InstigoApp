package io.App.CommunityService.exceptions;

public class NonExistantOperation extends Exception {

	private static final long serialVersionUID = 1L;

	public NonExistantOperation() {
		super("The operation you are trying to get does not exist");
	}
}
