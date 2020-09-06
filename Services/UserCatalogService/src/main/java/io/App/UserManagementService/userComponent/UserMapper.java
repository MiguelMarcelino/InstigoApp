package io.App.UserManagementService.userComponent;

import java.util.ArrayList;
import java.util.List;

import io.App.UserManagementService.dto.UserDTO;

public class UserMapper {

	public static UserDTO userToUserDTOMapper(User user) {
		UserDTO userDTO = new UserDTO(user.getId(), user.getUserName(),
				user.getFirstName(), user.getLastName(), user.getRole(),
				user.getEmail());
		return userDTO;
	}

	public static List<UserDTO> userListToUserDTOListMapper(List<User> users) {
		List<UserDTO> userDTOs = new ArrayList<>();
		for (User user : users) {
			userDTOs.add(new UserDTO(user.getId(), user.getUserName(),
					user.getFirstName(), user.getLastName(), user.getRole(),
					user.getEmail()));
		}

		return userDTOs;
	}

	public static User userDTOToUserMapper(UserDTO userDTO) {
		User user = new User(userDTO.getId(), userDTO.getName(),
				userDTO.getFirstName(), userDTO.getLastName(),
				userDTO.getEmail(), userDTO.getRole());
		return user;
	}

	public static List<User> userDTOListToUserListMapper(
			List<UserDTO> userDTOs) {
		List<User> users = new ArrayList<>();
		for (UserDTO userDTO : userDTOs) {
			users.add(new User(userDTO.getId(), userDTO.getName(),
					userDTO.getFirstName(), userDTO.getLastName(),
					userDTO.getEmail(), userDTO.getRole()));
		}

		return users;
	}
}
