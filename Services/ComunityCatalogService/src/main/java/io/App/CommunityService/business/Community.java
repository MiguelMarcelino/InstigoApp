package io.App.CommunityService.business;

public class Community {

	private int id;
	private String name;
	private String description;
	private int communityOwnerId;
	private boolean isRegistered;

	public Community() {
		// For REST only
	}

	/**
	 * Community constructor when retreiving a community from the database
	 * 
	 * @param id
	 * @param name
	 * @param description
	 * @param communityOwnerId
	 */
	public Community(int id, String name, String description,
			int communityOwnerId) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.communityOwnerId = communityOwnerId;
	}

	/**
	 * Constructor used when returning community requiring extra information
	 * about community subscription for the sending user
	 * 
	 * @param id
	 * @param name
	 * @param description
	 * @param communityOwnerId
	 * @param isRegistered
	 */
	public Community(int id, String name, String description,
			int communityOwnerId, boolean isRegistered) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.communityOwnerId = communityOwnerId;
		this.isRegistered = isRegistered;
	}

	/**
	 * Constructor used when inserting new community in database
	 * 
	 * @param name
	 * @param description
	 * @param communityOwnerId
	 */
	public Community(String name, String description, int communityOwnerId) {
		this.name = name;
		this.description = description;
		this.communityOwnerId = communityOwnerId;
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

	public int getCommunityOwnerId() {
		return communityOwnerId;
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

	public void setCommunityOwner(int communityOwnerId) {
		this.communityOwnerId = communityOwnerId;
	}

	public void setIsRegistered(boolean isRegistered) {
		this.isRegistered = isRegistered;
	}

}
