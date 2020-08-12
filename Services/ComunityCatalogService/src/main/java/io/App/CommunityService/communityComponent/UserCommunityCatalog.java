package io.App.CommunityService.communityComponent;

import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.App.CommunityService.databaseConnection.UserCommunityDatabaseConnection;
import io.App.CommunityService.exceptions.AlreadySubscribedException;
import io.App.CommunityService.exceptions.InternalAppException;

@SpringBootApplication
public class UserCommunityCatalog {

	private UserCommunityDatabaseConnection uCDC;

	public UserCommunityCatalog() {
		this.uCDC = new UserCommunityDatabaseConnection();
	}

	public List<Community> userSubbscribedCommunities(int uID) throws InternalAppException {
		return uCDC.userSubCommunities(uID);
	}
	
	public List<Community> userCreatedCommunities(String ownerUserName) throws InternalAppException {
		return uCDC.userCreatedCommunities(ownerUserName);
	}

	public void isRegToCommunity(int uID, int cID) {

	}

	public void subscribeToCommunity(int uID, int cID)
			throws InternalAppException, AlreadySubscribedException {
		uCDC.subscribeUserToCommunity(uID, cID);
	}

	public void unsubscribeFromCommunity(int uID, int cID) throws InternalAppException {
		uCDC.unsubscribeFromCommunity(uID, cID);
	}	
	
}
