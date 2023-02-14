package it.unipv.sfw.findme.controller.users.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.util.Properties;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import it.unipv.sfw.findme.model.rooms.Rooms;
import it.unipv.sfw.findme.model.users.admin.Admin;
import it.unipv.sfw.findme.view.users.general_user.UserGUI;

public class AddRoomListener implements ActionListener{
	
	private JTextField roomCodeField;
	private JComboBox<String> typeBox;
	private JTextField roomSeatsField;
	private JComboBox<String> yesNoLIM;
	private JComboBox<String> yesNoOutlets;
	private JComboBox<String> yesNoDisabledAccess;
	private UserGUI frame;

	public AddRoomListener(JTextField roomCodeField, JComboBox<String> typeBox, JTextField roomSeatsField,
			JComboBox<String> yesNoLIM, JComboBox<String> yesNoOutlets, JComboBox<String> yesNoDisabledAccess,
			UserGUI frame) {
		this.roomCodeField = roomCodeField;
		this.typeBox = typeBox;
		this.roomSeatsField = roomSeatsField;
		this.yesNoLIM = yesNoLIM;
		this.yesNoOutlets = yesNoOutlets;
		this.yesNoDisabledAccess = yesNoDisabledAccess;
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String code=roomCodeField.getText();
		String type=(String)typeBox.getSelectedItem();
		String seatsNumber=roomSeatsField.getText();
		String limString=(String)yesNoLIM.getSelectedItem();
		String outlets=(String)yesNoOutlets.getSelectedItem();
		String disabled=(String)yesNoDisabledAccess.getSelectedItem();
		
		Admin user=(Admin)frame.getUser();
		Properties config= new Properties();
		try {
			FileInputStream fis;
			fis = new FileInputStream("Resources/Property/config.properties");
			config.load(fis);

		String roomClassName=config.getProperty(type);
		Rooms r=(Rooms)Class.forName(roomClassName).getDeclaredConstructor(String.class, String.class, String.class, String.class, String.class, String.class).newInstance(code, type, seatsNumber, limString, outlets, disabled);
		user.addRoom(r);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
