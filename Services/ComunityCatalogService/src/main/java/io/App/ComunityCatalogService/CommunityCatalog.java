package io.App.ComunityCatalogService;
import java.util.HashMap;
import java.util.List;

import io.App.ComunityCatalogService.databaseConnection.*;
import io.App.ComunityCatalogService.dto.CommunityListWrapper;

public class CommunityCatalog {
	
	private HashMap<Integer, Community> communities;
	
	public CommunityCatalog() {
		CommunityDatabaseConnection cDC = new CommunityDatabaseConnection(); 
		CommunityListWrapper cLW = cDC.getCommunityDatabaseList(); //tentar tornar static
		List<Community> communityList = cLW.getList();
		for (int i = 0; i < communityList.size(); i++) {
			communities.put(communityList.get(i).getId(), communityList.get(i));
		}
	}
	
	

}
