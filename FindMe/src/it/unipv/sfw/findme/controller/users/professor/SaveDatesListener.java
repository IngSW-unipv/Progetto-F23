package it.unipv.sfw.findme.controller.users.professor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import org.jdatepicker.impl.JDatePanelImpl;
import it.unipv.sfw.findme.model.datemanager.MyTimer;
import it.unipv.sfw.findme.model.exceptions.ExceptionFrame;
import it.unipv.sfw.findme.model.users.general_user.Users;
import it.unipv.sfw.findme.view.users.general_user.UserGUI;
import it.unipv.sfw.findme.view.users.professor.ConfirmSwapPanel;
import it.unipv.sfw.findme.view.users.professor.SwapPanel;

public class SaveDatesListener implements ActionListener{

	private SwapPanel panel;
	private JDatePanelImpl datePanel;
	private UserGUI mainGUI;
	private Users user;

	public SaveDatesListener(SwapPanel panel, JDatePanelImpl datePanel, UserGUI mainGUI, Users user) {
		this.panel=panel;
		this.datePanel=datePanel;
		this.mainGUI=mainGUI;
		this.user=user;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MyTimer time=new MyTimer();

		Date currentDate=Date.valueOf(time.getJavaDate().toString());	

		try {
			if(currentDate.compareTo((Date)this.datePanel.getModel().getValue())>0) {
				throw new IllegalArgumentException();
			}
		}catch(Exception ex) {
			new ExceptionFrame("Invalid Date");
			return;
		}



		if(this.panel.getFirstDate()==null) {
			this.mainGUI.removePanel();
			SwapPanel swap=new SwapPanel(this.user, this.mainGUI, (Date)this.datePanel.getModel().getValue(), null);
			swap.setLabelText("(Optional) Select a preference");
			this.mainGUI.addSecondPanel(swap);
			this.mainGUI.revalidate();
			this.mainGUI.repaint();
		}
		else {
			if(this.panel.getFirstDate().compareTo((Date)this.datePanel.getModel().getValue())<0) {
				this.mainGUI.removePanel();
				this.mainGUI.addSecondPanel(new ConfirmSwapPanel(this.panel.getFirstDate(), (Date)this.datePanel.getModel().getValue(), this.user, this.mainGUI, false));
				this.mainGUI.revalidate();
				this.mainGUI.repaint();
			}
			else {
				new ExceptionFrame("Not a Valid Date");
				return;
			}
		}

	}

}
