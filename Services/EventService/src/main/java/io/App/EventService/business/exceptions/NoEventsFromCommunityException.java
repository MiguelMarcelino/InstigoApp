package io.App.EventService.business.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No events from chosen community")
public class NoEventsFromCommunityException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public NoEventsFromCommunityException() {
		super("No events from chosen community");
	}

}
