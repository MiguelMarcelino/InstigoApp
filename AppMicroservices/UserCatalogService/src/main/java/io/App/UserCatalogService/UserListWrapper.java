package io.App.UserCatalogService;

import java.util.List;

//Esta classe foi criada visto que no RegisterService o 
//restTemplate.getForObject nao aceita listas
public class UserListWrapper {

	private List<User> userList;

	public List<User> getListUser() {
		return userList;
	}

	public void setListUser(List<User> listUser) {
		this.userList = listUser;
	}

}