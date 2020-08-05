package io.App.RoleCatalogService.roleComponent;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.App.RoleCatalogService.databaseConnection.RoleDatabaseManagement;
import io.App.RoleCatalogService.dto.RoleListWrapper;
import io.App.RoleCatalogService.exceptions.InternalAppException;
import io.App.RoleCatalogService.exceptions.RoleAlreadyExistsException;

@SpringBootApplication
public class RoleCatalog {

	private RoleDatabaseManagement rDC;

	public RoleCatalog() {
		this.rDC = new RoleDatabaseManagement();
	}

	public RoleListWrapper getRoleList() throws InternalAppException {
		return this.rDC.getRoleDatabaseList();
	}

	public void addRole(Role role) throws RoleAlreadyExistsException, InternalAppException {
		this.rDC.addRoleToDatabase(role);
	}

	public void removeRole(Role role) throws InternalAppException {
		this.rDC.removeRoleFromDatabase(role);
	}

}
