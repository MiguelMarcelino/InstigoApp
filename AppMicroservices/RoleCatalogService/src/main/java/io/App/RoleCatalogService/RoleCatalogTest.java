package io.App.RoleCatalogService;

import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class RoleCatalogTest {

	public RoleCatalogTest() {
		// TODO Auto-generated constructor stub
	}

	@RequestMapping("/roleCatalogTest")
	public String test() {
		return "TestOK";
	}

	@RequestMapping("/roleCatalogList")
	public List<Role> list() {
		RoleCatalog rC = new RoleCatalog();
		Role r1 = new Role(1, "Admin", 1);
		rC.addRole(r1);
		return rC.getRoleList();
	}
}
