package it.unipv.sfw.findme.users.lab_manager;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLayeredPane;


import it.unipv.sfw.findme.users.general_user.UserGUI;
import it.unipv.sfw.findme.users.general_user.Users;

public class LabManagerGUI extends UserGUI{
	
	public LabManagerGUI(String name, String lastName, String email, Users user) {
		super(name, lastName, email, user);
//		UsersHeadPanel head=new UsersHeadPanel(name, lastName, email, user, this);
//		northPanel=head;
		this.add(northPanel, BorderLayout.NORTH);
		this.add(downPanel(), BorderLayout.SOUTH);
		this.setBackground(Color.white);
		JLayeredPane pane = this.getLayeredPane();


	}
	
	@Override
	public void addFindPanel(UserGUI gui, Users user) {
		
	}

}
