package it.unipv.sfw.findme.model.groups.dao;


import java.sql.*;
import java.util.ArrayList;

import it.unipv.sfw.findme.model.database.DBConnection;
import it.unipv.sfw.findme.model.groups.Group;
import it.unipv.sfw.findme.model.users.student.Student;

public class GroupDAO {
	
	public GroupDAO(){
		
		}

	public void insertGroup(Group group) throws Exception {
		Connection conn=DBConnection.connect();

		String query="insert into allgroups (Group_ID, Admin, Partecipant)"+"values (?, ?, ?)";
		PreparedStatement preparedStmt = conn.prepareStatement(query);

		preparedStmt.setString(1, group.getGroupID());
		preparedStmt.setString(2, group.getGroupAdmin());
		preparedStmt.setString(3, group.getGroupAdmin());

		preparedStmt.execute();
		conn.close();
	}


	public void checkPartecipant(Group group, Student student) throws Exception {
		Connection conn=DBConnection.connect();
		String query="select * from allgroups where Group_ID=? and Admin=? and Partecipant=?";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setString(1, group.getGroupID());
		preparedStmt.setString(2, group.getGroupAdmin());
		preparedStmt.setString(3, student.getID());
		ResultSet result=preparedStmt.executeQuery();
		if(result.next()==true) {
			conn.close();
			throw new Exception();
		}
		conn.close();
	}


	public int countPartecipant(Group group) {
		try {
			Connection conn=DBConnection.connect();

			String query ="select count(Partecipant) from allgroups where Group_ID=? and Admin=?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, group.getGroupID());
			preparedStmt.setString(2, group.getGroupAdmin());
			ResultSet result=preparedStmt.executeQuery();
			result.next();

			int newPartecipants=result.getInt(1);
			return newPartecipants;
		} catch (Exception e) {
		}
		return 0;
	}
	
	public void insertPartecipant(Group group, Student student) throws Exception {
		Connection conn=DBConnection.connect();

		String query="insert into allgroups (Group_ID, Admin, Partecipant)"+"values (?, ?, ?)";
		PreparedStatement preparedStmt = conn.prepareStatement(query);

		preparedStmt.setString(1, group.getGroupID());
		preparedStmt.setString(2, group.getGroupAdmin());
		preparedStmt.setString(3, student.getID());

		preparedStmt.execute();
		conn.close();
	}
	
	public void deleteGroup(Group group) throws Exception {
		Connection conn=DBConnection.connect();
		String query="delete from allgroups where Group_ID=? and Admin=?";
		PreparedStatement preparedStmt = conn.prepareStatement(query);

		preparedStmt.setString(1, group.getGroupID());
		preparedStmt.setString(2, group.getGroupAdmin());

		preparedStmt.execute();
		conn.close();
	}
	
	
	public ArrayList<Student> selectPartecipantsFromGroup(Group group) throws Exception {
		
		ArrayList<Student> students=new ArrayList<Student>();
		
		Connection conn=DBConnection.connect();
		String query="select * from(select Partecipant from allgroups where Admin=? and Group_ID=?)temp1 join (select * from users)temp2 on temp1.Partecipant=temp2.User_Code";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setString(1, group.getGroupAdmin());
		preparedStmt.setString(2, group.getGroupID());
		ResultSet result=preparedStmt.executeQuery();
		while(result.next()) {
			students.add(new Student(result.getString(4), result.getNString(5), result.getString(2), null, null, null));
		}
		conn.close();
		return students;
	}
	
	public void deletePartecipant(Group group, Student student) throws Exception {
		Connection conn=DBConnection.connect();
		String query="delete from allgroups where Group_ID=? and Admin=? and Partecipant=?";
		PreparedStatement preparedStmt = conn.prepareStatement(query);

		preparedStmt.setString(1, group.getGroupID());
		preparedStmt.setString(2, group.getGroupAdmin());
		preparedStmt.setString(3, student.getID());

		preparedStmt.execute();
		conn.close();
		
	}
	

}