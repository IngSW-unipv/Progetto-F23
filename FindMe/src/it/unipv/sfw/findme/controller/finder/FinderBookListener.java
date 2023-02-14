package it.unipv.sfw.findme.controller.finder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JList;
import it.unipv.sfw.findme.model.booking.Booking;
import it.unipv.sfw.findme.model.exceptions.ExceptionFrame;
import it.unipv.sfw.findme.model.users.general_user.Users;
import it.unipv.sfw.findme.view.booking.ConfirmBookigPanel;
import it.unipv.sfw.findme.view.users.general_user.UserGUI;

public class FinderBookListener implements ActionListener{

	private JList<Booking> scroll;
	private UserGUI frame;
	private Users user;


	public FinderBookListener(JList<Booking> list, UserGUI frame, Users user) {
		this.scroll=list;
		this.frame=frame;
		this.user=user;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if(this.scroll.getSelectedValue()==null) {
				throw new IllegalArgumentException();
			}
		}catch(IllegalArgumentException ex){
			new ExceptionFrame("No Room Selected");
			return;
		}
		this.frame.removePanel();
		this.frame.addSecondPanel(new ConfirmBookigPanel(this.scroll, user, frame));
		this.frame.revalidate();
		this.frame.repaint();
	}

}