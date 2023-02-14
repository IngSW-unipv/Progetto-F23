package it.unipv.sfw.findme.controller.users.student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JList;
import it.unipv.sfw.findme.model.booking.Booking;
import it.unipv.sfw.findme.model.booking.dao.RoomsBookingDAO;
import it.unipv.sfw.findme.model.exceptions.ExceptionFrame;
import it.unipv.sfw.findme.model.notifications.dao.LabNotificationDAO;
import it.unipv.sfw.findme.view.users.general_user.UserGUI;
import it.unipv.sfw.findme.view.users.student.BookingsListPanel;

public class CancelGroupBookingListener implements ActionListener{

	private JList<Booking> groupsList;
	private UserGUI mainGUI;
	private RoomsBookingDAO groupsDAO;
	private LabNotificationDAO labDAO;

	public CancelGroupBookingListener(JList<Booking> groupsList, UserGUI mainGUI, RoomsBookingDAO groupsDAO,
			LabNotificationDAO labDAO) {
		this.groupsList = groupsList;
		this.mainGUI = mainGUI;
		this.groupsDAO = groupsDAO;
		this.labDAO = labDAO;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if(groupsList.getSelectedValue().getPeopleID().split("-")[1].equals(mainGUI.getUser().getID())){

			}else {
				throw new IllegalArgumentException();
			}
		}catch(IllegalArgumentException ex1) {
			new ExceptionFrame("You are not the Admin of this Group");
			return;
		}catch(NullPointerException ex2) {
			new ExceptionFrame("No Booking Selected");
			return;
		}
		groupsDAO.deleteBooking(groupsList.getSelectedValue());
		labDAO.delete(groupsList.getSelectedValue());
		mainGUI.removePanel();
		mainGUI.addSecondPanel(new BookingsListPanel(mainGUI));
		mainGUI.revalidate();
		mainGUI.repaint();
		new ExceptionFrame("Booking Cancelled Succesfully");
		
	}

}
