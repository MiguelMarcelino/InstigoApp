package io.App.CommunityService.communityComponent;

public class Community {

	private int id;
	private String name;
	private String description;

	public Community(int id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public Community(String name, String description) {
		this.name = name;
		this.description = description;
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

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
