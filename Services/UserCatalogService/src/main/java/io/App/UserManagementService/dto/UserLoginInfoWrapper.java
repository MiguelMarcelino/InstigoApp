package io.App.UserManagementService.dto;

import java.util.List;

public class UserLoginInfoWrapper {

	private List<OperationDTO> allowedOperations;
	
	public UserLoginInfoWrapper() {
		// For REST only
	}
	
	public List<OperationDTO> getAllowedOperations() {
		return allowedOperations;
	}
	
	public void setAllowedOperations(List<OperationDTO> allowedOperations) {
		this.allowedOperations = allowedOperations;
	}
}
