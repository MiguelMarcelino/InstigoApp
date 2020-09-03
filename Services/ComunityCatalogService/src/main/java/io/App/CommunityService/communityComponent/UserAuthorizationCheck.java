package io.App.CommunityService.communityComponent;

import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.App.CommunityService.databaseConnection.OperationsDatabaseConnection;
import io.App.CommunityService.databaseConnection.UserDatabaseConnection;
import io.App.CommunityService.exceptions.InternalAppException;
import io.App.CommunityService.exceptions.NonExistantOperation;
import io.App.CommunityService.exceptions.UserDoesNotExistException;
import io.App.CommunityService.exceptions.UserNotAuthorizedException;

@SpringBootApplication
public class UserAuthorizationCheck {

	private static final String CREATE_OPERATION_NAME = "CREATE";
	private static final String DELETE_OPERATION_NAME = "DELETE";
	private static final String DELETE_ALL_OPERATION_NAME = "DELETE_ALL";

	private UserDatabaseConnection uDC;
	private OperationsDatabaseConnection oDC;
	private OperationsManager oM;

	public UserAuthorizationCheck() {
		uDC = new UserDatabaseConnection();
		oM = new OperationsManager();
	}

	public void checkCreateAuthorization(User communityOwner)
			throws InternalAppException, UserDoesNotExistException,
			UserNotAuthorizedException, NonExistantOperation {
		// future iteration --> check JWT token

		int userRoleID = getUserRoleID(communityOwner.getUserName());
		List<Operation> roleOperations = getOperationsByRoleID(userRoleID);
		if (!roleOperations
				.contains(oM.getOperationByName(CREATE_OPERATION_NAME))) {
			throw new UserNotAuthorizedException();
		}
	}

	public void checkDeleteAuthorization(User communityOwner, User loggedInUser)
			throws InternalAppException, UserDoesNotExistException,
			UserNotAuthorizedException, NonExistantOperation {
		// future iteration --> check JWT token

		int userRoleID = getUserRoleID(communityOwner.getUserName());
		List<Operation> roleOperations = getOperationsByRoleID(userRoleID);

		// the user can delete a community either:
		// 1. If he can execute the operation DELETE and is the
		// creator of that community or
		// 2. If he can execute the operation DELETE_ALL
		if (!((roleOperations
				.contains(oM.getOperationByName(DELETE_OPERATION_NAME))
				&& loggedInUser.equals(communityOwner))
				|| (roleOperations.contains(
						oM.getOperationByName(DELETE_ALL_OPERATION_NAME))))) {
			throw new UserNotAuthorizedException();
		}
	}

	private int getUserRoleID(String username)
			throws InternalAppException, UserDoesNotExistException {
		return uDC.getUserRoleID(username);
	}

	private List<Operation> getOperationsByRoleID(int roleID)
			throws InternalAppException {
		return oDC.getOperationsForRole(roleID);
	}
}
