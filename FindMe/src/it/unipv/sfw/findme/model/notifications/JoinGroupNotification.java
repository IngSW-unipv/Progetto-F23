package it.unipv.sfw.findme.model.notifications;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import it.unipv.sfw.findme.model.database.DBConnection;
import it.unipv.sfw.findme.model.groups.Group;
import it.unipv.sfw.findme.model.groups.dao.GroupDAO;
import it.unipv.sfw.findme.model.users.student.Student;


public class JoinGroupNotification implements Notification{

	private String sender;
	private String receiver;
	private String groupID;

	public JoinGroupNotification(String sender, String receiver, String groupID) {
		this.sender=sender;
		this.receiver=receiver;
		this.groupID=groupID;
	}


	public void accept(){
		GroupDAO daoGroup=new GroupDAO();
		Group group=new Group(this.groupID, this.sender);

		if(daoGroup.countPartecipant(group)==group.getStudentsLimit()) {
			throw new IllegalArgumentException();
		}

		try {
			daoGroup.insertPartecipant(group, new Student(null, null, this.receiver, null, null, null));
		} catch (Exception ea) {
			ea.printStackTrace();
		}

	}

	@Override
	public String toString() {
		try {

			Connection conn=DBConnection.connect();
			String query="select Name, Last_Name from users where User_Code=?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, Integer.parseInt(this.sender));
			ResultSet result=preparedStmt.executeQuery();

			result.next();
			String name=result.getString(1);
			String lastName=result.getString(2);	
			conn.close();
			return name+" "+lastName+" wants you to join the group: "+"'"+this.groupID+"'";

		} catch (Exception e) {
			e.printStackTrace();
		}	

		return null;
	}


	public String getSender() {
		return sender;
	}


	public String getReceiver() {
		return receiver;
	}


	public String getGroupID() {
		return groupID;
	}



}
