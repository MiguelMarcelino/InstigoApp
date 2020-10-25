package io.App.CommunityService.business.services;

import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.App.CommunityService.business.Operation;
import io.App.CommunityService.business.exceptions.InternalAppException;
import io.App.CommunityService.business.exceptions.NonExistantOperationException;
import io.App.CommunityService.databaseAccess.OperationsDatabaseConnection;

@SpringBootApplication
public class OperationsService {

	private OperationsDatabaseConnection operationsDBConnection;

	public OperationsService() {
		operationsDBConnection = new OperationsDatabaseConnection();
	}

	public List<Operation> getAllOperations() throws InternalAppException {
		return operationsDBConnection.getAllOperations();
	}

	public Operation getOperationByName(String name)
			throws NonExistantOperationException, InternalAppException {
		return operationsDBConnection.getOperationByName(name);
	}

	public List<Operation> getOperationsForRoleID(int roleId) throws InternalAppException {
		return operationsDBConnection.getOperationsForRoleID(roleId);
	}
}
