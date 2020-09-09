package io.App.CommunityService.business.catalogs;

import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.App.CommunityService.business.Community;
import io.App.CommunityService.business.exceptions.CommunityAlreadyExistsException;
import io.App.CommunityService.business.exceptions.InternalAppException;
import io.App.CommunityService.databaseAccess.CommunityDatabaseConnection;

@SpringBootApplication
public class CommunityCatalog {

	private CommunityDatabaseConnection communityDBConnection;

	public CommunityCatalog() {
		communityDBConnection = new CommunityDatabaseConnection();
	}

	public List<Community> getCommunityList() throws InternalAppException {
		return communityDBConnection.getCommunityDatabaseList();
	}

	public List<Community> getCommunityListWithSubInfo(int uID)
			throws InternalAppException {
		return communityDBConnection.getCommunityListWithSubInfo(uID);
	}

	public void addCommunity(Community c)
			throws CommunityAlreadyExistsException, InternalAppException {
		communityDBConnection.addCommunityToDatabase(c);
	}

	public void removeCommunity(Community c) throws InternalAppException {
		communityDBConnection.removeCommunityFromDatabase(c);
	}

	public Community getCommunityById(int cID) throws InternalAppException {
		Community community = communityDBConnection.getCommunityById(cID);
		return community;
	}

	public Community getCommunityByName(String cName)
			throws InternalAppException {
		Community community = communityDBConnection.getCommunityByName(cName);
		return community;
	}

	public List<Community> userCreatedCommunities(String ownerUserName)
			throws InternalAppException {
		return communityDBConnection.userCreatedCommunities(ownerUserName);
	}

}
