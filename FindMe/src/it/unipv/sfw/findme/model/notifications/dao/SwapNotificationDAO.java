package it.unipv.sfw.findme.model.notifications.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import it.unipv.sfw.findme.model.database.DBConnection;
import it.unipv.sfw.findme.model.notifications.Notification;
import it.unipv.sfw.findme.model.notifications.SwapNotification;


public class SwapNotificationDAO {

	public boolean checkNotification(SwapNotification notification) {
		boolean swapRequests=false;
		try {

			Connection conn=DBConnection.connect();
			String query="select * from swap_notifications where Receiver=? and Accepted=? or Sender=? and Accepted=?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement = conn.prepareStatement(query);
			statement.setString(1, notification.getReceiver());
			statement.setString(2, Boolean.toString(notification.isAccepted()));
			statement.setString(3, notification.getReceiver());
			statement.setString(4, Boolean.toString(notification.isAccepted()));
			ResultSet result=statement.executeQuery();

			swapRequests=result.next();
			conn.close();
			return swapRequests;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return swapRequests;
	}

	public List<Notification> getNotifications(SwapNotification notification) {
		List<Notification> list=new ArrayList();
		try {

			Connection conn=DBConnection.connect();
			String query="select * from swap_notifications where Receiver=? or Sender=?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, notification.getReceiver());
			statement.setString(2, notification.getSender());
			ResultSet result=statement.executeQuery();
			while(result.next()) {
				list.add(new SwapNotification(result.getString(1), result.getString(2), result.getDate(3), result.getDate(4), result.getString(5), result.getString(6), result.getString(7)));
			}
			conn.close();
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public void acceptSwap(SwapNotification notification) throws Exception {
			Connection conn=DBConnection.connect();
			String query="UPDATE swap_notifications SET Accepted = 'true' WHERE Sender=? and Receiver=? and First_Date=? and New_Date=? and First_Schedule=? and New_Schedule=?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, notification.getSender());
			preparedStmt.setString(2, notification.getReceiver());
			preparedStmt.setDate(3, notification.getFirstDate());
			preparedStmt.setDate(4, notification.getNewDate());
			preparedStmt.setString(5, notification.getFirstSchedule());
			preparedStmt.setString(6, notification.getNewScehudle());
			preparedStmt.executeUpdate();
			conn.close();
	}
	
	public void deleteSwap(SwapNotification notification) throws Exception {
		Connection conn=DBConnection.connect();
		String query="delete from swap_notifications where Sender=? and Receiver=? and First_Date=? and New_Date=? and First_Schedule=? and New_Schedule=? and Accepted='false'";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setString(1, notification.getSender());
		preparedStmt.setString(2, notification.getReceiver());
		preparedStmt.setDate(3, notification.getFirstDate());
		preparedStmt.setDate(4, notification.getNewDate());
		preparedStmt.setString(5, notification.getFirstSchedule());
		preparedStmt.setString(6, notification.getNewScehudle());
		preparedStmt.executeUpdate();
	}
	
	public void insertNotification(SwapNotification notification) throws Exception {
		Connection conn=DBConnection.connect();
		String query="insert into swap_notifications (Sender, Receiver, First_Date, New_Date, First_Schedule, New_Schedule, Accepted)"+"values(?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.setString(1, notification.getReceiver());
		preparedStmt.setString(2, notification.getSender());
		preparedStmt.setDate(3, notification.getFirstDate());
		preparedStmt.setDate(4, notification.getNewDate());
		preparedStmt.setString(5, notification.getFirstSchedule());
		preparedStmt.setString(6, notification.getNewScehudle());
		preparedStmt.setString(7, Boolean.toString(notification.isAccepted()));
		preparedStmt.execute();
	}

}
