package io.App.CommunityService.business.security;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import io.App.CommunityService.business.Operation;
import io.App.CommunityService.business.User;
import io.App.CommunityService.business.exceptions.InternalAppException;
import io.App.CommunityService.business.exceptions.NonExistantOperationException;
import io.App.CommunityService.business.exceptions.UserDoesNotExistException;
import io.App.CommunityService.business.services.OperationsService;
import io.App.CommunityService.business.services.UserService;

public class CommunityPermissionEvaluator implements PermissionEvaluator {

	@Autowired
	private UserService userCatalog;
	@Autowired
	private OperationsService operationsCatalog;

	public CommunityPermissionEvaluator() {
	}

	@Override
	public boolean hasPermission(Authentication authentication,
			Object targetDomainObject, Object permission) {
		if ((authentication == null) || (targetDomainObject == null)
				|| !(permission instanceof String)) {
			return false;
		}

		if (targetDomainObject instanceof Integer) {
			int userID = (Integer) targetDomainObject;
			String requiredPermission = (String) permission;

			try {
				return hasAuthorization(userID, requiredPermission);
			} catch (InternalAppException | UserDoesNotExistException
					| NonExistantOperationException e) {
				System.err.println(e.getMessage());
				return false;
			}
		}

		return false;
	}

	@Override
	public boolean hasPermission(Authentication authentication,
			Serializable targetId, String targetType, Object permission) {
		// Method not used
		return false;
	}

	private boolean hasAuthorization(int communityCreatorId, String opName)
			throws InternalAppException, UserDoesNotExistException,
			NonExistantOperationException {
		User user = userCatalog.getUserById(communityCreatorId);
		List<Operation> operations = operationsCatalog
				.getOperationsForRoleID(user.getRoleId());

		return operations
				.contains(operationsCatalog.getOperationByName(opName));
	}

}
