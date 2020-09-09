package io.App.CommunityService.business.exceptions;

public class CommunityAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public CommunityAlreadyExistsException(String cName) {
		super("The community with the name '" + cName + "' already exists.");
	}


}
