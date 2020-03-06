package io.App.ComunityCatalogService.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.App.ComunityCatalogService.Community;

public class CommunityListWrapper {

	@JsonProperty("list")
	private List<Community> list;

	public CommunityListWrapper() {
		// For REST only
	}

	public List<Community> getList() {
		return list;
	}

	public void setList(List<Community> someList) {
		this.list = someList;
	}
}
