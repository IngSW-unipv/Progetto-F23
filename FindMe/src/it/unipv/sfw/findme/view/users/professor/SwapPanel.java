package it.unipv.sfw.findme.view.users.professor;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.SqlDateModel;
import it.unipv.sfw.findme.controller.users.professor.SaveDatesListener;
import it.unipv.sfw.findme.model.exceptions.ExceptionFrame;
import it.unipv.sfw.findme.model.users.general_user.Users;
import it.unipv.sfw.findme.view.users.general_user.UserGUI;


public class SwapPanel extends JPanel{

	private Date firstDate;
	private Date secondDate;
	private JLabel calendarLabel;
	private boolean skip;


	public SwapPanel(Users user, UserGUI mainGUI, Date firstDate, Date secondDate) {
		this.firstDate=firstDate;
		this.secondDate=secondDate;

		setLayout (new GridBagLayout());
		setBackground(Color.white);
		GridBagConstraints c=new GridBagConstraints();

		c.anchor = GridBagConstraints.FIRST_LINE_START;
		this.calendarLabel=new JLabel("(Requiered) Select a day to Swap");
		calendarLabel.setFont(new Font("Comic Sans MS", Font.BOLD,25));
		calendarLabel.setForeground(new Color(145,0,0));
		c.gridx=1;
		c.gridy=0;
		c.insets= new Insets (0,0,100,0);
		add(calendarLabel, c);
		//
		SqlDateModel model= new SqlDateModel();

		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		c.gridx=1;
		c.gridy=1;
		c.insets= new Insets(0,100,50,0);
		add(datePanel, c);
		//
		JButton firstDateButton=new JButton("Next");
		firstDateButton.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
		firstDateButton.setForeground(Color.WHITE);
		firstDateButton.setBackground(new Color(145,0,0));		
		firstDateButton.setOpaque(true);
		firstDateButton.setBorderPainted(false);
		firstDateButton.addActionListener(new SaveDatesListener(this, datePanel, mainGUI, user));
		c.gridx=2;
		c.gridy=1;
		add(firstDateButton, c);
		//
		JButton skipSecondDate=new JButton("Skip");
		skipSecondDate.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
		skipSecondDate.setForeground(Color.WHITE);
		skipSecondDate.setBackground(new Color(145,0,0));		
		skipSecondDate.setOpaque(true);
		skipSecondDate.setBorderPainted(false);

		skipSecondDate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(firstDate!=null) {
					mainGUI.removePanel();
					mainGUI.addSecondPanel(new ConfirmSwapPanel(firstDate, null, user, mainGUI, true));
					mainGUI.revalidate();
					mainGUI.repaint();
				}
				else {
					new ExceptionFrame("You can't skip this step");
					return;
				}

			}

		});
		c.gridx=2;
		c.gridy=1;
		c.insets= new Insets(50,100,0,0);
		add(skipSecondDate, c);


	}

	public Date getFirstDate() {
		return firstDate;
	}


	public Date getSecondDate() {
		return secondDate;
	}

	public void setLabelText(String text) {
		this.calendarLabel.setText(text);
	}


}