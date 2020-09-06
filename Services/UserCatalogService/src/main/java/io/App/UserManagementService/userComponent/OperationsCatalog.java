package io.App.UserManagementService.userComponent;

import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.App.UserManagementService.databaseConnection.OperationsDatabaseConnection;
import io.App.UserManagementService.exceptions.InternalAppException;

@SpringBootApplication
public class OperationsCatalog {

	private OperationsDatabaseConnection oDC;

	public List<Operation> getOperationsForRoleID(int roleID)
			throws InternalAppException {
		return oDC.getOperationsForRole(roleID);
	}

}
