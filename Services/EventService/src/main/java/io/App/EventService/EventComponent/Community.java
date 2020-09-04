package io.App.EventService.EventComponent;


public class Community {

	private int id;
	private String name;
	private String description;
	private int ownerUserId;
	
	public Community() {
		// For REST only
	}

	public Community(int id, String name, String description,
			int ownerUserId) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.ownerUserId = ownerUserId;
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

	public int getCommunityOwner() {
		return ownerUserId;
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

	public void setCommunityOwner(int ownerUserId) {
		this.ownerUserId = ownerUserId;
	}

}
