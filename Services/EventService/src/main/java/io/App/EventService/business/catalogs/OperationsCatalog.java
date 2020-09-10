package io.App.EventService.business.catalogs;

import java.util.List;

import io.App.EventService.business.Operation;
import io.App.EventService.business.exceptions.InternalAppException;
import io.App.EventService.business.exceptions.NonExistantOperationException;
import io.App.EventService.databaseConnection.OperationsDatabaseConnection;

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

	public List<Operation> getOperationsForRoleID(int roleId)
			throws InternalAppException {
		return operationsDBConnection.getOperationsForRoleId(roleId);
	}
}
