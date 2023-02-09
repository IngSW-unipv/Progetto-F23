package it.unipv.sfw.findme.users.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import it.unipv.sfw.findme.users.general_user.UserGUI;
import it.unipv.sfw.findme.users.general_user.Users;


public class UserHeadAdmin extends JPanel{
	public UserHeadAdmin(String name, String lastName, String email, Users user, UserGUI gui) {
		
		setLayout(new BorderLayout());
		setBackground(new Color(145,0,0));
		//setBackground(new Color(0,0,0,0));
		add(user.getMainPanel(gui), BorderLayout.WEST);
		
		
	}	

}