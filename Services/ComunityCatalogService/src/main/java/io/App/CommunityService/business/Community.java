package io.App.CommunityService.business;

public class Community {

	private int id;
	private String name;
	private String description;
	private User communityOwner;
	private boolean isRegistered;
	
	public Community() {
		// For REST only
	}

	public Community(int id, String name, String description,
			User communityOwner) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.communityOwner = communityOwner;
	}

	public Community(String name, String description,
			User communityOwner) {
		this.name = name;
		this.description = description;
		this.communityOwner = communityOwner;
	}

	public Community(int id, String name, String description, User user,
			boolean isRegistered) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.communityOwner = user;
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
		return communityOwner;
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
		this.communityOwner = user;
	}
	
	public void setIsRegistered(boolean isRegistered) {
		this.isRegistered = isRegistered;
	}

}
