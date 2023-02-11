package it.unipv.sfw.findme.users.admin;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;
import javax.swing.*;

import it.unipv.sfw.findme.courses.Course;
import it.unipv.sfw.findme.courses.CourseDAO;
import it.unipv.sfw.findme.database.DBConnection;
import it.unipv.sfw.findme.login.LoginGUI;
import it.unipv.sfw.findme.rooms.RoomDAO;
import it.unipv.sfw.findme.rooms.Rooms;
import it.unipv.sfw.findme.users.general_user.UserGUI;
import it.unipv.sfw.findme.users.general_user.Users;

public class Admin extends Users {


	public Admin(String name, String lastName, String ID, String email, String password, String type) {
		super(name, lastName, ID, email, password, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void GUI(LoginGUI frame) {
		AdminGUI gui=new AdminGUI(this.name, this.lastName, this.email, this);
		frame.dispose();

	}

	@Override
	public JButton checkNotifications() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel getMainPanel(UserGUI gui) {
		return new AdminMainPanel(name, lastName, email, gui);
		
	}

	@Override
	public JPanel book(Object[] objects, UserGUI frame) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel notificationPanel(Users user, UserGUI frame) {
		// TODO Auto-generated method stub
		return null;
	}


	public void addCourse(Course course) {
		CourseDAO dao=new CourseDAO();
		dao.insertCourse(course);
	}


	public void addRoom(Rooms room) {
		RoomDAO dao=new RoomDAO();
		dao.insertRoom(room);
	}
	
	public void removeCourse(Course course) {
		CourseDAO daoCourse=new CourseDAO();
		daoCourse.deleteCourse(course);
	}
	
	
	public void updateSemester(Date start, Date end) {

		Connection conn=DBConnection.connect();
		try {
			String query="delete from semester";
			Statement statement=conn.prepareStatement(query);
			statement.execute(query);
			
			PreparedStatement preparedStmt=conn.prepareStatement(query);
			query="insert into semester (Start_Date, End_Date)"+"values (?, ?)";
			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setDate(1, start);
			preparedStmt.setDate(2, end);
			preparedStmt.execute();
				
			conn.close();

		}catch (Exception e1) {
		}
	}
	
}
