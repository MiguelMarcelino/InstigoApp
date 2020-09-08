package io.App.CommunityService.business.catalogs;

import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.App.CommunityService.business.Community;
import io.App.CommunityService.databaseAccess.CommunityDatabaseConnection;
import io.App.CommunityService.facade.exceptions.CommunityAlreadyExistsException;
import io.App.CommunityService.facade.exceptions.InternalAppException;

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
