package it.unipv.sfw.findme.controller.groups;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTextField;
import it.unipv.sfw.findme.model.exceptions.ExceptionFrame;
import it.unipv.sfw.findme.model.users.general_user.Users;
import it.unipv.sfw.findme.model.users.student.Student;
import it.unipv.sfw.findme.view.groups.GroupsListPanel;
import it.unipv.sfw.findme.view.users.general_user.UserGUI;

public class AddGroupNameListener implements ActionListener{
	private Student user;
	private JTextField name;
	private JFrame frame;
	private UserGUI studentsGUI;

	public AddGroupNameListener(Users user, JTextField name, JFrame frame, UserGUI studentsGUI) {
		this.user=(Student)user;
		this.name=name;
		this.frame=frame;
		this.studentsGUI=studentsGUI;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			checkName(this.name.getText());
		}catch(IllegalArgumentException ex) {
			new ExceptionFrame("Field can't be empty");
			return;
		}

		this.user.createGroup(this.name.getText());
		this.studentsGUI.removePanel();
		this.studentsGUI.addSecondPanel(new GroupsListPanel(this.user, this.studentsGUI));
		this.studentsGUI.revalidate();
		this.studentsGUI.repaint();
		frame.dispose();
	}

	public void checkName(String nameGroup){
		if(nameGroup.isEmpty()==true) {
			throw new IllegalArgumentException();
		}
	}

}