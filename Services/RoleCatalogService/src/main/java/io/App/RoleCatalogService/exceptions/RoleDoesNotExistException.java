package io.App.RoleCatalogService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Role does not exist")
public class RoleDoesNotExistException extends Exception {

	private static final long serialVersionUID = 1L;

	public RoleDoesNotExistException() {
		super("Role does not exist");
	}
}
