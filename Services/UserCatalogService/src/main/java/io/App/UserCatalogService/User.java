package io.App.UserCatalogService;

public class User {

	private int id;
	private String name;
	// seguranca tratada por API externa

	public User() {
		// For REST only
	}

	/**
	 * Existe um construtor so com o nome, visto que o id é atribuido ao user quando
	 * ele eh introduzido na tabela
	 * 
	 * @param name - name of teh User
	 */
	public User(String name) {
		this.name = name;
	}

	/**
	 * Existe tambem um construtor com id e name para quando queremos ir buscar os
	 * Utilizadores a tabela de utilizadores, visto que ai eles ja incluem o id
	 * 
	 * @param id   - id of the user
	 * @param name - name of the user
	 */
	public User(int id, String name) {
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
