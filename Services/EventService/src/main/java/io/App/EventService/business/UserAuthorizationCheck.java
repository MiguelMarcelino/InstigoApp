package io.App.EventService.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.App.EventService.business.catalogs.OperationsCatalog;
import io.App.EventService.business.catalogs.UserCatalog;
import io.App.EventService.business.exceptions.InternalAppException;
import io.App.EventService.business.exceptions.NonExistantOperationException;
import io.App.EventService.business.exceptions.UserDoesNotExistException;
import io.App.EventService.business.exceptions.UserNotAuthorizedException;

@SpringBootApplication
public class UserAuthorizationCheck {

	private static final String CREATE_EVENT_OP_NAME = "CREATE_EVENT";
	private static final String DELETE_EVENT_OP_NAME = "DELETE_EVENT";
	private static final String DELETE_ALL_EVENT_OP_NAME = "DELETE_ALL_EVENT";

	@Autowired
	private UserCatalog userCatalog;

	@Autowired
	private OperationsCatalog oM;

	public UserAuthorizationCheck() {
	}

	public void checkCreateEventAuthorization(int communityOwnerId)
			throws InternalAppException, UserDoesNotExistException,
			UserNotAuthorizedException, NonExistantOperationException {
		// future iteration --> check JWT token

		User user = userCatalog.getUserById(communityOwnerId);
		List<Operation> roleOperations = oM
				.getOperationsForRoleID(user.getRoleID());

		if (!roleOperations
				.contains(oM.getOperationByName(CREATE_EVENT_OP_NAME))) {
			throw new UserNotAuthorizedException();
		}
	}

	public void checkDeleteEventAuthorization(int communityOwnerId,
			int loggedInUserId)
			throws InternalAppException, UserDoesNotExistException,
			UserNotAuthorizedException, NonExistantOperationException {
		// future iteration --> check JWT token

		User communityOwner = userCatalog.getUserById(communityOwnerId);
		User currentUser = userCatalog.getUserById(loggedInUserId);

		List<Operation> roleOperations = oM
				.getOperationsForRoleID(currentUser.getRoleID());

		// the user can delete a community either:
		// 1. If he can execute the operation DELETE and is the
		// creator of that community or
		// 2. If he can execute the operation DELETE_ALL
		if (!((roleOperations
				.contains(oM.getOperationByName(DELETE_EVENT_OP_NAME))
				&& currentUser.equals(communityOwner))
				|| (roleOperations.contains(
						oM.getOperationByName(DELETE_ALL_EVENT_OP_NAME))))) {
			throw new UserNotAuthorizedException();
		}
	}

}
