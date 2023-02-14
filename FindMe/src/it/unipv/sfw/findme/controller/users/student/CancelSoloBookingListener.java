package it.unipv.sfw.findme.controller.users.student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JList;
import it.unipv.sfw.findme.model.booking.Booking;
import it.unipv.sfw.findme.model.booking.dao.SoloBookingDAO;
import it.unipv.sfw.findme.model.exceptions.ExceptionFrame;
import it.unipv.sfw.findme.view.users.general_user.UserGUI;
import it.unipv.sfw.findme.view.users.student.BookingsListPanel;

public class CancelSoloBookingListener implements ActionListener{
	
	private SoloBookingDAO soloDAO;
	private JList<Booking> soloList;
	private UserGUI mainGUI;

	public CancelSoloBookingListener(SoloBookingDAO soloDAO, JList<Booking> soloList, UserGUI mainGUI) {
		super();
		this.soloDAO = soloDAO;
		this.soloList = soloList;
		this.mainGUI = mainGUI;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			soloDAO.deleteBooking(soloList.getSelectedValue());
		}catch(Exception ex) {
			new ExceptionFrame("No Booking Selected");
			return;
		}
		mainGUI.removePanel();
		mainGUI.addSecondPanel(new BookingsListPanel(mainGUI));
		mainGUI.revalidate();
		mainGUI.repaint();
		new ExceptionFrame("Booking Cancelled Succesfully");
		
	}

}
