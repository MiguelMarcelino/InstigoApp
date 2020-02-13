package io.App.UserCatalogService;

import java.util.List;

//Esta classe foi criada visto que no RegisterService o 
//restTemplate.getForObject nao aceita listas
public class UserListWrapper {

	private List<User> list;

	public List<User> getList() {
		return list;
	}

	public void setList(List<User> someList) {
		this.list = someList;
	}

}