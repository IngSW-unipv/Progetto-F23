package it.unipv.sfw.findme.controller.users.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import it.unipv.sfw.findme.model.courses.Course;
import it.unipv.sfw.findme.model.exceptions.ExceptionFrame;
import it.unipv.sfw.findme.model.users.admin.Admin;
import it.unipv.sfw.findme.view.users.admin.ViewCoursesPanel;
import it.unipv.sfw.findme.view.users.general_user.UserGUI;

public class RemoveCourseListener implements ActionListener{

	private UserGUI frame;
	private JTable table;
	
	public RemoveCourseListener(UserGUI frame, JTable table) {
		this.frame=frame;
		this.table=table;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int row=table.getSelectedRow();
		String code=(String) table.getValueAt(row, 0);
		
		Admin user=(Admin)frame.getUser();
		user.removeCourse(new Course(code, null, 0));
		
		this.frame.removePanel();
		this.frame.addSecondPanel(new ViewCoursesPanel(this.frame));
		this.frame.revalidate();
		this.frame.repaint();
		
		new ExceptionFrame("Course Removed Successfully!");
		
	}
}
