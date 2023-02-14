package it.unipv.sfw.findme.controller.users.general_user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

import it.unipv.sfw.findme.view.users.general_user.UserGUI;

public class NewPanelListener implements ActionListener{
	
	private UserGUI frame;
	private JPanel panel;
	
	public NewPanelListener(UserGUI frame, JPanel panel) {
		this.frame=frame;
		this.panel=panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.frame.removePanel();
		this.frame.addSecondPanel(this.panel);
		this.frame.revalidate();
		this.frame.repaint();
		
	}

}