package io.App.CommunityService.business.mappers;

import java.util.ArrayList;
import java.util.List;

import io.App.CommunityService.business.Operation;
import io.App.CommunityService.business.Role;
import io.App.CommunityService.business.User;
import io.App.CommunityService.facade.dto.OperationDTO;
import io.App.CommunityService.facade.dto.RoleDTO;
import io.App.CommunityService.facade.dto.UserDTO;

public class UserMapper {

	public static UserDTO userToUserDTOMapper(User user) {
		UserDTO userDTO = new UserDTO(user.getId(), user.getUserName(),
				user.getFirstName(), user.getLastName(), user.getEmail(),
				new RoleDTO(user.getRole().getId(), user.getRole().getName(),
						operationDTOListToOperationListMapper(
								user.getRole().getOperations())));
		return userDTO;
	}

	public static User userDTOToUserMapper(UserDTO userDTO) {
		User user = new User(userDTO.getId(), userDTO.getUserName(),
				userDTO.getFirstName(), userDTO.getLastName(),
				userDTO.getEmail(),
				new Role(userDTO.getRole().getId(), userDTO.getRole().getName(),
						operationListToOperationDTOListMapper(
								userDTO.getRole().getOperations())));
		return user;
	}

	private static List<OperationDTO> operationDTOListToOperationListMapper(
			List<Operation> operations) {
		List<OperationDTO> operationDTOs = new ArrayList<>();
		for (Operation op : operations) {
			operationDTOs.add(
					new OperationDTO(op.getId(), op.getName(), op.getRoleID()));
		}
		return operationDTOs;
	}

	private static List<Operation> operationListToOperationDTOListMapper(
			List<OperationDTO> operationDTOs) {
		List<Operation> operations = new ArrayList<>();
		for (OperationDTO op : operationDTOs) {
			operations.add(
					new Operation(op.getId(), op.getName(), op.getRoleID()));
		}
		return operations;
	}
}
