package it.unipv.sfw.findme.users.admin;

import java.awt.BorderLayout;

import it.unipv.sfw.findme.users.general_user.UserGUI;
import it.unipv.sfw.findme.users.general_user.Users;


public class AdminGUI extends UserGUI{

	public AdminGUI(String name, String lastName, String email, Users user) {
		super(name, lastName, email, user);
		
		this.add(new AdminMainPanel(name, lastName, email, this), BorderLayout.NORTH);
	}

}
