package io.App.UserRoleService.dto;

import java.util.ArrayList;

import io.App.UserRoleService.roleComponent.Role;

public class RoleListWrapper {

	
	private ArrayList<Role> roleList;
	
	public RoleListWrapper() {
		// For REST only
	}
	
	public ArrayList<Role> getRoleList() {
		return roleList;
	}
	
	public void setRoleList (ArrayList<Role> receivedList) {
		roleList = receivedList;
	}
	
}
