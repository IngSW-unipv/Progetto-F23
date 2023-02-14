package it.unipv.sfw.findme.controller.groups;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;
import it.unipv.sfw.findme.model.exceptions.ExceptionFrame;
import it.unipv.sfw.findme.model.groups.Group;
import it.unipv.sfw.findme.model.users.general_user.Users;
import it.unipv.sfw.findme.model.users.student.Student;
import it.unipv.sfw.findme.view.groups.GroupsListPanel;
import it.unipv.sfw.findme.view.users.general_user.UserGUI;

public class ConfirmAddUserListener implements ActionListener{
	
	private JList<Group> list;
	private JTextField emailOrID;
	private JFrame frame;
	private UserGUI studentsGUI;
	private Student user;
	
	public ConfirmAddUserListener(JList<Group> list, JTextField emailOrID, JFrame frame, UserGUI studentsGUI, Users user) {
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
