package io.App.UserManagementService.dto;

import java.util.ArrayList;

import io.App.UserManagementService.userComponent.User;

//Esta classe foi criada visto que no RegisterService o 
//restTemplate.getForObject nao aceita listas
public class UserListWrapper {

	private ArrayList<User> list;

	public ArrayList<User> getList() {
		return list;
	}

	public void setList(ArrayList<User> someList) {
		this.list = someList;
	}

}