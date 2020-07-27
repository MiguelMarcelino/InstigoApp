package io.App.CommunityService.communityComponent;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.App.CommunityService.databaseConnection.UserCommunityDatabaseConnection;
import io.App.CommunityService.dto.CommunityListWrapper;
import io.App.CommunityService.exceptions.AlreadySubscribedException;
import io.App.CommunityService.exceptions.InternalAppException;

@SpringBootApplication
public class UserCommunityCatalog {

	private UserCommunityDatabaseConnection uCDC;

	public UserCommunityCatalog() {
		this.uCDC = new UserCommunityDatabaseConnection();
	}

	public CommunityListWrapper userSubbscribedCommunities(int uID) {
		return uCDC.userSubCommunities(uID);
	}

	public void isRegToCommunity(int uID, int cID) {

	}

	public void subscribeToCommunity(int uID, int cID)
			throws InternalAppException, AlreadySubscribedException {
		uCDC.subscribeUserToCommunity(uID, cID);
	}
}
