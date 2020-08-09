package io.App.CommunityService.communityComponent;

public class Community {

	private int id;
	private String name;
	private String description;
	private String ownerUserName;

	public Community(int id, String name, String description, String ownerUserName) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.ownerUserName = ownerUserName;
	}
	
	public Community(String name, String description, String ownerUserName) {
		this.name = name;
		this.description = description;
		this.ownerUserName = ownerUserName;
	}


	public Community() {
		// For REST only
	}


	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getOwnerUserName() {
		return ownerUserName;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setOwnerId(String ownerUserName) {
		this.ownerUserName = ownerUserName;
	}
}
