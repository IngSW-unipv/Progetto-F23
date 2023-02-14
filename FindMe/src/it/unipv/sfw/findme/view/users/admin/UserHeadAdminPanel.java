package it.unipv.sfw.findme.view.users.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;

import it.unipv.sfw.findme.model.users.general_user.Users;
import it.unipv.sfw.findme.view.users.general_user.UserGUI;


public class UserHeadAdminPanel extends JPanel{
	public UserHeadAdminPanel(String name, String lastName, String email, Users user, UserGUI gui) {
		
		setLayout(new BorderLayout());
		setBackground(new Color(145,0,0));
		//setBackground(new Color(0,0,0,0));
		add(user.getMainPanel(gui), BorderLayout.WEST);
		
		
	}	

}