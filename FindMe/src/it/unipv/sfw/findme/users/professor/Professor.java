package it.unipv.sfw.findme.users.professor;

import javax.swing.*;

import it.unipv.sfw.findme.login.LoginGUI;
import it.unipv.sfw.findme.users.general_user.UserGUI;
import it.unipv.sfw.findme.users.general_user.Users;  

public class Professor extends Users {

	public Professor(String name, String lastName, String ID, String email, String password, String type) {
		super(name, lastName, ID, email, password, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void GUI(LoginGUI frame) {
		// TODO Auto-generated method stub
		
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
	


	

	
}
