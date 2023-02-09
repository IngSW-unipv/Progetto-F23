package it.unipv.sfw.findme.users.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import it.unipv.sfw.findme.users.general_user.UserGUI;
import it.unipv.sfw.findme.users.general_user.Users;


public class AdminGUI extends UserGUI{

	public AdminGUI(String name, String lastName, String email, Users user) {
		super(name, lastName, email, user);
		
		//this.add(new AdminMainPanel(name, lastName, email, this), BorderLayout.NORTH);
		UserHeadAdmin head=new UserHeadAdmin(name, lastName, email, user, this);
		northPanel=head;
		this.add(northPanel, BorderLayout.NORTH);
		this.add(downPanel(), BorderLayout.SOUTH);
		
		JLayeredPane pane = this.getLayeredPane();
		pane.add(image(), new Integer(1));
	}

}