package io.App.UserRoleService.userComponent;

import java.util.ArrayList;
import java.util.HashMap;

import io.App.UserRoleService.databaseManagement.UserDatabaseConnection;
import io.App.UserRoleService.dto.UserListWrapper;

public class UserCatalog {
	
	private HashMap<Integer, User> users;
	private UserDatabaseConnection uDC;

	public UserCatalog() {
		users = new HashMap<Integer, User>();
		uDC = new UserDatabaseConnection();
		ArrayList<User> userList = uDC.getUsers().getList();
		for (int i = 0; i < userList.size(); i++) {
			users.put(userList.get(i).getId(), userList.get(i));
		}
	}

	public UserListWrapper getUsers() {
		UserListWrapper uLW = new UserListWrapper();
		ArrayList<User> userList = new ArrayList<User>(users.values());
		uLW.setList(userList);
		return uLW;
	}

	public void addUser(User user) {
		if(!users.containsKey(user.getId())) {
			users.put(user.getId(), user);
			uDC.addUser(user);					//VER ISTO!
		}
		
	}

	public void removeUser(User user) {
		if(users.containsKey(user.getId())) {
			users.remove(user.getId());
			uDC.removeUser(user);//VER ISTO!
		}
		
	}

	public void getUserInfo(int uID) {
		// TODO
	}
	
	

}
