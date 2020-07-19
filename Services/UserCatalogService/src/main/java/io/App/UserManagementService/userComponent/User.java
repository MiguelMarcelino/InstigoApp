package io.App.UserManagementService.userComponent;

public class User {

	private int id;
	private String uName;
	private String firstName;
	private String lastName;
	private String email;
	private String password;

	public User() {
		// For REST only
	}

	/**
	 * 
	 * @param id
	 *            - id of the user
	 * @param name
	 *            - name of the user
	 */
	public User(int id, String name, String firstName, String lastName, String email, String password) {
		this.id = id;
		this.uName = name;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	/**
	 * This contructor is used to remove users from the system
	 * @param getuName
	 * @param password2
	 */
	public User(String getuName, String password2) {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return uName;
	}

	public String getEmail() {
		return this.email;
	}

	public String getPassword() {
		return password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}

}
