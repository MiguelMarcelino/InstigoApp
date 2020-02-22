package io.App.UserCatalogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class UserCatalogController {
	
	@Autowired
	private UserCatalog uC;
	@Autowired
	private UserCommunityEvent uCE;

	public UserCatalogController() {
		// TODO Auto-generated constructor stub
	}

	@RequestMapping("/userCatalogList")
	public UserListWrapper list() {
		return uC.getUsers();
	}

	@PostMapping("addUser/user")
	public void addUser(User user) {
		uC.addUser(user);
	}
	
	@PostMapping("removeUser/user")
	public void removeUser(User user) {
		uC.removeUser(user);
	}
	
	@RequestMapping("isRegistered/{uID}/{cID}")
	public void isRegistered(@PathVariable("uID") int uID, @PathVariable("cID") int cID) {
		uC.isRegistered(uID, cID);
	}
	
	@RequestMapping("getUserInfo/{uID}")
	public void getUserInfo(@PathVariable("uID") int uID) {
		uC.getUserInfo(uID);
	}
	
	/******************************************************/
	/******************************************************/
	/******************************************************/
	
	@RequestMapping("/userSubbedCommunities/{uID}")
	public CommunityListWrapper userSubbedCommunities(@PathVariable("uID") int uID) {
		UserCommunityEvent uCE = new UserCommunityEvent();
		return uCE.userSubCommunities(uID);
	}
	
	@RequestMapping("/eventsFromSubbedCommunities/{uID}")
	public EventListWrapper eventsFromSubbedCommunities(@PathVariable("uID") int uID) {
		return uCE.eventsFromSubCommunities(uID);
	}


}
