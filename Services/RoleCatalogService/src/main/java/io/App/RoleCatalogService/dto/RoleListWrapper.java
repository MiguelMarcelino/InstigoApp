package io.App.RoleCatalogService.dto;

import java.util.List;

import io.App.RoleCatalogService.roleComponent.Role;

public class RoleListWrapper {

	
	private List<Role> roleList;
	
	public RoleListWrapper() {
		// For REST only
	}
	
	public List<Role> getRoleList() {
		return roleList;
	}
	
	public void setRoleList (List<Role> receivedList) {
		roleList = receivedList;
	}
	
}
