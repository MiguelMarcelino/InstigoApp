package io.App.RoleCatalogService.roleComponent;

public class Role {

	private int id;
	private String name;
	private String communityName;
	private int authLevel;

	public Role(int id, String name, String communityName, int authLevel) {
		this.id = id;
		this.name = name;
		this.communityName = communityName;
		this.authLevel = authLevel;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCommunityName() {
		return communityName;
	}
	
	public int getAuthLevel() {
		return authLevel;
	}

}
