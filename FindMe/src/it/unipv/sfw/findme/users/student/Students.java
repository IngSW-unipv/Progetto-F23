package it.unipv.sfw.findme.users.student;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.*;

import Groups.GroupsPanel;
import Notifications.Notification;
import Rooms.RoomDAO;
import Users.Students.StudentNotificationPanel;
import Users.Students.Students;
import Users.Students.StudentsGUI;
import Users.Students.StudentsMainPanel;
import it.unipv.sfw.findme.booking.Booking;
import it.unipv.sfw.findme.database.DBConnection;
import it.unipv.sfw.findme.exceptions.ExceptionFrame;
import it.unipv.sfw.findme.groups.Group;
import it.unipv.sfw.findme.groups.GroupDAO;
import it.unipv.sfw.findme.login.LoginGUI;
import it.unipv.sfw.findme.mytimer.DateHolder;
import it.unipv.sfw.findme.users.general_user.UserGUI;
import it.unipv.sfw.findme.users.general_user.Users;  

public class Students extends Users{

	private HashMap<String, Group> groups;

	public Students(String name, String lastName, String ID, String email, String password, String type) {
		super(name, lastName, ID, email, password,type);
		this.groups=new HashMap<String, Group>();
	}

	@Override
	public void GUI(LoginGUI frame) {
		StudentsGUI gui=new StudentsGUI(name, lastName, email, this);
		frame.dispose();
	}	

	public void createGroup(String groupName){
		try {
			GroupDAO dao=new GroupDAO();
			dao.insertGroup(new Group(groupName+"-"+this.ID, this.ID));			
		} catch (Exception e) {
			new ExceptionFrame("Group Name not Valid!");
			return;
		}	
	}
	
	
	public void removeGroup(Group group) throws Exception {
		GroupDAO dao=new GroupDAO();
			dao.deleteGroup(group);
	}
	
	
	public void removeStudent(Group group, Students student) throws Exception {
		GroupDAO dao=new GroupDAO();
		dao.deletePartecipant(group, student);
	}
	

	public HashMap<String, Group> getGroups(){
		return this.groups;
	}

	public JButton checkNotifications() {

		try {

			Connection conn=DBConnection.connect();
			String query="select * from group_notifications where Receiver=?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, Integer.parseInt(this.getID()));
			ResultSet result=preparedStmt.executeQuery();

			if (result.next() == true) {
				conn.close();
				ImageIcon notificationIcon=new ImageIcon("Files/bell-icon-active.png");
				Image image = notificationIcon.getImage();
				Image newimg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);  
				notificationIcon = new ImageIcon(newimg);
				JButton b= new JButton(notificationIcon);
				b.setBackground(Color.white);
				return b;
			}
			else {
				conn.close();
				ImageIcon notificationIcon=new ImageIcon("Files/bell-icon-inactive.png");
				Image image = notificationIcon.getImage();
				Image newimg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);  
				notificationIcon = new ImageIcon(newimg);
				JButton b= new JButton(notificationIcon);
				b.setBackground(Color.white);
				return b;
			}


		} catch (Exception e) {
		}
		return null;	

	}

	@Override
	public JPanel getMainPanel(UserGUI gui) {
		return new StudentsMainPanel(name, lastName, email, gui, this);
	}


	public JPanel book(Object[] objects, UserGUI frame) {
		int year=DateHolder.getYear();
		int month=DateHolder.getMonth();
		int day=DateHolder.getDay();

		JPanel bookPanel=new JPanel();

		bookPanel.setLayout (new GridBagLayout());
		bookPanel.setBackground(Color.white);
		GridBagConstraints c=new GridBagConstraints();

		GroupsPanel groupsPanel=new GroupsPanel(this, frame);
		HashMap<String, Group> groups=groupsPanel.getUserSpecificGroups();
		ArrayList<Group> groupsArray=new ArrayList<Group>();
		for(HashMap.Entry<String, Group> group : groups.entrySet()) {
			groupsArray.add(group.getValue());
		}
		JLabel selectGroup=new JLabel("Select Group: ");
		selectGroup.setFont(new Font("Comic Sans MS", Font.BOLD,25));
		selectGroup.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=0;
		c.insets= new Insets (0,0,50,0);
		bookPanel.add(selectGroup, c);
		JComboBox<Group> goupsBox=new JComboBox(groupsArray.toArray());
		goupsBox.setFont(new Font("Comic Sans MS", Font.PLAIN,17));
		goupsBox.setForeground(new Color(145,0,0));
		goupsBox.setFocusable(false);
		goupsBox.setBackground(Color.WHITE);
		c.gridx=0;
		c.gridy=1;
		bookPanel.add(goupsBox, c);
		//
		JButton groupButton=new JButton("Book as a Group");
		groupButton.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
		groupButton.setForeground(Color.WHITE);
		groupButton.setBackground(new Color(145,0,0));
		groupButton.setOpaque(true);
		groupButton.setBorderPainted(false);
		groupButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Booking booking=(Booking)objects[0];
				try {
					RoomDAO dao=new RoomDAO();
					dao.selectAllRooms().get(booking.getRoom().getCode());
					Group myGroup=(Group)goupsBox.getSelectedItem();
					booking.getRoom().book(myGroup, new Booking(booking.getStartTime(), booking.getEndTime(), Date.valueOf(year+"-"+month+"-"+day), null, null, null, null));
					frame.removePanel();
				} catch (Exception e1) {
					new ExceptionFrame("No Group Selected!");
				}

			}

		});
		c.gridx=0;
		c.gridy=2;
		bookPanel.add(groupButton, c);
		//
		JButton soloButton=new JButton("Book as an Individual");
		soloButton.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
		soloButton.setForeground(Color.WHITE);
		soloButton.setBackground(new Color(145,0,0));
		soloButton.setOpaque(true);
		soloButton.setBorderPainted(false);

		soloButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Booking booking=(Booking)objects[0];
				try {
					RoomDAO dao=new RoomDAO();
					dao.selectAllRooms().get(booking.getRoom().getCode());
					try {
						booking.getRoom().soloBook(ID, new Booking(booking.getStartTime(), booking.getEndTime(), Date.valueOf(year+"-"+month+"-"+day), null, null, null, null));
					}catch(IllegalAccessError notAllowed) {
						new ExceptionFrame("Not Allowed to Register as and Individual");
						return;
					}catch(IllegalArgumentException alreadyBooked) {
						new ExceptionFrame("A group which you are part of already booked this room!");
						return;
					}
					frame.removePanel();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}

		});
		c.gridx=0;
		c.gridy=3;
		c.insets= new Insets (0,0,40,0);
		bookPanel.add(soloButton, c);
		return bookPanel;

	}

	@Override
	public List<Notification> getNotifications(){
		return this.notifications;
	}

	@Override
	public JPanel notificationPanel(Users user, UserGUI frame) {
		return new StudentNotificationPanel(user, frame);
	}
	
	
	@Override
	public String toString() {
		return this.name+" "+this.lastName+" ID: "+this.ID;
	}
}
