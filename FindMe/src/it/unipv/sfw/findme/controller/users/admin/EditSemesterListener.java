package it.unipv.sfw.findme.controller.users.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import org.jdatepicker.impl.JDatePanelImpl;
import it.unipv.sfw.findme.model.exceptions.ExceptionFrame;
import it.unipv.sfw.findme.model.users.admin.Admin;
import it.unipv.sfw.findme.view.users.general_user.UserGUI;

public class EditSemesterListener implements ActionListener{
	
	private JDatePanelImpl startCalendar;
	private JDatePanelImpl endCalendar;
	private UserGUI frame;
	
	public EditSemesterListener(JDatePanelImpl startCalendar, JDatePanelImpl endCalendar, UserGUI frame) {
		this.startCalendar = startCalendar;
		this.endCalendar = endCalendar;
		this.frame = frame;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Date startDate=(Date)startCalendar.getModel().getValue();
		Date endDate=(Date)endCalendar.getModel().getValue();
		
		Admin user=(Admin)frame.getUser();
		user.updateSemester(startDate, endDate);
		
		new ExceptionFrame("Semester Updated");
		
	}

}
