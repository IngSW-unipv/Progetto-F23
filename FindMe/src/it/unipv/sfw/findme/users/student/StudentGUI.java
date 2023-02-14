package it.unipv.sfw.findme.users.student;

import java.awt.BorderLayout;
import javax.swing.JLayeredPane;
import it.unipv.sfw.findme.users.general_user.UserGUI;
import it.unipv.sfw.findme.users.general_user.UsersHeadPanel;


public class StudentGUI extends UserGUI{
	

	public StudentGUI(String name, String lastName, String email, Student user) {
		super(name, lastName, email, user);
		
		
		UsersHeadPanel head=new UsersHeadPanel(name, lastName, email, user, this);
		northPanel=head;
		
		addFindPanel(this, user);
		this.add(northPanel, BorderLayout.NORTH);
		this.add(downPanel(), BorderLayout.SOUTH);
		
		
		JLayeredPane pane = this.getLayeredPane();
		pane.add(image(), new Integer(1));
		
	}
	
	
	
}