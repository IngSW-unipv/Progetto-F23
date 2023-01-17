package it.unipv.sfw.findme.groups;


import java.io.*;
import java.sql.*;
import java.util.*;

import it.unipv.sfw.findme.database.DBConnection;


public class GroupDAO {

	Connection conn;
	
	public GroupDAO (){
		
		
	}
	
	
	public void check(Group group) {
	/*	
		Connection conn=DBConnection.connect();
		String query="select User_Code, Email from users where User_Code=? or Email=?";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setInt(1, Integer.parseInt(emailOrID));
		preparedStmt.setString(2, emailOrID);
		ResultSet result=preparedStmt.executeQuery();

		if (result.next() == true) {

			query="insert into group_notifications (Sender, Receiver, Group_ID)"+"values (?, ?, ?)";
			preparedStmt = conn.prepareStatement(query);

			preparedStmt.setString(1, group.getGroupAdmin());
			preparedStmt.setString(2, result.getString(1));
			preparedStmt.setString(3, group.getGroupID());
			preparedStmt.execute();
			// manca parte della mail, aggiorniamo appena viene creata 
			
		}
		
		conn.close();*/
		return; // lo messo solo per non dare errore
	}
}
