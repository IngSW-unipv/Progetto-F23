package it.unipv.sfw.findme.view.users.admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import it.unipv.sfw.findme.controller.users.admin.AddRoomListener;
import it.unipv.sfw.findme.view.users.general_user.UserGUI;


public class AddRoomPanel extends JPanel{

	public AddRoomPanel(UserGUI frame) {

		String[] yesNoArray=new String[]{"yes", "no"};
		String[] roomTypeArray=new String[]{"SMALL", "BIG", "LAB"};
	
		setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		setBackground(Color.white);
		
		
		c.anchor = GridBagConstraints.CENTER;
		JLabel roomCode=new JLabel("Room Code: ");
		roomCode.setFont(new Font("Comic Sans MS", Font.BOLD,17));
		roomCode.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=0;
		c.insets= new Insets (0,0,20,0);
		add(roomCode, c);
		//
		JTextField roomCodeField=new JTextField(5);
		c.gridx=1;
		c.gridy=0;
		add(roomCodeField, c);
		//
		JLabel roomSeats=new JLabel("Room Seats: ");
		roomSeats.setFont(new Font("Comic Sans MS", Font.BOLD,17));
		roomSeats.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=1;
		add(roomSeats, c);
		//
		JTextField roomSeatsField=new JTextField(5);
		c.gridx=1;
		c.gridy=1;
		add(roomSeatsField, c);
		//
		JLabel lim=new JLabel("Equipped with LIM: ");
		lim.setFont(new Font("Comic Sans MS", Font.BOLD,17));
		lim.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=2;
		add(lim, c);
		//
		JComboBox<String> yesNoLIM=new JComboBox<String>(yesNoArray);
		yesNoLIM.setBackground(Color.WHITE);
		yesNoLIM.setForeground(new Color(145,0,0));
		c.gridx=1;
		c.gridy=2;
		add(yesNoLIM, c);
		//
		JLabel outlets=new JLabel("Equipped with Outlets: ");
		outlets.setFont(new Font("Comic Sans MS", Font.BOLD,17));
		outlets.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=3;
		add(outlets, c);
		//
		JComboBox<String> yesNoOutlets=new JComboBox<String>(yesNoArray);
		yesNoOutlets.setBackground(Color.WHITE);
		yesNoOutlets.setForeground(new Color(145,0,0));
		c.gridx=1;
		c.gridy=3;
		add(yesNoOutlets, c);
		//
		JLabel disabledAccess=new JLabel("Access for disabled: ");
		disabledAccess.setFont(new Font("Comic Sans MS", Font.BOLD,17));
		disabledAccess.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=4;
		add(disabledAccess, c);
		JComboBox<String> yesNoDisabledAccess=new JComboBox<String>(yesNoArray);
		yesNoDisabledAccess.setBackground(Color.WHITE);
		yesNoDisabledAccess.setForeground(new Color(145,0,0));
		c.gridx=1;
		c.gridy=4;
		add(yesNoDisabledAccess, c);
		//
		JLabel roomType=new JLabel("Room Type: ");
		roomType.setFont(new Font("Comic Sans MS", Font.BOLD,17));
		roomType.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=5;
		add(roomType, c);
		//
		JComboBox<String> typeBox=new JComboBox<String>(roomTypeArray);
		typeBox.setBackground(Color.WHITE);
		typeBox.setForeground(new Color(145,0,0));
		c.gridx=1;
		c.gridy=5;
		add(typeBox, c);
		//
		JButton confirm=new JButton("Add");
		confirm.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
		confirm.setForeground(Color.white);
		confirm.setBackground(new Color(145,0,0));
		confirm.setOpaque(true);
		confirm.setBorderPainted(false);
		AddRoomListener addRoom=new AddRoomListener(roomCodeField, typeBox, roomSeatsField, yesNoLIM, yesNoOutlets, yesNoDisabledAccess, frame);
		confirm.addActionListener(addRoom);
		c.gridx=0;
		c.gridy=6;
		add(confirm, c);
	
	}
}