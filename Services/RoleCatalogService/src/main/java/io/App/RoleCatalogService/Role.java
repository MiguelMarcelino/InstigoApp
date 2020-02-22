package io.App.RoleCatalogService;

public class Role {

	private int id;
	private String name;
	private int authLevel;

	public Role(int id, String name, int authLevel) {
		this.id = id;
		this.name = name;
		this.authLevel = authLevel;
	}
	
	public Role(String name, int authLevel) {
		this.name = name;
		this.authLevel = authLevel;
	}
	
	public Role () {
		//For REST only
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getAuthLevel() {
		return authLevel;
	}
}
