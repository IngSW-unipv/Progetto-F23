package it.unipv.sfw.findme.users.admin;

import java.io.FileInputStream;
import java.util.Properties;

import javax.swing.*;
import it.unipv.sfw.findme.login.LoginGUI;
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
		// TODO Auto-generated method stub
		return null;
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


	public void addCourse(String courseCode, String courseFullName, String yearsSelect) {
		CourseDAO dao=new CourseDAO();
		dao.insertCourse(new Course(courseCode, courseFullName, Integer.parseInt(yearsSelect)));
	}


	public void addRoom(String code, String type, String seatsNumber, String limString, String outlets, String disabled) {
		RoomDAO dao=new RoomDAO();
		Properties config= new Properties();
		try {
			FileInputStream fis;
			fis = new FileInputStream("Resources/Property/config.properties");
			config.load(fis);

		String roomClassName=config.getProperty(type);
		Rooms r=(Rooms)Class.forName(roomClassName).getDeclaredConstructor(String.class, String.class, String.class, String.class, String.class, String.class).newInstance(code, type, seatsNumber, limString, outlets, disabled);
		dao.insertRoom(r);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}