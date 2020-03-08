package io.App.UserRoleService.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.App.UserRoleService.dto.CommunityListWrapper;
import io.App.UserRoleService.dto.EventListWrapper;
import io.App.UserRoleService.dto.RoleListWrapper;
import io.App.UserRoleService.dto.UserListWrapper;
import io.App.UserRoleService.roleComponent.Role;
import io.App.UserRoleService.roleComponent.RoleCatalog;
import io.App.UserRoleService.userComponent.User;
import io.App.UserRoleService.userComponent.UserCatalog;
import io.App.UserRoleService.userComponent.UserCommunityEvent;

@RestController
@EnableAutoConfiguration
public class UserRoleController {
	
	@Autowired
	private UserCatalog uC;
	@Autowired
	private RoleCatalog rC;
	@Autowired
	private UserCommunityEvent uCE;

	public UserRoleController() {
		// TODO Auto-generated constructor stub
	}

	@RequestMapping("/userCatalogList")
	public UserListWrapper userList() {
		return uC.getUsers();
	}

	@PostMapping(path="addUser/user", consumes= {"application/json"})
	public void addUser(@RequestBody String userJSON) {
		System.out.println(userJSON);
		ObjectMapper objectMapper = new ObjectMapper();
		User user = null;
		try {
			user = objectMapper.readValue(userJSON, User.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		uC.addUser(user);
	}

	/*
	 * VER ISTO!
	 */
	@RequestMapping("/test")
	public void test() throws IOException {		
		URL url = new URL("http://localhost:8081/addUser/user");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		OutputStream os = conn.getOutputStream();

		ObjectMapper objectMapper = new ObjectMapper();
		User user = new User(1, "Ola");
		System.out.println(objectMapper.writeValueAsString(user));
	}
	
	@PostMapping("removeUser/user")
	public void removeUser(User user) {
		uC.removeUser(user);
	}
	
	@RequestMapping("isRegistered/{uID}/{cID}")
	public void isRegistered(@PathVariable("uID") int uID, @PathVariable("cID") int cID) {
		uCE.isRegistered(uID, cID);
	}
	
	@RequestMapping("getUserInfo/{uID}")
	public void getUserInfo(@PathVariable("uID") int uID) {
		uC.getUserInfo(uID);
	}
	
	
	/////////////UserCommunityEvent
	@RequestMapping("/userSubbedCommunities/{uID}")
	public CommunityListWrapper userSubbedCommunities(@PathVariable("uID") int uID) {
		UserCommunityEvent uCE = new UserCommunityEvent();
		return uCE.userSubCommunities(uID);
	}
	
	@RequestMapping("/eventsFromSubbedCommunities/{uID}")
	public EventListWrapper eventsFromSubbedCommunities(@PathVariable("uID") int uID) {
		return uCE.eventsFromSubCommunities(uID);
	}
	
	/******************************************************/
	/*************************Role*************************/
	/******************************************************/

	@RequestMapping("/roleCatalogList")
	public RoleListWrapper rolelist() {
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
