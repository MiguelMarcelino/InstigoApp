package io.App.RoleCatalogService;

import java.util.List;

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
