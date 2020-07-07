package io.App.CommunityService.dto;

import java.io.Serializable;

public class CommunityDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private int id;
	private String name;

	public CommunityDTO(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public CommunityDTO() {
		// For REST only
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
