package io.App.CommunityService.facade.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommunityListWrapper {

	@JsonProperty("list")
	private List<CommunityDTO> list;

	public CommunityListWrapper() {
		// For REST only
	}
	
	public CommunityListWrapper(List<CommunityDTO> communityList) {
		this.list = communityList;
	}

	public List<CommunityDTO> getList() {
		return list;
	}

}
