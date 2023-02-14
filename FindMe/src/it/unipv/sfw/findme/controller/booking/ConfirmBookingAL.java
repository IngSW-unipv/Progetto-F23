package it.unipv.sfw.findme.controller.booking;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JList;
import it.unipv.sfw.findme.model.booking.Booking;
import it.unipv.sfw.findme.model.exceptions.ExceptionFrame;
import it.unipv.sfw.findme.model.users.general_user.Users;
import it.unipv.sfw.findme.view.users.general_user.UserGUI;

public class ConfirmBookingAL implements ActionListener{
	
	private JList<Booking> list;
	private boolean maxed;
	private UserGUI frame;
	private Users user;

	public ConfirmBookingAL(JList<Booking> list, boolean maxed, UserGUI frame, Users user) {
		this.list=list;
		this.maxed=maxed;
		this.frame=frame;
		this.user=user;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if(list.getSelectedValue()==null) {
				throw new IllegalArgumentException();
			}
			}catch(IllegalArgumentException ex) {
				new ExceptionFrame("No Time Span Selected");
				return;
			}
			
			
			if(maxed==false) {
			frame.removePanel();
			frame.addSecondPanel(user.book(list.getSelectedValues(), frame));
			frame.revalidate();
			frame.repaint();
			}
			else {
				new ExceptionFrame("Room is FULL!");
			}
		
	}

}
