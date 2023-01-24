package it.unipv.sfw.findme.users.general_user;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import it.unipv.sfw.findme.login.LoginGUI;

public abstract class Users {
	
	protected String name;
	protected String lastName;
	protected String ID;
	protected String email;
	protected String password;
	protected List<Notification> notifications;
	
	
	public Users(String name, String lastName, String iD, String email, String password) {
		super();
		this.notifications=new ArrayList();
		this.name = name;
		this.lastName = lastName;
		this.ID = iD;
		this.email = email;
		this.password = password;
	}
	
	public abstract void GUI(LoginGUI frame);
	
	public abstract JButton checkNotifications();
	
	public abstract JPanel getMainPanel(UserGUI gui);
	
	public abstract JPanel book(Object[] objects, UserGUI frame);
	
	public abstract JPanel notificationPanel(Users user, UserGUI frame);
	
	public void loadNotifications(Notification notification) {
		this.notifications.add(notification);
	}
	
	public List<Notification> getNotifications(){
		return this.notifications;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getID() {
		return ID;
	}


	public void setID(String iD) {
		ID = iD;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}	

}