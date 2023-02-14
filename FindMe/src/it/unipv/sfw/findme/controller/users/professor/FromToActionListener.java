package it.unipv.sfw.findme.controller.users.professor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class FromToActionListener implements ActionListener{

	private JComboBox<String> startTimeBox;
	private JComboBox<String> endTimeBox;
	public FromToActionListener(JComboBox<String> startTimeBox, JComboBox<String> endTimeBox) {
		this.startTimeBox=startTimeBox;
		this.endTimeBox=endTimeBox;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.startTimeBox.removeItem("From");
		String timeString=(String)this.startTimeBox.getSelectedItem();
		String[] temp=timeString.split(":");
		int timeFrom=Integer.parseInt(temp[0]);
		int timeEnd=timeFrom+1;
		String[] timeEndArray=new String[1];
		timeEndArray[0]=Integer.toString(timeEnd)+":00";
		DefaultComboBoxModel<String> df=new DefaultComboBoxModel<String>(timeEndArray);
		endTimeBox.setModel(df);
	}
		
}