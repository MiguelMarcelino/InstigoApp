package io.App.ComunityCatalogService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommunityCatalogTest {

	@RequestMapping("/communityCatalogTest")
	public String home() {
		return "Ola";
	}

	@RequestMapping("/communityCatalogList")
	public CommunityListWrapper communityList() {
		CommunityCatalog cC = new CommunityCatalog();
		Community c1 = new Community(1, "ASC");
		Community c2 = new Community(2, "LASIGE");
		Community c3 = new Community(3, "SO");
		Community c4 = new Community(4, "SD");
		cC.addCommunity(c1);
		cC.addCommunity(c2);
		cC.addCommunity(c3);
		cC.addCommunity(c4);
		return cC.getCommunityList();
	}
}
