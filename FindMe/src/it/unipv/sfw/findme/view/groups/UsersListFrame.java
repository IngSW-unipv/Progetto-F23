package it.unipv.sfw.findme.view.groups;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import it.unipv.sfw.findme.controller.groups.RemoveUserListener;
import it.unipv.sfw.findme.model.groups.Group;
import it.unipv.sfw.findme.model.users.student.Student;
import it.unipv.sfw.findme.view.users.general_user.UserGUI;

public class UsersListFrame extends JFrame{

	public UsersListFrame(ArrayList<Student> studentsList, Student user, Group group, UserGUI studentsGUI) {
		
		
	JPanel mainPanel=new JPanel();
	mainPanel.setLayout (new GridBagLayout());
	GridBagConstraints c=new GridBagConstraints();
	
	ImageIcon icon=new ImageIcon("Resources/Images/logo4.png");
	setIconImage(icon.getImage());
	mainPanel.setBackground(Color.white);
	
	JLabel title=new JLabel("List");
	title.setFont(new Font("Comic Sans MS", Font.BOLD,17));
	title.setForeground(new Color(145,0,0));
	c.gridx=0;
	c.gridy=0;
	c.insets = new Insets(0,0,10,0);
	mainPanel.add(title, c);
	
	JList<Student> list=new JList(studentsList.toArray());
	list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	JScrollPane scroll = new JScrollPane(list);
	scroll.setBorder(new LineBorder(new Color(145,0,0),2));
	scroll.setPreferredSize(new Dimension(250, 200));
	c.gridx=0;
	c.gridy=1;
	mainPanel.add(scroll, c);	
	
	
	JButton removeUser=new JButton("Remove");
	removeUser.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
	removeUser.setForeground(Color.WHITE);
	removeUser.setBackground(new Color(145,0,0));	
	removeUser.setOpaque(true);
	removeUser.setBorderPainted(false);
	RemoveUserListener removeListener=new RemoveUserListener(list, user, group, studentsGUI, this);
	removeUser.addActionListener(removeListener);
	c.gridx=0;
	c.gridy=2;
	mainPanel.add(removeUser, c);
	
	
	add(mainPanel);
	setSize(600,400);
	setTitle("List");

	setLocationRelativeTo(null);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setVisible(true);
	}

}
