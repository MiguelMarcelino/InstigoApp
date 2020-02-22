package io.App.RoleCatalogService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class RoleCatalogConverter {

	@Autowired
	private RoleCatalog rC;
	
	public RoleCatalogConverter() {
		// TODO
	}

	@RequestMapping("/roleCatalogList")
	public RoleListWrapper list() {
		return rC.getRoleList();
	}
	
	@PostMapping("addRole/role")
	public void addRole(Role role) {
		rC.addRole(role);
	}
	
	@PostMapping("removeRole/role")
	public void removeRole(Role role) {
		rC.removeRole(role);
	}
}
