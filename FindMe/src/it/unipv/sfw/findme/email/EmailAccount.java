package it.unipv.sfw.findme.email;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import it.unipv.sfw.findme.database.DBConnection;


public class EmailAccount {
	
	private String email;
	private String password;
	
	public EmailAccount(){
		
		try {

			Connection conn=DBConnection.connect();

			String query="select * from email";
			Statement statement = conn.prepareStatement(query);
			ResultSet result=statement.executeQuery(query);
			result.next();
			
			this.email=result.getString(1);
			this.password=result.getString(2);
			
			conn.close();
			
		} catch (Exception e1) {
			return;
		}

	}

	
	public String getEmail() {
		return this.email;
	}
	
	public String getPassword() {
		return this.password;
	}


}