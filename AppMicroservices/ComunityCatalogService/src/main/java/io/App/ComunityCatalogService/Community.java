package io.App.ComunityCatalogService;

public class Community {
	
	private int id;
	private String name;

	public Community(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
