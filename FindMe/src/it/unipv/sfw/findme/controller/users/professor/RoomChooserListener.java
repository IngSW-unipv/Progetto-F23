package it.unipv.sfw.findme.controller.users.professor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.DayOfWeek;
import java.time.LocalDate;
import javax.swing.JComboBox;
import it.unipv.sfw.findme.model.database.DBConnection;
import it.unipv.sfw.findme.model.exceptions.ExceptionFrame;
import it.unipv.sfw.findme.model.notifications.ProfessorNotification;
import it.unipv.sfw.findme.model.notifications.SwapNotification;
import it.unipv.sfw.findme.model.notifications.dao.ProfessorNotificationDAO;
import it.unipv.sfw.findme.model.notifications.dao.SwapNotificationDAO;
import it.unipv.sfw.findme.model.users.general_user.Users;
import it.unipv.sfw.findme.view.users.general_user.UserGUI;
import it.unipv.sfw.findme.view.users.general_user.UsersHeadPanel;
import it.unipv.sfw.findme.view.users.professor.RoomChooserFrame;

public class RoomChooserListener implements ActionListener{
	
	private JComboBox<String> roomsBox;
	private ProfessorNotification notification;
	private Users user;
	private UserGUI frame;
	private RoomChooserFrame smallFrame;

	public RoomChooserListener(JComboBox<String> roomsBox, ProfessorNotification notification, Users user,
			UserGUI frame, RoomChooserFrame smallFrame) {
		this.roomsBox = roomsBox;
		this.notification = notification;
		this.user = user;
		this.frame = frame;
		this.smallFrame = smallFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String selectedRoom=(String)roomsBox.getSelectedItem().toString();
		if(notification.getNewDate()!=null) {
			try {
				Connection conn=DBConnection.connect();


				LocalDate myDate =notification.getDate().toLocalDate();
				DayOfWeek dayOfWeek=myDate.getDayOfWeek();
				String query="select Schedule_ID from schedule where Start_Time=? and End_Time=? and Room=? and Day_Of_Week=?";
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, notification.getNewTo());
				preparedStmt.setString(2, notification.getNewFrom());
				preparedStmt.setString(3, selectedRoom);
				preparedStmt.setString(4, dayOfWeek.toString());
				ResultSet result=preparedStmt.executeQuery();
				result.next();
				String newSchedule=result.getString(1);

				SwapNotificationDAO swapDao=new SwapNotificationDAO();
				SwapNotification swap=new SwapNotification(user.getID(), notification.getSender(), notification.getDate(), notification.getNewDate(),  notification.getScheduleID(), newSchedule, "true");
				swapDao.insertNotification(swap);
				ProfessorNotificationDAO dao=new ProfessorNotificationDAO();
				dao.deleteWithNewDate(notification);

				conn.close();

			} catch (Exception ea) {
				new ExceptionFrame("\u274C Error!");
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
	    	new ExceptionFrame("Swap Request Accepted!");
		}
		else {
			new ExceptionFrame("You need to send Swap-Draft before you can accept this request");

		}
		smallFrame.dispose();
		
	}

}
