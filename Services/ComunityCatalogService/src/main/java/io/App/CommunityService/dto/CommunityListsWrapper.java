package io.App.CommunityService.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.App.CommunityService.communityComponent.Community;

public class CommunityListsWrapper {

	@JsonProperty("userSubscribedCommunities")
	private List<Community> userSubscribedCommunities;

	@JsonProperty("allCommunities")
	private List<Community> allCommunities;

	public CommunityListsWrapper() {
		// For REST only
	}

	public CommunityListsWrapper(List<Community> userSubscribedCommunities,
			List<Community> allCommunities) {
		this.userSubscribedCommunities = userSubscribedCommunities;
		this.allCommunities = allCommunities;
	}

	public List<Community> getUserSubscribedCommunities() {
		return userSubscribedCommunities;
	}

	public List<Community> getAllCommunities() {
		return allCommunities;
	}

}
