package io.App.EventService.EventComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import io.App.EventService.databaseConnection.OperationsDatabaseConnection;
import io.App.EventService.exceptions.InternalAppException;
import io.App.EventService.exceptions.NonExistantOperationException;

public class OperationsManager {

	private OperationsDatabaseConnection oDC;
	private List<Operation> operations;

	public OperationsManager() {
		getOperationsFromDB();
		this.operations = new ArrayList<>();
	}

	private void getOperationsFromDB() {
		try {
			this.operations = oDC.getAllOperations();
		} catch (InternalAppException e) {
			System.err.println("Error while getting operations");
		}
	}

	public Operation getOperationByName(String name) throws NonExistantOperationException {
		Optional<Operation> op = this.operations.stream()
				.filter(operation -> operation.getName().equals(name))
				.findAny();

		if (!op.isPresent()) {
			throw new NonExistantOperationException();
		}

		return op.get();
	}
}
