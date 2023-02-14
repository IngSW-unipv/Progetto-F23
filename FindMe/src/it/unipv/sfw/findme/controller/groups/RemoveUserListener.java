package it.unipv.sfw.findme.controller.groups;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JList;
import it.unipv.sfw.findme.model.exceptions.ExceptionFrame;
import it.unipv.sfw.findme.model.groups.Group;
import it.unipv.sfw.findme.model.users.general_user.Users;
import it.unipv.sfw.findme.model.users.student.Student;
import it.unipv.sfw.findme.view.groups.GroupsListPanel;
import it.unipv.sfw.findme.view.groups.UsersListFrame;
import it.unipv.sfw.findme.view.users.general_user.UserGUI;

public class RemoveUserListener implements ActionListener{

	private Student user;
	private Group group;
	private UserGUI studentsGUI;
	private JList<Student> list;
	private UsersListFrame frame;


	public RemoveUserListener(JList<Student> list, Users user, Group group, UserGUI studentsGUI, UsersListFrame frame) {
		this.user=(Student)user;
		this.list=list;
		this.group=group;
		this.frame=frame;
		this.studentsGUI=studentsGUI;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if(list.getSelectedValue().getID().equals(user.getID())) {
				throw new IllegalArgumentException();
			}
		}catch(IllegalArgumentException e1) {
			new ExceptionFrame("You can't remove yourself");
			return;
		}


		try {
			user.removeStudent(group, list.getSelectedValue());
		} catch (Exception e1) {
			new ExceptionFrame("No User Selected!");
			return;
		}
		new ExceptionFrame("User Removed Succesfully!");
		frame.dispose();
		studentsGUI.removePanel();
		studentsGUI.addSecondPanel(new GroupsListPanel(user, studentsGUI));
		studentsGUI.revalidate();
		studentsGUI.repaint();


	}

}
