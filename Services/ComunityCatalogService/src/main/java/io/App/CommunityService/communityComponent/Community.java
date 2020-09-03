package io.App.CommunityService.communityComponent;

public class Community {

	private int id;
	private String name;
	private String description;
	private User user;

	public Community(int id, String name, String description,
			User user) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.user = user;
	}

	public Community(String name, String description,
			User user) {
		this.name = name;
		this.description = description;
		this.user = user;
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

	public User getCommunityOwner() {
		return user;
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

	public void setCommunityOwner(User user) {
		this.user = user;
	}
}
