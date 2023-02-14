package it.unipv.sfw.findme.view.users.professor;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import it.unipv.sfw.findme.controller.users.professor.SwapListener;
import it.unipv.sfw.findme.controller.users.professor.FromToActionListener;
import it.unipv.sfw.findme.model.rooms.Rooms;
import it.unipv.sfw.findme.model.rooms.dao.RoomDAO;
import it.unipv.sfw.findme.model.users.general_user.Users;
import it.unipv.sfw.findme.view.users.general_user.UserGUI;

public class ConfirmSwapPanel extends JPanel{
	
	private String[] startHours= {"From","9:00", "10:00", "11:00", "12:00", "14:00", "15:00", "16:00", "17:00"};

	public ConfirmSwapPanel(Date firstDate, Date secondDate, Users User, UserGUI mainGUI, boolean skip) {


		setLayout (new GridBagLayout());
		setBackground(Color.white);
		GridBagConstraints c=new GridBagConstraints();

		JLabel firstDateLabel=new JLabel("Swappable date: "+firstDate.toString()+" ");
		firstDateLabel.setFont(new Font("Comic Sans MS", Font.BOLD,17));
		firstDateLabel.setForeground(new Color(145,0,0));
		c.insets= new Insets (5,0,0,0);
		c.gridx=0;
		c.gridy=0;
		add(firstDateLabel, c);
		JLabel secondDateLabel;
		if(secondDate==null) {
			secondDateLabel=new JLabel("No preference specified");
			secondDateLabel.setFont(new Font("Comic Sans MS", Font.BOLD,17));
			secondDateLabel.setForeground(new Color(145,0,0));
		}
		else {
			secondDateLabel=new JLabel("Substitute date: "+secondDate.toString()+" ");
			secondDateLabel.setFont(new Font("Comic Sans MS", Font.BOLD,17));
			secondDateLabel.setForeground(new Color(145,0,0));
		}
		c.gridx=0;
		c.gridy=1;
		add(secondDateLabel, c);


		JComboBox<String> endTimeBox=new JComboBox<String>();
		endTimeBox.addItem("To");
		endTimeBox.setFocusable(false);
		endTimeBox.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		endTimeBox.setForeground(new Color(145,0,0));
		endTimeBox.setBackground(Color.WHITE);
		c.insets= new Insets (0,5,0,0);
		c.gridx=2;
		c.gridy=0;
		add(endTimeBox, c);

		JComboBox<String> startTimeBox=new JComboBox<String>(startHours);
		FromToActionListener sl=new FromToActionListener(startTimeBox, endTimeBox);
		startTimeBox.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		startTimeBox.setForeground(new Color(145,0,0));
		startTimeBox.setBackground(Color.WHITE);
		startTimeBox.addActionListener(sl);
		startTimeBox.setFocusable(false);
		
		c.gridx=1;
		c.gridy=0;
		add(startTimeBox, c);

		JComboBox<String> secondEndTimeBox=new JComboBox<String>();
		JComboBox<String> secondStartTimeBox=new JComboBox<String>(startHours);
		
		if(secondDate!=null) {
			secondEndTimeBox=new JComboBox<String>();
			secondEndTimeBox.addItem("To");
			secondEndTimeBox.setFocusable(false);
			secondEndTimeBox.setFont(new Font("Comic Sans MS", Font.BOLD,15));
			secondEndTimeBox.setForeground(new Color(145,0,0));
			secondEndTimeBox.setBackground(Color.WHITE);
			c.gridx=2;
			c.gridy=1;
			add(secondEndTimeBox, c);

			secondStartTimeBox=new JComboBox<String>(startHours);
			sl=new FromToActionListener(secondStartTimeBox, secondEndTimeBox);
			secondStartTimeBox.addActionListener(sl);
			secondStartTimeBox.setFocusable(false);
			secondStartTimeBox.setFont(new Font("Comic Sans MS", Font.BOLD,15));
			secondStartTimeBox.setForeground(new Color(145,0,0));
			secondStartTimeBox.setBackground(Color.WHITE);
			c.gridx=1;
			c.gridy=1;
			add(secondStartTimeBox, c);


		}
		JComboBox<Rooms> possibleRooms=new JComboBox();
		try {
			RoomDAO dao=new RoomDAO();

			HashMap<String, Rooms> allRooms=dao.selectAllRooms();
			ArrayList<Rooms> roomsList=new ArrayList<Rooms>();
			for(HashMap.Entry<String, Rooms> entry : allRooms.entrySet()) {
				roomsList.add(entry.getValue());
			}
			Collections.sort(roomsList);


			possibleRooms=new JComboBox(roomsList.toArray());
			possibleRooms.setFont(new Font("Comic Sans MS", Font.BOLD,15));
			possibleRooms.setForeground(new Color(145,0,0));
			possibleRooms.setBackground(Color.WHITE);
			c.gridx=3;
			c.gridy=0;
			add(possibleRooms, c);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		JButton swapButton=new JButton("Send Swap Request");
		swapButton.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
		swapButton.setForeground(Color.WHITE);
		swapButton.setBackground(new Color(145,0,0));
		
		swapButton.setOpaque(true);
		swapButton.setBorderPainted(false);
		swapButton.addActionListener(new SwapListener(firstDate, secondDate, User, mainGUI, possibleRooms, startTimeBox, endTimeBox, secondStartTimeBox, secondEndTimeBox, skip));
		c.gridx=4;
		c.gridy=0;
		add(swapButton, c);


	}

}