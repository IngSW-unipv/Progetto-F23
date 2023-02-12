package it.unipv.sfw.findme.users.lab_manager;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;


import it.unipv.sfw.findme.booking.Booking;
import it.unipv.sfw.findme.database.DBConnection;
import it.unipv.sfw.findme.exceptions.ExceptionFrame;
import it.unipv.sfw.findme.login.LoginGUI;
import it.unipv.sfw.findme.notifications.LabNotification;
import it.unipv.sfw.findme.users.general_user.UserGUI;
import it.unipv.sfw.findme.users.general_user.Users;  

public class LabManager extends Users{

	public LabManager(String name, String lastName, String ID, String email, String password, String type) {
		super(name, lastName, ID, email, password, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void GUI(LoginGUI frame) {
		LabManagerGUI gui=new LabManagerGUI(this.name, this.lastName, this.email, this);
		frame.dispose();
	}

	@Override
	public JButton checkNotifications() {
		try {

			Connection conn=DBConnection.connect();
			String query="select * from lab_booking where Locked='false'";
			Statement statement = conn.createStatement();
			ResultSet result=statement.executeQuery(query);

			if (result.next() == true) {
				conn.close();
				ImageIcon notificationIcon=new ImageIcon("Resources/Images/bell-icon-inactive.png");
				Image image = notificationIcon.getImage();
				Image newimg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);  
				notificationIcon = new ImageIcon(newimg);
				JButton button= new JButton(notificationIcon);
				button.setBackground(Color.white);
				return button;
			}
			else {
				conn.close();
				ImageIcon notificationIcon=new ImageIcon("Resources/Images/bell-icon-active.png");
				Image image = notificationIcon.getImage();
				Image newimg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);  
				notificationIcon = new ImageIcon(newimg);
				JButton button= new JButton(notificationIcon);
				button.setBackground(Color.white);
				return button;
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public JPanel getMainPanel(UserGUI gui) {

		return new LabManagerMainPanel(name, lastName, email, gui, this);
	}

	@Override
	public JPanel book(Object[] objects, UserGUI frame) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel notificationPanel(Users user, UserGUI frame) {
		JPanel requestsPanel=new JPanel();
		requestsPanel.setBackground(Color.white);
		requestsPanel.setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();

		ArrayList<LabNotification> notificationsArray=new ArrayList<LabNotification>();

		try {


			Connection conn=DBConnection.connect();
			String query="select * from lab_booking where Locked='false'";
			Statement statement = conn.createStatement();
			ResultSet result=statement.executeQuery(query);
			while(result.next()) {
				notificationsArray.add(new LabNotification(result.getString(2), result.getString(6), result.getDate(3), result.getString(4), result.getString(5), result.getString(7), Boolean.parseBoolean(result.getString(8))));
			}

			conn.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		c.anchor = GridBagConstraints.CENTER;
		JLabel intro = new JLabel("Select the notification:");
		intro.setFont(new Font("Comic Sans MS", Font.BOLD,20));
		intro.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=0;
		c.insets= new Insets(20,0,0,0);
		requestsPanel.add(intro, c);

		JList<LabNotification> notificationsList=new JList(notificationsArray.toArray());
		JScrollPane listScroller = new JScrollPane(notificationsList);
		listScroller.setBorder(new LineBorder(new Color(145,0,0),2));
		notificationsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		c.gridx=0;
		c.gridy=1;
		requestsPanel.add(listScroller, c);
		//
		JButton expandButton=new JButton("Expand");
		expandButton.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		expandButton.setForeground(Color.white);
		expandButton.setBackground(new Color(145,0,0));
		expandButton.setOpaque(true);
		expandButton.setBorderPainted(false);
		expandButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				ExpandedRequest expandFrame=new ExpandedRequest(notificationsList.getSelectedValue());
				}catch(Exception ex) {
					new ExceptionFrame("No Notification Selected");
					return;
				}
			}

		});
		c.gridx=0;
		c.gridy=2;
		c.insets= new Insets(20,0,0,250);
		requestsPanel.add(expandButton, c);

		JButton acceptButton=new JButton("Accept");
		acceptButton.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		acceptButton.setForeground(Color.white);
		acceptButton.setBackground(new Color(145,0,0));
		acceptButton.setOpaque(true);
		acceptButton.setBorderPainted(false);
		acceptButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				LabNotification notification=notificationsList.getSelectedValue();
				LabManager manager=(LabManager)user;
				try {
				manager.accept(notification);
				}catch(Exception ex) {
					new ExceptionFrame("No Notification Selected");
					return;
				}
				
				new ExceptionFrame("Booking Request ACCEPTED!");
				frame.removePanel();
				user.getNotifications().clear();
				frame.addSecondPanel(user.notificationPanel(user, frame));
				frame.revalidate();
				frame.repaint();



			}
		});
		c.gridx=0;
		c.gridy=2;
		c.insets= new Insets(20,0,0,0);
		requestsPanel.add(acceptButton, c);
		//
		JButton rejectButton=new JButton("Reject");
		rejectButton.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		rejectButton.setForeground(Color.white);
		rejectButton.setBackground(new Color(145,0,0));
		rejectButton.setOpaque(true);
		rejectButton.setBorderPainted(false);
		rejectButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				LabNotification notification=notificationsList.getSelectedValue();
				LabManager manager=(LabManager)user;
				try {
				manager.reject(notification);
				}catch(Exception ex) {
					new ExceptionFrame("No Notification Selected");
					return;
				}

				new ExceptionFrame("Booking Request REJECTED!");
				frame.removePanel();
				user.getNotifications().clear();
				frame.addSecondPanel(user.notificationPanel(user, frame));
				frame.revalidate();
				frame.repaint();

			}

		});
		c.gridx=0;
		c.gridy=2;
		c.insets= new Insets(20,250,0,0);
		requestsPanel.add(rejectButton, c);

		return requestsPanel;
	}
	
	public void accept(LabNotification notification) throws Exception {
		LabBookingDAO labDAO=new LabBookingDAO();
		String bookingID=labDAO.selectID(notification);
		labDAO.update(new Booking(null, null, null, null, bookingID, null, null));
		
	}
	
	public void reject(LabNotification notification) throws Exception {
		LabBookingDAO labDAO=new LabBookingDAO();
		String bookingID=labDAO.selectID(notification);
		labDAO.delete(new Booking(null, null, null, null, bookingID, null, null));		
		
	}
}
