package io.App.CommunityService.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.App.CommunityService.communityComponent.Community;

public class CommunityListWrapper {

	@JsonProperty("list")
	private ArrayList<Community> list;

	public CommunityListWrapper() {
		// For REST only
	}

	public ArrayList<Community> getList() {
		return list;
	}

	public void setList(ArrayList<Community> someList) {
		this.list = someList;
	}
}
