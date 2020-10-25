package io.App.CommunityService.business.services;

import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.App.CommunityService.business.Community;
import io.App.CommunityService.business.exceptions.AlreadySubscribedException;
import io.App.CommunityService.business.exceptions.InternalAppException;
import io.App.CommunityService.databaseAccess.CommunitySubscriptionDatabaseConnection;

@SpringBootApplication
public class CommunitySubscriptionService {

	private CommunitySubscriptionDatabaseConnection communitySubDBConnection;

	public CommunitySubscriptionService() {
		this.communitySubDBConnection = new CommunitySubscriptionDatabaseConnection();
	}

	public List<Community> userSubbscribedCommunities(int uID) throws InternalAppException {
		return communitySubDBConnection.userSubCommunities(uID);
	}

	public void subscribeToCommunity(int uID, int cID)
			throws InternalAppException, AlreadySubscribedException {
		communitySubDBConnection.subscribeUserToCommunity(uID, cID);
	}

	public void unsubscribeFromCommunity(int uID, int cID) throws InternalAppException {
		communitySubDBConnection.unsubscribeFromCommunity(uID, cID);
	}	
	
}
