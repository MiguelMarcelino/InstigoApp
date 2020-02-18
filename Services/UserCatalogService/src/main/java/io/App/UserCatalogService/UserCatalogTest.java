package io.App.UserCatalogService;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
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
		return "TestOK. Service Online!";
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
		uLW.setList(uC.getUserList());
		return uLW;
	}

	@RequestMapping("/eventsFromSubbedCommunities/{uID}")
	public EventListWrapper eventsFromSubbedCommunities(@PathVariable("uID") int uID) {
		UserCommunityEvent uCE = new UserCommunityEvent();

		// User fc11111 subscribed to community ASC
		
		// User u = new User(1, "fc11111");
		// Community c = new Community(1, "ASC");
		// Role r = new Role(1, "Manager", 5);
		// String dStart = "04-01-2020";
		// String dEnd = "04-01-2021";
		// uCE.addCommunity(u, c, r, dStart, dEnd);

		// Events where created in EventService Microservice

		return uCE.eventsFromSubCommunities(uID);
	}

	@RequestMapping("/userSubbedCommunities/{uID}")
	public CommunityListWrapper userSubbedCommunities(@PathVariable("uID") int uID) {
		UserCommunityEvent uCE = new UserCommunityEvent();
		return uCE.userSubCommunities(uID);
	}

	@RequestMapping("/getUserInfo/{uID}")
	public User getUserInfo(@PathVariable("uID") int uID) {
		UserCatalog uC = new UserCatalog();
		return uC.getUserInfo(uID);
	}

	// @RequestMapping("/getUserEvents")
	// public EventListWrapper getListEventUser() {
	// User user = new User(1, "fc11111");
	// EventListWrapper eLW = new EventListWrapper();
	// eLW.setList(user.notifyUser());
	// return eLW;
	// }

}
