package io.App.CommunityService.communityComponent;

public class Community {

	private int id;
	private String name;
	private String description;
	private User user;
	private boolean isRegistered;
	
	public Community() {
		// For REST only
	}

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

	public Community(int id, String name, String description, User user,
			boolean isRegistered) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.user = user;
		this.isRegistered = isRegistered;
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
	
	public boolean getIsRegistered() {
		return isRegistered;
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
	
	public void setIsRegistered(boolean isRegistered) {
		this.isRegistered = isRegistered;
	}

}
