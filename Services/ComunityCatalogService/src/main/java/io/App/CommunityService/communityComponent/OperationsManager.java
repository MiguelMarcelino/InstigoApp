package io.App.CommunityService.communityComponent;

import java.util.List;
import java.util.Optional;

import io.App.CommunityService.databaseConnection.OperationsDatabaseConnection;
import io.App.CommunityService.exceptions.InternalAppException;
import io.App.CommunityService.exceptions.NonExistantOperation;

public class OperationsManager {

	private OperationsDatabaseConnection oDC;
	private List<Operation> operations;

	public OperationsManager() {
		getOperationsFromDB();
	}

	private void getOperationsFromDB() {
		try {
			this.operations = oDC.getAllOperations();
		} catch (InternalAppException e) {
			System.err.println("Error while getting operations");
		}
	}

	public Operation getOperationByName(String name) throws NonExistantOperation {
		Optional<Operation> op = this.operations.stream()
				.filter(operation -> operation.getName().equals(name))
				.findAny();

		if (!op.isPresent()) {
			throw new NonExistantOperation();
		}

		return op.get();
	}
}
