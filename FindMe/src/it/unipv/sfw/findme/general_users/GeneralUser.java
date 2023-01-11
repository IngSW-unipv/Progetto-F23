package it.unipv.sfw.findme.general_users;

public abstract class GeneralUser {
	
	protected String name;
	protected String lastName;
	protected String ID;
	protected String email;
	protected String password;
	
	
	public GeneralUser(String name, String lastName, String iD, String email, String password) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.ID = iD;
		this.email = email;
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getID() {
		return ID;
	}


	public void setID(String iD) {
		ID = iD;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	

}
