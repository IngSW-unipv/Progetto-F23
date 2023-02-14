package it.unipv.sfw.findme.controller.users.general_user;

import java.awt.event.*;
import javax.swing.*;

public class YearComboBoxListener implements ActionListener{
	private String[] months;
	private JComboBox<String> monthsSelect;
	private JComboBox<String> yearSelect;
	public YearComboBoxListener(String[] months, JComboBox<String> monthsSelect, JComboBox<String> yearSelect) {
		this.months=months;
		this.monthsSelect=monthsSelect;
		this.yearSelect=yearSelect;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		this.yearSelect.removeItem("Year");
		DefaultComboBoxModel<String> df=new DefaultComboBoxModel<String>(months);
		monthsSelect.setModel(df);
	}

}