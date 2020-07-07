package io.App.RoleCatalogService.roleComponent;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.App.RoleCatalogService.databaseConnection.RoleDatabaseConnection;
import io.App.RoleCatalogService.dto.RoleListWrapper;
import io.App.RoleCatalogService.exceptions.RoleAlreadyExistsException;
import io.App.RoleCatalogService.exceptions.RoleDoesNotExistException;

@SpringBootApplication
public class RoleCatalog {

	private RoleDatabaseConnection rDC;

	public RoleCatalog() {
		this.rDC = new RoleDatabaseConnection();
	}

	public RoleListWrapper getRoleList() {
		return this.rDC.getRoleDatabaseList();
	}

	public void addRole(Role role) throws RoleAlreadyExistsException {
		if (this.rDC.getRoleByName(role.getName()) != null) {
			throw new RoleAlreadyExistsException();
		}
		this.rDC.addRoleToDatabase(role);
	}

	public void removeRole(Role role) throws RoleDoesNotExistException {
		if (this.rDC.getRoleByName(role.getName()) == null) {
			throw new RoleDoesNotExistException();
		}
		this.rDC.removeRoleFromDatabase(role);
	}

}
