package it.unipv.sfw.findme.view.groups;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import it.unipv.sfw.findme.controller.groups.AddGroupNameListener;
import it.unipv.sfw.findme.model.exceptions.ExceptionFrame;
import it.unipv.sfw.findme.model.users.general_user.Users;
import it.unipv.sfw.findme.model.users.student.Student;
import it.unipv.sfw.findme.view.users.general_user.UserGUI;

public class AddGroupNameFrame  extends JFrame{

	public AddGroupNameFrame(Users user, UserGUI studentsGUI) {
		setSize(400,200);
		setTitle("Insert Group Name");

		setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();

		JLabel insertName=new JLabel("Insert Group Name");
		insertName.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		insertName.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=0;
		c.insets= new Insets(0,0,10,0);
		add(insertName, c);

		JTextField nameField=new JTextField(10);
		AddGroupNameListener confirm=new AddGroupNameListener(user, nameField, this, studentsGUI);
		nameField.addActionListener(confirm);
		c.gridx=0;
		c.gridy=1;
		add(nameField, c);

		JButton okButton=new JButton("OK");
		okButton.addActionListener(confirm);
		okButton.setFont(new Font("Comic Sans MS", Font.BOLD,10));
		okButton.setBackground(new Color(145,0,0));
		okButton.setForeground(Color.white);
		okButton.setOpaque(true);
		okButton.setBorderPainted(false);
		c.gridx=0;
		c.gridy=2;
		c.insets= new Insets(10,0,0,0);
		add(okButton, c);
		ImageIcon icon=new ImageIcon("Resources/Images/logo4.png");
		setIconImage(icon.getImage());

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);

	}
}



