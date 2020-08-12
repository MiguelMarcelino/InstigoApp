package io.App.CommunityService.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.App.CommunityService.communityComponent.Community;

public class CommunityListWrapper {

	@JsonProperty("list")
	private List<Community> list;

	public CommunityListWrapper() {
		// For REST only
	}
	
	public CommunityListWrapper(List<Community> communityList) {
		this.list = communityList;
	}

	public List<Community> getList() {
		return list;
	}

	public void setList(List<Community> someList) {
		this.list = someList;
	}
}
