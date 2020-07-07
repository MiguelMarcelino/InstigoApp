package io.App.RoleCatalogService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT, reason="Role already exists")
public class RoleAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public RoleAlreadyExistsException() {
		super("Role already exists");
	}
}
