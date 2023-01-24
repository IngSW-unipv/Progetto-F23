package it.unipv.sfw.findme.users.admin;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unipv.sfw.findme.users.general_user.UserGUI;


public class AdminMainPanel extends JPanel{
	
	public AdminMainPanel(String name, String lastName, String email, UserGUI frame) {
		setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		
		c.anchor = GridBagConstraints.EAST;
		JLabel adminNameLabel=new JLabel("Admin Name: ");
		c.gridx=0;
		c.gridy=0;
		add(adminNameLabel, c);
		c.anchor = GridBagConstraints.WEST;
		JLabel adminName=new JLabel(name);
		c.gridx=1;
		c.gridy=0;
		add(adminName, c);
		c.anchor = GridBagConstraints.EAST;
		JLabel adminLastNameLabel=new JLabel("Admin Last Name: ");
		c.gridx=0;
		c.gridy=1;
		add(adminLastNameLabel, c);
		c.anchor = GridBagConstraints.WEST;
		JLabel adminLastName=new JLabel(lastName);
		c.gridx=1;
		c.gridy=1;
		add(adminLastName, c);
		c.anchor = GridBagConstraints.EAST;
		JLabel editCourses=new JLabel("Edit Available Courses: ");
		c.gridx=0;
		c.gridy=2;
		add(editCourses, c);
		c.anchor = GridBagConstraints.WEST;
		JButton editCoursesButton=new JButton("Edit Courses");
		editCoursesButton.addActionListener(new NewPanelListener(frame, new ViewCoursesPanel(frame)));
		c.gridx=1;
		c.gridy=2;
		add(editCoursesButton, c);
		c.anchor = GridBagConstraints.EAST;
		JLabel addRoom=new JLabel("Edit Current Rooms: ");
		c.gridx=0;
		c.gridy=3;
		add(addRoom, c);
		c.anchor = GridBagConstraints.WEST;
		JButton addRoomButton=new JButton("Edit Rooms");
		addRoomButton.addActionListener(new NewPanelListener(frame, new AddRoomPanel(frame)));
		c.gridx=1;
		c.gridy=3;
		add(addRoomButton, c);
		c.anchor = GridBagConstraints.EAST;
		JLabel editSemesterLabel=new JLabel("Edit Semester: ");
		c.gridx=0;
		c.gridy=4;
		add(editSemesterLabel, c);
		c.anchor = GridBagConstraints.WEST;
		JButton editSemesterButton=new JButton("Edit Semester");
		editSemesterButton.addActionListener(new NewPanelListener(frame, new EditSemesterPanel(frame)));
		c.gridx=1;
		c.gridy=4;
		add(editSemesterButton, c);
		
	}

}