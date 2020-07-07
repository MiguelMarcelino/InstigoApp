package io.App.RoleCatalogService.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.App.RoleCatalogService.dto.RoleListWrapper;
import io.App.RoleCatalogService.exceptions.RoleAlreadyExistsException;
import io.App.RoleCatalogService.exceptions.RoleDoesNotExistException;
import io.App.RoleCatalogService.roleComponent.Role;
import io.App.RoleCatalogService.roleComponent.RoleCatalog;

@RestController
@EnableAutoConfiguration
public class RoleCatalogController {

	@Autowired
	private RoleCatalog rC;
	
	public RoleCatalogController() {
		// TODO
	}

	@RequestMapping("/roleCatalogList")
	public RoleListWrapper list() {
		return rC.getRoleList();
	}
	
	@PostMapping("addRole/role")
	public void addRole(Role role) throws RoleAlreadyExistsException {
		rC.addRole(role);
	}
	
	@PostMapping("removeRole/role")
	public void removeRole(Role role) throws RoleDoesNotExistException {
		rC.removeRole(role);
	}
}
