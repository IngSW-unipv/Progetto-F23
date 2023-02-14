package it.unipv.sfw.findme.view.users.general_user;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;

import it.unipv.sfw.findme.model.users.general_user.Users;


public class UsersHeadPanel extends JPanel{
	
	public UsersHeadPanel(String name, String lastName, String email, Users user, UserGUI gui) {
		setLayout(new BorderLayout());
		setBackground(new Color(145,0,0));
		//setBackground(new Color(0,0,0,0));
		add(user.getMainPanel(gui), BorderLayout.WEST);
		add(new NotificationRightPanel(user, gui), BorderLayout.EAST);
		
		
		
	}	
}