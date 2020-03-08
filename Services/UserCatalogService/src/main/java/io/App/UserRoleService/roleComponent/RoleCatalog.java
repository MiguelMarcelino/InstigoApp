package io.App.UserRoleService.roleComponent;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.App.UserRoleService.databaseManagement.RoleDatabaseConnection;
import io.App.UserRoleService.dto.RoleListWrapper;

@SpringBootApplication
public class RoleCatalog {

	private RoleDatabaseConnection rDC;
	private HashMap<Integer, Role> roles;

	public RoleCatalog() {
		rDC = new RoleDatabaseConnection();
		ArrayList<Role> roleList = rDC.getRoleList().getRoleList();
		for (int i = 0; i < roleList.size(); i++) {
			roles.put(roleList.get(i).getId(), roleList.get(i));
		}
	}

	public RoleListWrapper getRoleList() {
		RoleListWrapper rLW = new RoleListWrapper();
		ArrayList<Role> roleList = new ArrayList<Role>(roles.values());
		rLW.setRoleList(roleList);
		return rLW;
	}

	public void addRole(Role role) {
		if (!roles.containsKey(role.getId())) {
			roles.put(role.getId(), role);
			rDC.addRole(role); 					// VER ISTO!
		}

	}

	public void removeRole(Role role) {
		if (roles.containsKey(role.getId())) {
			roles.remove(role.getId());
			rDC.removeRole(role); 				// VER ISTO!
		}
	}

}
