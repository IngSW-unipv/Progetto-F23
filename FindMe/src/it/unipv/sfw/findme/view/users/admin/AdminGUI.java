package it.unipv.sfw.findme.view.users.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import it.unipv.sfw.findme.model.users.general_user.Users;
import it.unipv.sfw.findme.view.users.general_user.UserGUI;


public class AdminGUI extends UserGUI{

	public AdminGUI(String name, String lastName, String email, Users user) {
		super(name, lastName, email, user);
		
		//this.add(new AdminMainPanel(name, lastName, email, this), BorderLayout.NORTH);
		UserHeadAdminPanel head=new UserHeadAdminPanel(name, lastName, email, user, this);
		northPanel=head;
		this.add(northPanel, BorderLayout.NORTH);
		this.add(downPanel(), BorderLayout.SOUTH);
		
		JLayeredPane pane = this.getLayeredPane();
		pane.add(image(), new Integer(1));
	}

}