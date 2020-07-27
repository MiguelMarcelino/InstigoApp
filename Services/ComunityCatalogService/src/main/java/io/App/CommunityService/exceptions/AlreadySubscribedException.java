package io.App.CommunityService.exceptions;

public class AlreadySubscribedException extends Exception {

	private static final long serialVersionUID = 1L;

	public AlreadySubscribedException(int uID, int cID) {
		super("The user with the id " + uID + " has already "
				+ "subscribed to the community with the id " + cID);
	}
}
