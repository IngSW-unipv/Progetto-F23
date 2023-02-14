package it.unipv.sfw.findme.controller.groups;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JList;
import it.unipv.sfw.findme.model.exceptions.ExceptionFrame;
import it.unipv.sfw.findme.model.groups.Group;
import it.unipv.sfw.findme.model.groups.dao.GroupDAO;
import it.unipv.sfw.findme.model.users.general_user.Users;
import it.unipv.sfw.findme.model.users.student.Student;
import it.unipv.sfw.findme.view.groups.UsersListFrame;
import it.unipv.sfw.findme.view.users.general_user.UserGUI;

public class UsersListListener implements ActionListener{
	
	private Users user;
	private UserGUI studentsGUI;
	private JList<Group> list;
	
	public UsersListListener(JList<Group> list, Users user, UserGUI studentsGUI) {
		this.user=user;
		this.list=list;
		this.studentsGUI=studentsGUI;	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Student student=(Student)user;
		ArrayList<Student> partecipants=new ArrayList<Student>();
		GroupDAO dao=new GroupDAO();
		try {
		partecipants=dao.selectPartecipantsFromGroup(list.getSelectedValue());
		}catch(Exception ex) {
			new ExceptionFrame("No Group Selected!");
		}
		UsersListFrame listFrame=new UsersListFrame(partecipants, student, list.getSelectedValue(), studentsGUI);
		
	}

}
