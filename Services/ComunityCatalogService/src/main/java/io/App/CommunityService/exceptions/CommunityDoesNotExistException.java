package io.App.CommunityService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT, reason="This community does not exists.")
public class CommunityDoesNotExistException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public CommunityDoesNotExistException() {
		super("This community does not exists.");
	}
}
