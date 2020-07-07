package io.App.EventService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT, reason="Event already exists")
public class EventAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public EventAlreadyExistsException() {
		super("Event already exists");
	}
}
