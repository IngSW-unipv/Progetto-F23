package it.unipv.sfw.findme.view.finder;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.LineBorder;
import it.unipv.sfw.findme.controller.finder.FinderBookListener;
import it.unipv.sfw.findme.controller.finder.filters.FiltersListener;
import it.unipv.sfw.findme.model.booking.Booking;
import it.unipv.sfw.findme.model.users.general_user.Users;
import it.unipv.sfw.findme.view.finder.filters.FilterCheckBox;
import it.unipv.sfw.findme.view.finder.filters.FilterRoomDisabledAccess;
import it.unipv.sfw.findme.view.finder.filters.FilterRoomLim;
import it.unipv.sfw.findme.view.finder.filters.FilterRoomOutlets;
import it.unipv.sfw.findme.view.finder.filters.FilterRoomType;
import it.unipv.sfw.findme.view.finder.filters.FilterSeatsNumber;
import it.unipv.sfw.findme.view.users.general_user.UserGUI;

public class FinderMainPanel extends JPanel{

	private JScrollPane scroll;
	private List<Booking> freeRooms;
	private List<Booking> freeRoomsBackUp;
	private LinkedList<FilterCheckBox> allFilters;

	public FinderMainPanel(List<Booking> freeRooms, List<Booking> freeRoomsBackUp, UserGUI frame, Users user) {
		SwingUtilities.updateComponentTreeUI(frame);
		this.allFilters=new LinkedList<FilterCheckBox>();
		this.freeRooms=freeRooms;
		this.freeRoomsBackUp=freeRoomsBackUp;
		removeAll();

		setBackground(Color.white);
		setLayout (new GridBagLayout());

		GridBagConstraints c=new GridBagConstraints();


		JList<Booking> list=new JList(freeRooms.toArray());
	
		
		

		list.setForeground(Color.black);
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setBorder(new LineBorder(new Color(145,0,0),2));
		listScroller.setPreferredSize(new Dimension(250, 200));

		c.insets = new Insets(0,0,20,0);
		c.gridx=1;
		c.gridy=0;

		this.scroll=listScroller;
		add(listScroller, c);

		c.anchor = GridBagConstraints.CENTER;
		FilterSeatsNumber filterSeats100=new FilterSeatsNumber();
		filterSeats100.setLabel("100 seats");
		filterSeats100.setSeats(100);
		filterSeats100.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		filterSeats100.setForeground(new Color(145,0,0));
		filterSeats100.setBackground(Color.white);
		this.allFilters.add(filterSeats100);
		c.gridx=0;
		c.gridy=1;
		add(filterSeats100, c);


		FilterSeatsNumber filterSeats50=new FilterSeatsNumber();
		filterSeats50.setLabel("50 Seats");
		filterSeats50.setSeats(50);
		this.allFilters.add(filterSeats50);
		filterSeats50.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		filterSeats50.setForeground(new Color(145,0,0));
		filterSeats50.setBackground(Color.white);
		c.gridx=1;
		c.gridy=1;
		add(filterSeats50, c);

		FilterRoomType filterLab=new FilterRoomType();
		filterLab.setLabel("Only Labs");
		filterLab.setIdentifier("LAB");
		this.allFilters.add(filterLab);
		filterLab.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		filterLab.setForeground(new Color(145,0,0));
		filterLab.setBackground(Color.white);
		c.gridx=2;
		c.gridy=1;
		add(filterLab, c);
		
		FilterRoomLim filterLim=new FilterRoomLim();
		filterLim.setLabel("Lim");
		filterLim.setIdentifier("LIM");
		this.allFilters.add(filterLim);
		filterLim.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		filterLim.setForeground(new Color(145,0,0));
		filterLim.setBackground(Color.white);
		c.gridx=0;
		c.gridy=3;
		add(filterLim, c);

		FilterRoomOutlets filterOutlets =new FilterRoomOutlets();
		filterOutlets.setLabel("Outletls");
		filterOutlets.setIdentifier("OUTLETS");
		this.allFilters.add(filterOutlets);
		filterOutlets.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		filterOutlets.setForeground(new Color(145,0,0));
		filterOutlets.setBackground(Color.white);
		c.gridx=1;
		c.gridy=3;
		add(filterOutlets, c);
		
		FilterRoomDisabledAccess filterDisabledAccess =new FilterRoomDisabledAccess();
		filterDisabledAccess.setLabel("DisabledAccess");
		filterDisabledAccess.setIdentifier("DISABLEDACCESS");
		this.allFilters.add(filterDisabledAccess);
		filterDisabledAccess.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		filterDisabledAccess.setForeground(new Color(145,0,0));
		filterDisabledAccess.setBackground(Color.white);
		c.gridx=2;
		c.gridy=3;
		add(filterDisabledAccess, c);

		c.anchor = GridBagConstraints.CENTER;
		JButton filterButton=new JButton("Filter");
		FiltersListener filterL= new FiltersListener(this, this.allFilters, frame, user);
		filterButton.addActionListener(filterL);
		filterButton.setFont(new Font("Comic Sans MS", Font.BOLD,10));
		filterButton.setForeground(Color.white);
		filterButton.setBackground(new Color(145,0,0));
		filterButton.setOpaque(true);
		filterButton.setBorderPainted(false);

		c.gridx=3;
		c.gridy=1;
		c.insets = new Insets(0,20,15,0);
		add(filterButton, c);

		c.anchor = GridBagConstraints.CENTER;
		JButton bookButton=new JButton("Book");
		FinderBookListener bookListen=new FinderBookListener(list, frame, user);
		bookButton.addActionListener(bookListen);
		bookButton.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		bookButton.setForeground(Color.white);
		bookButton.setBackground(new Color(145,0,0));
		bookButton.setOpaque(true);
		bookButton.setBorderPainted(false);

		c.gridx=1;
		c.gridy=4;
		c.insets = new Insets(20,0,0,0);
		add(bookButton, c);
	
		revalidate();
		repaint();
	}

	public List<Booking> getFreeRooms(){
		return this.freeRoomsBackUp;
	}

}


