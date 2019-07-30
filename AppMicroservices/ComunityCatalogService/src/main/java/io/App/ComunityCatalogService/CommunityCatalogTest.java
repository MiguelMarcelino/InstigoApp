package io.App.ComunityCatalogService;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommunityCatalogTest {

	@RequestMapping("/communityCatalogTest")
	public String home() {
		return "Ola";
	}

	@RequestMapping("/communityCatalogList")
	public List<Community> communityList() {
		CommunityCatalog cC = new CommunityCatalog();
		Community c1 = new Community(1, "ASC");
		Community c2 = new Community(2, "LASIGE");
		cC.addCommunity(c1);
		cC.addCommunity(c2);
		return cC.getCommunityList();
	}
}
