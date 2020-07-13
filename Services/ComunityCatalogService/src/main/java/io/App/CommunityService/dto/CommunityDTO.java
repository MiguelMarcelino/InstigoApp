package io.App.CommunityService.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CommunityDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	@JsonProperty("cID")
	private int cID;
	@JsonProperty("cName")
	private String cName;
	
	public CommunityDTO(int id, String name) {
		this.cID = id;
		this.cName = name;
	}

	public CommunityDTO(String name) {
		this.cName = name;
	}

	public CommunityDTO() {
		// For REST only
	}
	
	public int getcID() {
		return cID;
	}

	public String getName() {
		return cName;
	}

}
