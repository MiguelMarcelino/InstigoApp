package io.App.EventService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT, reason="Internal Error")
public class InternalAppException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public InternalAppException() {
		super("Internal Error");
	}
}
