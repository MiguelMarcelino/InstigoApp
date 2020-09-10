package io.App.UserManagementService.business.mappers;

import java.util.ArrayList;
import java.util.List;

import io.App.UserManagementService.business.Operation;
import io.App.UserManagementService.dto.OperationDTO;

public class OperationMapper {

	public static List<OperationDTO> operationListToOperationDTOListMapper(List<Operation> operations) {
		List<OperationDTO> operationDTOs = new ArrayList<>();
		for(Operation op: operations) {
			operationDTOs.add(new OperationDTO(op.getId(), op.getName(), op.getRoleID()));
		}
		return operationDTOs;
	}
}
