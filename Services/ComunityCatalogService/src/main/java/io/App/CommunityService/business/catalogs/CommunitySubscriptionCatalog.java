package io.App.CommunityService.business.catalogs;

import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.App.CommunityService.business.Community;
import io.App.CommunityService.databaseAccess.CommunitySubscriptionDatabaseConnection;
import io.App.CommunityService.facade.exceptions.AlreadySubscribedException;
import io.App.CommunityService.facade.exceptions.InternalAppException;

@SpringBootApplication
public class CommunitySubscriptionCatalog {

	private CommunitySubscriptionDatabaseConnection uCDC;

	public CommunitySubscriptionCatalog() {
		this.uCDC = new CommunitySubscriptionDatabaseConnection();
	}

	public List<Community> userSubbscribedCommunities(int uID) throws InternalAppException {
		return uCDC.userSubCommunities(uID);
	}

	public void subscribeToCommunity(int uID, int cID)
			throws InternalAppException, AlreadySubscribedException {
		uCDC.subscribeUserToCommunity(uID, cID);
	}

	public void unsubscribeFromCommunity(int uID, int cID) throws InternalAppException {
		uCDC.unsubscribeFromCommunity(uID, cID);
	}	
	
}
