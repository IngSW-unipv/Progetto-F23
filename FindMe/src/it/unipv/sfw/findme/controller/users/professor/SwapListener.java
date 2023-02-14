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
import it.unipv.sfw.findme.model.database.DBConnection;
import it.unipv.sfw.findme.model.exceptions.ExceptionFrame;
import it.unipv.sfw.findme.model.notifications.ProfessorNotification;
import it.unipv.sfw.findme.model.rooms.Rooms;
import it.unipv.sfw.findme.model.users.general_user.Users;
import it.unipv.sfw.findme.model.users.professor.Professor;
import it.unipv.sfw.findme.view.users.general_user.UserGUI;

public class SwapListener implements ActionListener{

	private JComboBox<Rooms> possibleRooms;
	private JComboBox<String> startTimeBox;
	private JComboBox<String> endTimeBox;
	private JComboBox<String> secondEndTimeBox;
	private JComboBox<String> secondStartTimeBox;
	private Date firstDate;
	private Date secondDate;
	private Users user;
	private UserGUI mainGUI;
	private boolean skip;
		
		
	public SwapListener(Date firstDate, Date secondDate, Users user, UserGUI mainGUI, JComboBox<Rooms> possibleRooms, JComboBox<String> startTimeBox, JComboBox<String> endTimeBox, JComboBox<String> secondEndTimeBox, JComboBox<String> secondStartTimeBox, boolean skip) {
		this.possibleRooms=possibleRooms;
		this.startTimeBox=startTimeBox;
		this.endTimeBox=endTimeBox;
		this.secondEndTimeBox=secondEndTimeBox;
		this.secondStartTimeBox=secondStartTimeBox;
		this.firstDate=firstDate;
		this.secondDate=secondDate;
		this.user=user;
		this.mainGUI=mainGUI;
		this.skip=skip;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		LocalDate myDate =this.firstDate.toLocalDate();
		DayOfWeek dayOfWeek=myDate.getDayOfWeek();
		Date newMyDate=Date.valueOf(myDate);
		String scheduleID="";
			
		Connection conn=DBConnection.connect();
		try {
				
			String query="select Schedule_ID from schedule where Start_Time=? and End_Time=? and Day_Of_Week=? and Room=?";
			PreparedStatement preparedStmt=conn.prepareStatement(query);
			preparedStmt.setString(1, (String)this.startTimeBox.getSelectedItem());
			preparedStmt.setString(2, (String)this.endTimeBox.getSelectedItem());
			preparedStmt.setString(3, dayOfWeek.toString());
			preparedStmt.setString(4, (String)this.possibleRooms.getSelectedItem().toString());
			ResultSet result=preparedStmt.executeQuery();
			result.next();
			scheduleID=result.getString(1);
			conn.close();
				
		} catch (Exception e1) {
			e1.printStackTrace();
			new ExceptionFrame("Not a valid Schedule");
			return;
		}
				
			Date DoW;
			try {
				DoW =Date.valueOf(this.secondDate.toLocalDate());
			}catch(Exception ex) {
				DoW=null;
			}

			String from=(String)this.secondStartTimeBox.getSelectedItem();
			String to=(String)this.secondEndTimeBox.getSelectedItem();
				
			if(this.skip==true) {
				from=null;
				to=null;
			}
				
			if(from!=null && from.equals("To")) {
				new ExceptionFrame("Fill all the fiels before continuing!");
				return;
			}
				
			ProfessorNotification notification=new ProfessorNotification(scheduleID, newMyDate, user.getID(), DoW, from, to);
			try {
			Professor prof=(Professor)user;
			prof.swapSchedule(notification);
			}catch(Exception ex) {
				ex.printStackTrace();
				new ExceptionFrame("A Swap Request already present");
			}
			
			new ExceptionFrame("Swap Request sent Successfully");
			this.mainGUI.removePanel();
	}

}