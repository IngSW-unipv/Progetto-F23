package it.unipv.sfw.findme.controller.users.professor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.DayOfWeek;
import java.time.LocalDate;

import javax.swing.JComboBox;
import javax.swing.JList;

import org.jdatepicker.impl.JDatePanelImpl;

import it.unipv.sfw.findme.model.database.DBConnection;
import it.unipv.sfw.findme.model.exceptions.ExceptionFrame;
import it.unipv.sfw.findme.model.notifications.ProfessorNotification;
import it.unipv.sfw.findme.model.users.general_user.Users;
import it.unipv.sfw.findme.view.users.general_user.UserGUI;
import it.unipv.sfw.findme.view.users.general_user.UsersHeadPanel;
import it.unipv.sfw.findme.view.users.professor.SendDraftFrame;

public class SendDraftListener implements ActionListener{
	private JComboBox<String> roomsBox;
	private ProfessorNotification notification;
	private JDatePanelImpl datePanel;
	private JComboBox<String> startTimeBox;
	private JComboBox<String> endTimeBox;
	private Users user;
	private UserGUI frame;
	private SendDraftFrame smallFrame;
	
	public SendDraftListener(JComboBox<String> roomsBox, ProfessorNotification notification, JDatePanelImpl datePanel,
			JComboBox<String> startTimeBox, JComboBox<String> endTimeBox, Users user, UserGUI frame, SendDraftFrame smallFrame) {
		this.roomsBox = roomsBox;
		this.notification = notification;
		this.datePanel = datePanel;
		this.startTimeBox = startTimeBox;
		this.endTimeBox = endTimeBox;
		this.user = user;
		this.frame = frame;
		this.smallFrame=smallFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Date receiverDate=null;
		String selectedRoom=(String)roomsBox.getSelectedItem().toString();
		try {
		receiverDate=notification.getDate();
		}catch(Exception ex) {
			new ExceptionFrame("No Notification Selected");
			return;
		}
		Date draftDate=(Date)datePanel.getModel().getValue();

		try {

			if(receiverDate.compareTo(draftDate)>0) {
				throw new Exception();
			}
		}catch(Exception ex) {
			new ExceptionFrame("Not a valid Date!");
			return;
		}

		try {
			Connection conn=DBConnection.connect();
			LocalDate myDate = draftDate.toLocalDate();
			DayOfWeek dayOfWeek=myDate.getDayOfWeek();
			String query="select Schedule_ID from schedule where Start_Time=? and End_Time=? and Room=? and Day_Of_Week=?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, startTimeBox.getSelectedItem().toString());
			preparedStmt.setString(2, endTimeBox.getSelectedItem().toString());
			preparedStmt.setString(3, selectedRoom);
			preparedStmt.setString(4, dayOfWeek.toString());
			ResultSet result=preparedStmt.executeQuery();
			result.next();
			String newSchedule=result.getString(1);

			query="insert into swap_notifications (Sender, Receiver, First_Date, New_Date, First_Schedule, New_Schedule, Accepted)"+"values(?, ?, ?, ?, ?, ?, ?)";
			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, user.getID());
			preparedStmt.setString(2, notification.getSender());
			preparedStmt.setDate(3, receiverDate);
			preparedStmt.setDate(4, draftDate);
			preparedStmt.setString(5, notification.getScheduleID());
			preparedStmt.setString(6, newSchedule);
			preparedStmt.setString(7, "false");
			preparedStmt.execute();

			conn.close();

		} catch (Exception ea) {
			new ExceptionFrame("Error!");
			return;
		}
		notification.accept();
		frame.removePanel();
		user.getNotifications().clear();
    	frame.addSecondPanel(user.notificationPanel(user, frame));
    	UserGUI tempFrame=frame;
    	tempFrame.removeHeadPanel(new UsersHeadPanel(user.getName(), user.getLastName(), user.getEmail(), user, frame));
    	tempFrame.revalidate();
    	tempFrame.repaint();
    	new ExceptionFrame("Swap Proposal Sent!");
    	smallFrame.dispose();

	}

}
