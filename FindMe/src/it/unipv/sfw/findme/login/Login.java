package it.unipv.sfw.findme.login;


	import java.io.*;
	import java.sql.*;
	import java.util.*;
	import DataBase.DBConnection;
	import Exceptions.ExceptionFrame;

	public class Login {

		public Login (String emailOrID, char[] password, LoginGUI frame) {
			String passwordString=new String(password);
			int ID=0;
			String type="";
			String name="";
			String lastName="";
			String email="";
			try {

				Connection conn=DBConnection.connect();

				String query="select * from GeneralUser where User_Code=? and Password=? or Email=? and Password=?";
				PreparedStatement preparedStmt = conn.prepareStatement(query);

				preparedStmt.setString(1, emailOrID);
				preparedStmt.setString(2, passwordString);
				preparedStmt.setString(3, emailOrID);
				preparedStmt.setString(4, passwordString);

				ResultSet result=preparedStmt.executeQuery();

				result.next();
				ID=result.getInt(1);
				type=result.getString(2);
				name=result.getString(3);
				lastName=result.getString(4);
				email=result.getString(5);

			} catch (Exception e1) {
				//new ExceptionFrame("\u274C Wrong Email or Password");
				return;
			}


			try {
				Properties config= new Properties();
				FileInputStream fis=new FileInputStream("Resources/Property/config.properties");
				config.load(fis);
				GeneralUser tempUser=selectType(name, lastName, Integer.toString(ID), email, passwordString, type, config);
				tempUser.GUI(frame);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("errore lettura properties");
			}
		}

		public GeneralUser selectType(String name, String lastName, String ID, String email, String password, String user, Properties property) throws Exception{
			String userClassName=property.getProperty(user);
			GeneralUser r=(GeneralUser)Class.forName(userClassName).getDeclaredConstructor(String.class, String.class, String.class, String.class, String.class).newInstance(name, lastName, ID, email, password);
			return r;
		}
	}

}
