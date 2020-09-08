package io.App.CommunityService.business;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.App.CommunityService.business.catalogs.OperationsCatalog;
import io.App.CommunityService.databaseAccess.OperationsDatabaseConnection;
import io.App.CommunityService.databaseAccess.UserDatabaseConnection;
import io.App.CommunityService.facade.exceptions.InternalAppException;
import io.App.CommunityService.facade.exceptions.NonExistantOperationException;
import io.App.CommunityService.facade.exceptions.UserDoesNotExistException;
import io.App.CommunityService.facade.exceptions.UserNotAuthorizedException;

@SpringBootApplication
public class UserAuthorizationCheck {

	private static final String CREATE_OPERATION_NAME = "CREATE";
	private static final String DELETE_OPERATION_NAME = "DELETE";
	private static final String DELETE_ALL_OPERATION_NAME = "DELETE_ALL";

	private UserDatabaseConnection uDC;
	private OperationsDatabaseConnection oDC;
	private OperationsCatalog oM;

	public UserAuthorizationCheck() {
		uDC = new UserDatabaseConnection();
		oM = new OperationsCatalog();
	}

	public void checkCreateAuthorization(User communityOwner)
			throws InternalAppException, UserDoesNotExistException,
			UserNotAuthorizedException, NonExistantOperationException {
		// future iteration --> check JWT token

		User user = getUserById(communityOwner.getId());
		user.getRole().setOperations(oDC.getOperationsForRole(user.getRole().getId()));

		if (!user.getRole().getOperations()
				.contains(oM.getOperationByName(CREATE_OPERATION_NAME))) {
			throw new UserNotAuthorizedException();
		}
	}

	public void checkDeleteAuthorization(User communityOwner, User loggedInUser)
			throws InternalAppException, UserDoesNotExistException,
			UserNotAuthorizedException, NonExistantOperationException {
		// future iteration --> check JWT token

		User user = getUserById(communityOwner.getId());
		user.getRole().setOperations(oDC.getOperationsForRole(user.getRole().getId()));

		// the user can delete a community either:
		// 1. If he can execute the operation DELETE and is the
		// creator of that community or
		// 2. If he can execute the operation DELETE_ALL
		if (!((user.getRole().getOperations()
				.contains(oM.getOperationByName(DELETE_OPERATION_NAME))
				&& loggedInUser.equals(communityOwner))
				|| (user.getRole().getOperations().contains(
						oM.getOperationByName(DELETE_ALL_OPERATION_NAME))))) {
			throw new UserNotAuthorizedException();
		}
	}

	private User getUserById(int id)
			throws InternalAppException, UserDoesNotExistException {
		return uDC.getUserById(id);
	}

}
