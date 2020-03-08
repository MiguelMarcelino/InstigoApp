package io.App.CommunityService.communityComponent;
import java.util.HashMap;
import java.util.ArrayList;

import io.App.CommunityService.databaseConnection.*;
import io.App.CommunityService.dto.CommunityListWrapper;

public class CommunityCatalog {
	
	private HashMap<Integer, Community> communities;
	private CommunityDatabaseConnection cDC;
	
	public CommunityCatalog() {
		cDC = new CommunityDatabaseConnection(); 
		CommunityListWrapper cLW = cDC.getCommunityDatabaseList(); //tentar tornar static
		ArrayList<Community> communityList = cLW.getList();
		for (int i = 0; i < communityList.size(); i++) {
			communities.put(communityList.get(i).getId(), communityList.get(i));
		}
	}

	public CommunityListWrapper getCommunityList() {
		CommunityListWrapper cLW = new CommunityListWrapper();
		ArrayList<Community> communityList = new ArrayList<Community>(communities.values());
		cLW.setList(communityList);
		return cLW;
	}

	public void addCommunity(Community c) {
		if(!communities.containsKey(c.getId())) {
			communities.put(c.getId(), c);
			//inserir na base de dados nao devia ser sempre
			cDC.addCommunityToDatabase(c);						//VER ISTO!
		}
	}

	public void removeCommunity(Community c) {
		if(communities.containsKey(c.getId())) {
			communities.remove(c.getId());
			//remover da base de dados
			cDC.removeCommunityFromDatabase(c);					//VER ISTO!
		}
	}

	public Community getCommunityById(int cID) {
		return communities.get(cID);
	}
	
	
	

}
