package io.App.CommunityService.communityComponent;

import io.App.CommunityService.databaseConnection.CommunityDatabaseConnection;
import io.App.CommunityService.dto.CommunityListWrapper;
import io.App.CommunityService.exceptions.CommunityAlreadyExistsException;
import io.App.CommunityService.exceptions.CommunityDoesNotExistException;

public class CommunityCatalog {

	private CommunityDatabaseConnection cDC;

	public CommunityCatalog() {
		cDC = new CommunityDatabaseConnection();
	}

	public CommunityListWrapper getCommunityList() {
		return cDC.getCommunityDatabaseList();
	}

	public void addCommunity(Community c) throws CommunityAlreadyExistsException {
		if (this.cDC.getCommunityById(c.getId()) == null) {
			cDC.addCommunityToDatabase(c);
		} else {
			throw new CommunityAlreadyExistsException();
		}
	}

	public void removeCommunity(Community c) throws CommunityDoesNotExistException {
		if (this.cDC.getCommunityById(c.getId()) != null) {
			cDC.removeCommunityFromDatabase(c);
		} else {
			throw new CommunityDoesNotExistException();
		}
	}

	public Community getCommunityById(int cID) throws CommunityDoesNotExistException {
		Community community = cDC.getCommunityById(cID);
		if (community == null) {
			throw new CommunityDoesNotExistException();
		}
		return community;
	}

}
