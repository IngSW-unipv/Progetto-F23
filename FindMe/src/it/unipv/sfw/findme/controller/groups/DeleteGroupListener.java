package it.unipv.sfw.findme.controller.groups;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JList;
import it.unipv.sfw.findme.model.exceptions.ExceptionFrame;
import it.unipv.sfw.findme.model.groups.Group;
import it.unipv.sfw.findme.model.users.general_user.Users;
import it.unipv.sfw.findme.model.users.student.Student;
import it.unipv.sfw.findme.view.groups.GroupsListPanel;
import it.unipv.sfw.findme.view.users.general_user.UserGUI;

public class DeleteGroupListener implements ActionListener{
	
	private Users user;
	private UserGUI studentsGUI;
	private JList<Group> list;
	
	public DeleteGroupListener(JList<Group> list, Users user, UserGUI studentsGUI) {
		this.user=user;
		this.list=list;
		this.studentsGUI=studentsGUI;		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Student student=(Student)user;
		try {
		student.removeGroup(list.getSelectedValue());
		} catch (Exception ex) {
			new ExceptionFrame("No Group Selected!");
			return;
		}
		studentsGUI.removePanel();
		studentsGUI.addSecondPanel(new GroupsListPanel(user, studentsGUI));
		studentsGUI.revalidate();
		studentsGUI.repaint();
		new ExceptionFrame("Group Deleted Succesfully");
	}
		
	}

