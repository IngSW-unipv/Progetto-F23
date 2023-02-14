package it.unipv.sfw.findme.model.notifications.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import it.unipv.sfw.findme.model.database.DBConnection;
import it.unipv.sfw.findme.model.groups.Group;
import it.unipv.sfw.findme.model.notifications.JoinGroupNotification;
import it.unipv.sfw.findme.model.users.general_user.Users;


public class JoinGroupNotificationDAO {

	public void insertNotification(Group group, Users user) throws Exception {

		Connection conn=DBConnection.connect();
		String query="insert into group_notifications (Sender, Receiver, Group_ID)"+"values (?, ?, ?)";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setString(1, group.getGroupAdmin());
		preparedStmt.setString(2, user.getID());
		preparedStmt.setString(3, group.getGroupID());
		preparedStmt.execute();
		conn.close();

	}
	
	public void deleteNotification(JoinGroupNotification notification) throws Exception {
		Connection conn=DBConnection.connect();
		String query="delete from group_notifications where Sender=? and Receiver=? and Group_ID=?";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setInt(1, Integer.parseInt(notification.getSender()));
		preparedStmt.setInt(2, Integer.parseInt(notification.getReceiver()));
		preparedStmt.setString(3, notification.getGroupID());
		preparedStmt.execute();
		conn.close();
	}

}
