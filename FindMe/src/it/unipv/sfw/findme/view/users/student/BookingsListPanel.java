package it.unipv.sfw.findme.view.users.student;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import it.unipv.sfw.findme.controller.users.student.CancelGroupBookingListener;
import it.unipv.sfw.findme.controller.users.student.CancelSoloBookingListener;
import it.unipv.sfw.findme.model.booking.Booking;
import it.unipv.sfw.findme.model.booking.dao.RoomsBookingDAO;
import it.unipv.sfw.findme.model.booking.dao.SoloBookingDAO;
import it.unipv.sfw.findme.model.notifications.dao.LabNotificationDAO;
import it.unipv.sfw.findme.view.users.general_user.UserGUI;


public class BookingsListPanel extends JPanel{


	public BookingsListPanel(UserGUI mainGUI) {
		setLayout (new GridBagLayout());
		setBackground(Color.white);
		GridBagConstraints c=new GridBagConstraints();

		JPanel soloPanel=new JPanel();
		JPanel groupPanel=new JPanel();

		JTabbedPane tabbedPane = new JTabbedPane();


		SoloBookingDAO soloDAO=new SoloBookingDAO();
		ArrayList<Booking> bookings=soloDAO.selectBookingsFromUser(mainGUI.getUser());

		JList<Booking> soloList=new JList(bookings.toArray());
		soloList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scroll = new JScrollPane(soloList);
		scroll.setBorder(new LineBorder(new Color(145,0,0),2));
		soloPanel.add(scroll);

		JButton cancelBooking=new JButton("Cancel");
		cancelBooking.setFont(new Font("Comic Sans MS", Font.BOLD,10));
		cancelBooking.setForeground(Color.white);
		cancelBooking.setBackground(new Color(145,0,0));
		cancelBooking.setOpaque(true);
		cancelBooking.setBorderPainted(false);
		CancelSoloBookingListener cancelSolo=new CancelSoloBookingListener(soloDAO, soloList, mainGUI);
		cancelBooking.addActionListener(cancelSolo);
		soloPanel.add(cancelBooking);


		RoomsBookingDAO groupsDAO=new RoomsBookingDAO();
		ArrayList<Booking> groupBookings=groupsDAO.selectBookingsFromUser(mainGUI.getUser());
		
		LabNotificationDAO labDAO=new LabNotificationDAO();
		ArrayList<Booking> labBookings=labDAO.selectBookingsFromUser(mainGUI.getUser());
		
		groupBookings.addAll(labBookings);
		
		JList<Booking> groupsList=new JList(groupBookings.toArray());
		soloList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollGroups = new JScrollPane(groupsList);
		scrollGroups.setBorder(new LineBorder(new Color(145,0,0),2));
		groupPanel.add(scrollGroups);

		JButton cancelGroupBooking=new JButton("Cancel");
		cancelGroupBooking.setFont(new Font("Comic Sans MS", Font.BOLD,10));
		cancelGroupBooking.setForeground(Color.white);
		cancelGroupBooking.setBackground(new Color(145,0,0));
		cancelGroupBooking.setOpaque(true);
		cancelGroupBooking.setBorderPainted(false);
		CancelGroupBookingListener groupListener=new CancelGroupBookingListener(groupsList, mainGUI, groupsDAO, labDAO);
		cancelGroupBooking.addActionListener(groupListener);
		groupPanel.add(cancelGroupBooking);



		tabbedPane.addTab("Solo Bookings", soloPanel);
		tabbedPane.addTab("Group Bookings", groupPanel);
		add(tabbedPane);

	}

}