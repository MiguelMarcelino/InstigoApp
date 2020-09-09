package io.App.CommunityService.business.catalogs;

import java.util.List;

import io.App.CommunityService.business.Operation;
import io.App.CommunityService.business.exceptions.InternalAppException;
import io.App.CommunityService.business.exceptions.NonExistantOperationException;
import io.App.CommunityService.databaseAccess.OperationsDatabaseConnection;

public class OperationsCatalog {

	private OperationsDatabaseConnection operationsDBConnection;

	public OperationsCatalog() {
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
		return operationsDBConnection.getOperationsForRole(roleId);
	}
}
