package it.unipv.sfw.findme.view.users.professor;

import java.awt.BorderLayout;
import javax.swing.JLayeredPane;

import it.unipv.sfw.findme.model.users.professor.Professor;
import it.unipv.sfw.findme.view.users.general_user.UserGUI;
import it.unipv.sfw.findme.view.users.general_user.UsersHeadPanel;


public class ProfessorGUI extends UserGUI{
	
	private boolean skip;

	public ProfessorGUI(String name, String lastName, String email, Professor user) {
		super(name, lastName, email, user);
		
		UsersHeadPanel head=new UsersHeadPanel(name, lastName, email, user, this);
		northPanel=head;
		addFindPanel(this, user);
		this.add(northPanel, BorderLayout.NORTH);
		this.add(downPanel(), BorderLayout.SOUTH);
		
		JLayeredPane pane = this.getLayeredPane();
		pane.add(image(), new Integer(1));

	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}
	
	

}
