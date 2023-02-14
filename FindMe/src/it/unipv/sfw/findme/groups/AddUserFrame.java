package it.unipv.sfw.findme.groups;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;


import it.unipv.sfw.findme.exceptions.ExceptionFrame;
import it.unipv.sfw.findme.users.general_user.UserGUI;
import it.unipv.sfw.findme.users.general_user.Users;
import it.unipv.sfw.findme.users.student.Student;


public class AddUserFrame extends JFrame{
	
	public AddUserFrame(JList<Group> list, UserGUI studentsGUI, Users user) {
		setSize(400,200);
		setTitle("Add User");
		ImageIcon icon=new ImageIcon("Resources/Images/logo4.png");
		setIconImage(icon.getImage());

		setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();

		JLabel insertName=new JLabel("Insert Email or ID");
		insertName.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		insertName.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=0;
		c.insets= new Insets(0,0,10,0);
		add(insertName, c);

		JTextField nameField=new JTextField(20);
		c.gridx=0;
		c.gridy=1;
		add(nameField, c);

		JButton okButton=new JButton("OK");
		OkListener listener=new OkListener(list, nameField, this, studentsGUI, user);
		okButton.setFont(new Font("Comic Sans MS", Font.BOLD,10));
		okButton.setBackground(new Color(145,0,0));
		okButton.setForeground(Color.white);
		okButton.setOpaque(true);
		okButton.setBorderPainted(false);
		okButton.addActionListener(listener);
		c.gridx=0;
		c.gridy=2;
		c.insets= new Insets(10,0,0,0);
		add(okButton, c);

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);

	}

}


class OkListener implements ActionListener{
	
	private JList<Group> list;
	private JTextField emailOrID;
	private JFrame frame;
	private UserGUI studentsGUI;
	private Student user;
	
	public OkListener(JList<Group> list, JTextField emailOrID, JFrame frame, UserGUI studentsGUI, Users user) {
		this.list=list;
		this.emailOrID=emailOrID;
		this.frame=frame;
		this.user=(Student)user;
		this.studentsGUI=studentsGUI;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(this.emailOrID.getText().equals(this.user.getID()) || this.emailOrID.getText().equals(this.user.getEmail())) {
			new ExceptionFrame("You are already part of this Group!");
			return;
		}
	
		try {
			this.list.getSelectedValue().addNewStudent(this.emailOrID.getText(), this.list.getSelectedValue());
			this.studentsGUI.removePanel();
			this.studentsGUI.addSecondPanel(new GroupsListPanel(this.user, this.studentsGUI));
			this.studentsGUI.revalidate();
			this.studentsGUI.repaint();
			this.frame.dispose();
			
		} catch (Exception e1) {
			new ExceptionFrame("No Group Selected!");
			return;
		}
	}
	
}
