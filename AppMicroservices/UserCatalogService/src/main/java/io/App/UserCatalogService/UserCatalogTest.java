package io.App.UserCatalogService;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Classe que aceita requests

@RestController
@EnableAutoConfiguration
public class UserCatalogTest {

	public UserCatalogTest() {
		// TODO Auto-generated constructor stub
	}

	@RequestMapping("/userCatalogTest")
	public String test() {
		return "TestOK";
	}

	@RequestMapping("/userCatalogList")
	public UserListWrapper list() {
		UserCatalog uC = new UserCatalog();
		User u1 = new User("fc11111");
		User u2 = new User("fc12345");
		uC.addUser(u1);
		uC.addUser(u2);

		UserListWrapper uLW = new UserListWrapper(); // wrapper para nao haver problemas com o restController no
		// register
		uLW.setListUser(uC.getUserList());
		return uLW;
	}

}
