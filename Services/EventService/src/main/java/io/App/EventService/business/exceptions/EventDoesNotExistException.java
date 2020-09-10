package io.App.EventService.business.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Event does not exist")
public class EventDoesNotExistException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public EventDoesNotExistException() {
		super("Event does not exist");
	}

}
