package it.unipv.sfw.findme.model.users.general_user;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import it.unipv.sfw.findme.model.database.DBConnection;
import it.unipv.sfw.findme.model.exceptions.ExceptionFrame;
import it.unipv.sfw.findme.model.users.student.Student;

public class UsersDAO {
	
	private Users user;
	
	public void insertUser(Users user) {
		Connection conn=DBConnection.connect();
		try {
			String query="insert into users (User_Code, User_Type, Name, Last_Name, Email, Password)"+"values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);

			preparedStmt.setString(1, user.ID);
			preparedStmt.setString(2, user.type.toString());
			preparedStmt.setString(3, user.name);
			preparedStmt.setString(4, user.lastName);
			preparedStmt.setString(5, user.email);
			preparedStmt.setString(6, user.password);
			
			preparedStmt.execute();
			conn.close();


		} catch (Exception e1) {
			System.out.println("errore query");
			e1.printStackTrace();
		}
	}
	
	public void selectUser(Users user) {
		int ID=0;
		String type="";
		String name="";
		String lastName="";
		String email="";
		try {

			Connection conn=DBConnection.connect();

			String query="select * from users where User_Code=? and Password=? or Email=? and Password=?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);

			preparedStmt.setString(1, user.getEmail());
			preparedStmt.setString(2, user.getPassword());
			preparedStmt.setString(3, user.getEmail());
			preparedStmt.setString(4, user.getPassword());

			ResultSet result=preparedStmt.executeQuery();

			result.next();
			ID=result.getInt(1);
			type=result.getString(2);
			name=result.getString(3);
			lastName=result.getString(4);
			email=result.getString(5);
		} catch (Exception e1) {
			new ExceptionFrame("Wrong Email or Password");
			return;
		}
		
		try {
			Properties config= new Properties();
			FileInputStream fis=new FileInputStream("Resources/Property/config.properties");
			config.load(fis);
			String userClassName=config.getProperty(type);
			Users u=(Users)Class.forName(userClassName).getDeclaredConstructor(String.class, String.class, String.class, String.class, String.class, String.class).newInstance(name, lastName, Integer.toString(ID), email, user.getPassword(), type);
			this.user=u;
		} catch (Exception e) {
		}

	}

	public Users getUser() {
		return user;
	}
	
	public Users checkUser(Users user) throws Exception {
			Connection conn=DBConnection.connect();
			String query="select User_Code, Email from users where User_Code=? or Email=?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, Integer.parseInt(user.getEmail()));
			preparedStmt.setString(2, user.getEmail());
			ResultSet result=preparedStmt.executeQuery();
			result.next();
			return new Student(null, null, result.getString(1), result.getString(1), null, null);
	}	
	
	public ArrayList<String> selectOrderedProf() {
		
		try {
			
			ArrayList<String> l = new ArrayList<String>();
			
			Connection conn=DBConnection.connect();
			String query="select User_Code,Name,Last_Name,COALESCE(c,0) AS numprenotazioni from users\r\n"
					+ "left join\r\n"
					+ "(select User_ID, COUNT(*) as c from solo_booking GROUP BY User_ID) t\r\n"
					+ "on User_Code=t.User_ID\r\n"
					+ " where User_Type='PROFESSOR' \r\n"
					+ " ORDER BY numprenotazioni DESC";
			
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			ResultSet result=preparedStmt.executeQuery();
			
			Integer cont=0;
			
			while(result.next())
			{
				cont++;
				l.add(cont+") Codice:"+result.getString(1)+" Nome:"+result.getString(2)+" Cognome:"+result.getString(3)+" Num Prenotazioni:"+result.getString(4));

			}
			
			return l;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
		
	}

}
