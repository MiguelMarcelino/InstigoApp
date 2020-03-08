package io.App.UserRoleService.dto;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.App.UserRoleService.Community;

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
