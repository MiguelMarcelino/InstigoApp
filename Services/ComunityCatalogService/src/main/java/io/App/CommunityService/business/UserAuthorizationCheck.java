package io.App.CommunityService.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.App.CommunityService.business.catalogs.OperationsCatalog;
import io.App.CommunityService.business.catalogs.UserCatalog;
import io.App.CommunityService.business.exceptions.InternalAppException;
import io.App.CommunityService.business.exceptions.NonExistantOperationException;
import io.App.CommunityService.business.exceptions.UserDoesNotExistException;
import io.App.CommunityService.business.exceptions.UserNotAuthorizedException;

@SpringBootApplication
public class UserAuthorizationCheck {

	private static final String CREATE_COMMUNITY_OP_NAME = "CREATE_COMMUNITY";
	private static final String DELETE_COMMUNITY_OP_NAME = "DELETE_COMMUNITY";
	private static final String DELETE_ALL_COMMUNITY_OP_NAME = "DELETE_ALL_COMMUNITY";

	@Autowired
	private UserCatalog userCatalog;
	@Autowired
	private OperationsCatalog operationsCatalog;

	public UserAuthorizationCheck() {
	}

	public void checkCreateCommunityAuthorization(int communityCreatorId)
			throws InternalAppException, UserDoesNotExistException,
			UserNotAuthorizedException, NonExistantOperationException {
		// future iteration --> check JWT token

		User user = userCatalog.getUserById(communityCreatorId);
		List<Operation> operations = operationsCatalog.getOperationsForRoleID(user.getRoleId());

		if (operations.contains(operationsCatalog
				.getOperationByName(CREATE_COMMUNITY_OP_NAME))) {
			throw new UserNotAuthorizedException();
		}
	}

	public void checkDeleteCommunityAuthorization(int communityOwnerId,
			int loggedInUserId)
			throws InternalAppException, UserDoesNotExistException,
			UserNotAuthorizedException, NonExistantOperationException {
		// future iteration --> check JWT token

		User communityOwner = userCatalog.getUserById(communityOwnerId);
		User currentUser = userCatalog.getUserById(loggedInUserId);
		List<Operation> operations = operationsCatalog.getOperationsForRoleID(currentUser.getRoleId());

		// the user can delete a community either:
		// 1. If he can execute the operation DELETE and is the
		// creator of that community or
		// 2. If he can execute the operation DELETE_ALL
		if (!((operations.contains(
				operationsCatalog.getOperationByName(DELETE_COMMUNITY_OP_NAME))
				&& currentUser.equals(communityOwner))
				|| (operations.contains(operationsCatalog
						.getOperationByName(DELETE_ALL_COMMUNITY_OP_NAME))))) {
			throw new UserNotAuthorizedException();
		}
	}

}
