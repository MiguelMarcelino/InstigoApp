package io.App.ComunityCatalogService;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommunityListWrapper {
	
	@JsonProperty("list")
	private List<Community> list;
	
    public CommunityListWrapper() {
		// TODO Auto-generated constructor stub
	}

	public List<Community> getList() {
		return list;
	}

	public void setList(List<Community> someList) {
		this.list = someList;
	}
}
