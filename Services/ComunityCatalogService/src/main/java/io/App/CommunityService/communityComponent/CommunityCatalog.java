package io.App.CommunityService.communityComponent;

import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.App.CommunityService.databaseConnection.CommunityDatabaseConnection;
import io.App.CommunityService.exceptions.CommunityAlreadyExistsException;
import io.App.CommunityService.exceptions.InternalAppException;

@SpringBootApplication
public class CommunityCatalog {

	private CommunityDatabaseConnection cDC;

	public CommunityCatalog() {
		cDC = new CommunityDatabaseConnection();
	}

	public List<Community> getCommunityList() throws InternalAppException {
		return cDC.getCommunityDatabaseList();
	}
	
	public List<Community> getCommunityListWithSubInfo(int uID) throws InternalAppException {
		return cDC.getCommunityListWithSubInfo(uID);
	}

	public void addCommunity(Community c) throws CommunityAlreadyExistsException, InternalAppException {
		cDC.addCommunityToDatabase(c);
	}

	public void removeCommunity(Community c) throws InternalAppException {
		cDC.removeCommunityFromDatabase(c);
	}

	public Community getCommunityById(int cID) throws InternalAppException {
		Community community = cDC.getCommunityById(cID);
		return community;
	}

	public Community getCommunityByName(String cName) throws InternalAppException {
		Community community = cDC.getCommunityByName(cName);
		return community;
	}
	
	public List<Community> userCreatedCommunities(String ownerUserName) throws InternalAppException {
		return cDC.userCreatedCommunities(ownerUserName);
	}

}
