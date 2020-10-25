package io.App.CommunityService.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommunityListWrapper {

	@JsonProperty("list")
	private List<CommunityDTO> communityList;

	public CommunityListWrapper() {
		// For REST only
	}
	
	public CommunityListWrapper(List<CommunityDTO> communityList) {
		this.communityList = communityList;
	}

	public List<CommunityDTO> getList() {
		return communityList;
	}

}
