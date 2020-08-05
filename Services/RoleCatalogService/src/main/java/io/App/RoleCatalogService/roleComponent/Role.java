package io.App.RoleCatalogService.roleComponent;

public class Role {

	private int id;
	private String name;
	private int communityID;
	private int authLevel;

	public Role(int id, String name, int communityID, int authLevel) {
		this.id = id;
		this.name = name;
		this.communityID = communityID;
		this.authLevel = authLevel;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getCommunityID() {
		return communityID;
	}

	public int getAuthLevel() {
		return authLevel;
	}

}
