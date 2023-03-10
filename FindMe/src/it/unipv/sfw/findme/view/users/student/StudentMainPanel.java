package it.unipv.sfw.findme.view.users.student;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unipv.sfw.findme.model.users.general_user.Users;
import it.unipv.sfw.findme.model.users.student.FindSchedule;
import it.unipv.sfw.findme.view.groups.GroupsListPanel;
import it.unipv.sfw.findme.view.login.LoginGUI;
import it.unipv.sfw.findme.view.users.admin.ViewCoursesPanel;
import it.unipv.sfw.findme.view.users.general_user.UserGUI;


public class StudentMainPanel extends JPanel{
		public StudentMainPanel(String name, String lastName, String email, UserGUI mainGUI, Users user) {		
			setLayout (new GridBagLayout());
			GridBagConstraints c=new GridBagConstraints();
			
			setBackground(new Color(145,0,0));
			
			c.anchor = GridBagConstraints.FIRST_LINE_START;
			JLabel Label=new JLabel("PROFILE");
			Label.setFont(new Font("Comic Sans MS", Font.BOLD,25));
			Label.setForeground(Color.white);
			c.insets= new Insets(0,10,0,0);
			c.gridx=0;
			c.gridy=0;
			add(Label, c);
			
			c.anchor = GridBagConstraints.FIRST_LINE_START;
			JLabel studentNameLabel=new JLabel("Name: ");
			studentNameLabel.setFont(new Font("Comic Sans MS", Font.BOLD,15));
			studentNameLabel.setForeground(Color.white);
			c.insets= new Insets(10,10,0,0);
			c.gridx=0;
			c.gridy=1;
			add(studentNameLabel, c);
			//
			c.anchor = GridBagConstraints.FIRST_LINE_END;
			JLabel studentName=new JLabel(name);
			studentName.setFont(new Font("Comic Sans MS", Font.BOLD,15));
			studentName.setForeground(new Color(217, 217, 217));
			c.gridx=1;
			c.gridy=1;
			add(studentName, c);
			//
			c.anchor = GridBagConstraints.FIRST_LINE_START;
			JLabel studentLastNameLabel=new JLabel("Last Name: ");
			studentLastNameLabel.setFont(new Font("Comic Sans MS", Font.BOLD,15));
			studentLastNameLabel.setForeground(Color.white);
			c.insets= new Insets(10,10,0,0);
			c.gridx=0;
			c.gridy=2;
			add(studentLastNameLabel, c);
			//
			c.anchor = GridBagConstraints.FIRST_LINE_END;
			JLabel studentLastName=new JLabel(lastName);
			studentLastName.setFont(new Font("Comic Sans MS", Font.BOLD,15));
			studentLastName.setForeground(new Color(217, 217, 217));
			c.gridx=1;
			c.gridy=2;
			add(studentLastName, c);
			//
		
			c.anchor = GridBagConstraints.PAGE_START;
			ImageIcon friendsIcon=new ImageIcon("Resources/Images/users-icon.png");
			Image image = friendsIcon.getImage();
			Image newimg = image.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);  
			friendsIcon = new ImageIcon(newimg);
			JButton groupsButton=new JButton(friendsIcon);
			groupsButton.setBackground(Color.WHITE);
			//
			groupsButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					mainGUI.removePanel();
					mainGUI.addSecondPanel(new GroupsListPanel(user, mainGUI));
					mainGUI.revalidate();
					mainGUI.repaint();
				}
			});
			c.insets= new Insets(15,0,35,10);
			c.gridx=0;
			c.gridy=4;
			add(groupsButton, c);
			//
			c.anchor = GridBagConstraints.EAST;
			ImageIcon clockIcon=new ImageIcon("Resources/Images/clock-icon.png");
			Image clock = clockIcon.getImage();
			Image tempClock = clock.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);  
			clockIcon = new ImageIcon(tempClock);
			JButton scheduleButton=new JButton(clockIcon);
			scheduleButton.setBackground(Color.WHITE);
			scheduleButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					mainGUI.removePanel();
					ViewCoursesPanel view=new ViewCoursesPanel(mainGUI);
					view.remove(view.getButtonsPanel());
					
					FindSchedule findSchedulePanel=new FindSchedule(view, mainGUI);

					mainGUI.addSecondPanel(findSchedulePanel);
					mainGUI.revalidate();
					mainGUI.repaint();
				}

			});
			c.gridx=1;
			c.gridy=4;
			c.insets= new Insets(0,0,50,0);
			add(scheduleButton, c);
			
			ImageIcon calendarIcon=new ImageIcon("Resources/Images/calendar-icon.png");
			Image calendar = calendarIcon.getImage();
			Image temp = calendar.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);  
			calendarIcon = new ImageIcon(temp);
			
			JButton bookings=new JButton(calendarIcon);
			bookings.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
			bookings.setBackground(Color.white);
			bookings.setForeground(new Color(145,0,0));
			bookings.setOpaque(true);
			bookings.setBorderPainted(false);
			c.gridx=1;
			c.gridy=4;
			c.insets= new Insets(70,10,10,0);
			bookings.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					mainGUI.removePanel();
					BookingsListPanel bookingsPanel=new BookingsListPanel(mainGUI);
					mainGUI.addSecondPanel(bookingsPanel);
					mainGUI.revalidate();
					mainGUI.repaint();
					
				}});
			add(bookings, c);
			
			JButton LogButton=new JButton("Sign out");
			LogButton.setFont(new Font("Comic Sans MS", Font.PLAIN,13));
			LogButton.setBackground(Color.white);
			LogButton.setForeground(new Color(145,0,0));
			LogButton.setOpaque(true);
			LogButton.setBorderPainted(false);
			LogButton.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) {
					LoginGUI l=new LoginGUI();
					mainGUI.dispose();
				}
			});
			
			c.gridx=0;
			c.gridy=4;
			c.insets= new Insets(70,0,10,10);
			add(LogButton, c);
			
		}
		
}
