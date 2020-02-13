package io.App.UserCatalogService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.client.RestTemplate;

public class User {

	private int id;
	private String name;
	// password

	/**
	 * Existe um construtor so com o nome, visto que o id é atribuido ao user quando		//why?
	 * ele eh introduzido na tabela
	 * 
	 * @param name - o nome do utilizador
	 */
	public User(String name) {
		this.name = name;
	}

	/**
	 * Existe tambem um construtor com id e name para quando queremos ir buscar os
	 * Utilizadores a tabela de utilizadores, visto que ai eles ja incluem o id
	 * 
	 * @param id   - o id do utilizador
	 * @param name - o nome do utilizador
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
