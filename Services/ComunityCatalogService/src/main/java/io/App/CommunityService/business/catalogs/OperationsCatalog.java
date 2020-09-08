package io.App.CommunityService.business.catalogs;

import java.util.List;

import io.App.CommunityService.business.Operation;
import io.App.CommunityService.databaseAccess.OperationsDatabaseConnection;
import io.App.CommunityService.facade.exceptions.InternalAppException;
import io.App.CommunityService.facade.exceptions.NonExistantOperationException;

public class OperationsCatalog {

	private OperationsDatabaseConnection oDC;

	public OperationsCatalog() {
		oDC = new OperationsDatabaseConnection();
	}

	public List<Operation> getAllOperations() throws InternalAppException {
		return oDC.getAllOperations();
	}

	public Operation getOperationByName(String name)
			throws NonExistantOperationException, InternalAppException {
		return oDC.getOperationByName(name);
	}
}
