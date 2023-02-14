package it.unipv.sfw.findme.controller.users.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import it.unipv.sfw.findme.model.courses.Course;
import it.unipv.sfw.findme.model.exceptions.ExceptionFrame;
import it.unipv.sfw.findme.model.users.admin.Admin;
import it.unipv.sfw.findme.view.users.admin.ViewCoursesPanel;
import it.unipv.sfw.findme.view.users.general_user.UserGUI;

public class AddCourseListener implements ActionListener{
	
	private JTextField courseFullNameField;
	private JComboBox<String> yearsSelect;
	private JTextField courseCodeFiled;
	private UserGUI frame;
	
	public AddCourseListener(JTextField courseFullNameField, JComboBox<String> yearsSelect, JTextField courseCodeFiled, UserGUI frame) {
		this.courseFullNameField=courseFullNameField;
		this.yearsSelect=yearsSelect;
		this.courseCodeFiled=courseCodeFiled;
		this.frame=frame;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		String courseFullName=courseFullNameField.getText();
		String years=(String)yearsSelect.getSelectedItem();
		String courseCode=courseCodeFiled.getText();
		
		Admin user=(Admin)frame.getUser();
		user.addCourse(new Course(courseCode, courseFullName, Integer.parseInt(years)));
		
		frame.removePanel();
		frame.addSecondPanel(new ViewCoursesPanel(frame));
		frame.revalidate();
		frame.repaint();
		
		new ExceptionFrame("Course Added Successfully!");
		
	}

}
