package io.App.UserManagementService.dto;

import java.util.ArrayList;
import java.util.List;

//Esta classe foi criada visto que no RegisterService o 
//restTemplate.getForObject nao aceita listas
public class UserListWrapper {

	private List<UserDTO> userDTOList;

	public UserListWrapper(List<UserDTO> userDTOs) {
		this.userDTOList = userDTOs;
	}
	
	public List<UserDTO> getList() {
		return userDTOList;
	}

	public void setList(ArrayList<UserDTO> someList) {
		this.userDTOList = someList;
	}

}