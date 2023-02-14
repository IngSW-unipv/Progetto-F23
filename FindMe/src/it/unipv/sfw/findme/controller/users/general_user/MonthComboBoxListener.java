package it.unipv.sfw.findme.controller.users.general_user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import it.unipv.sfw.findme.model.enums.MonthType;


public class MonthComboBoxListener implements ActionListener{
	private JComboBox<String> monthsBox;
	private MonthType type;
	private boolean leapYear;
	private JComboBox<String> daysSelect;
	private JComboBox<String> monthsSelect;
	public MonthComboBoxListener(JComboBox<String> monthsBox, boolean leapYear, JComboBox<String> daysSelect, JComboBox<String> monthsSelect) {
		this.monthsBox=monthsBox;
		this.leapYear=leapYear;
		this.daysSelect=daysSelect;
		this.monthsSelect=monthsSelect;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		this.monthsSelect.removeItem("Month");
		String tempMonth=(String)this.monthsBox.getSelectedItem();
		checkMonth(this.type, tempMonth);
		String[] days=CreateDaysList(this.type);
		this.daysSelect.removeItem("Day");
		DefaultComboBoxModel<String> m=new DefaultComboBoxModel<String>(days);
		this.daysSelect.setModel(m);
	}

	public void checkMonth(MonthType type, String tempMonth) {
		if(tempMonth.equals("November") || tempMonth.equals("April") || tempMonth.equals("June") || tempMonth.equals("September")) {
			this.type=MonthType.Month30;
		}
		else if(tempMonth.equals("February") && this.leapYear==false) {
			this.type=MonthType.Month28;
		}
		else if(tempMonth.equals("February") && this.leapYear==true) {
			this.type=MonthType.Month29;
		}
		else {
			this.type=MonthType.Month31;
		}
	}
	
	public String[] CreateDaysList(MonthType type) {
		String[] allDays;
		switch(type) {
		case Month30:{
			String[] days= new String[30];
			for(int i=0; i<30; i++) {
				days[i]=Integer.toString(i+1);
			}
			return allDays=days;
		}
		case Month28:{
			String[] days= new String[28];
			for(int i=0; i<28; i++) {
				days[i]=Integer.toString(i+1);
			}
			return allDays=days;
		}
		case Month29:{
			String[] days= new String[29];
			for(int i=0; i<29; i++) {
				days[i]=Integer.toString(i+1);
			}
			return allDays=days;
		}
		case Month31:{
			String[] days= new String[31];
			for(int i=0; i<31; i++) {
				days[i]=Integer.toString(i+1);
			}
			return allDays=days;
		}
		}
		return null;
	}
	
}