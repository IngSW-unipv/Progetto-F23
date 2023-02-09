package it.unipv.sfw.findme.users.student;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import it.unipv.sfw.findme.database.DBConnection;
import it.unipv.sfw.findme.exceptions.ExceptionFrame;
import it.unipv.sfw.findme.users.general_user.UserGUI;
import it.unipv.sfw.findme.users.general_user.Users;


public class StudentNotificationPanel extends JPanel{

	public StudentNotificationPanel(Users user, UserGUI frame) {

		setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();

		setBackground(Color.white);
		//	setBackground(new Color(0,0,0,0));
		try {

			Connection conn=DBConnection.connect();
			String query="select * from group_notifications where Receiver=?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, Integer.parseInt(user.getID()));
			ResultSet result=preparedStmt.executeQuery();

			while(result.next()) {
				user.loadNotifications(new JoinGroupNotification(result.getString(1), result.getString(2), result.getString(3)));
			}

			conn.close();

		} catch (Exception e) {
		}

		c.anchor = GridBagConstraints.CENTER;
		JLabel notLabel= new JLabel("Check your notifications:");
		notLabel.setFont(new Font("Comic Sans MS", Font.BOLD,25));
		notLabel.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=0;
		c.insets= new Insets (0,0,100,0);
		add(notLabel,c);
		//
		c.anchor = GridBagConstraints.CENTER;
		JList<JoinGroupNotification> list=new JList(user.getNotifications().toArray());
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(320, 200));
		c.gridx=0;
		c.gridy=1;
		c.insets= new Insets (0,0,100,0);
		add(listScroller,c);
		//
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		JButton accept=new JButton("Accept");
		accept.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
		accept.setForeground(Color.WHITE);
		accept.setBackground(new Color(145,0,0));
		accept.setOpaque(true);
		accept.setBorderPainted(false);
		c.gridx=1;
		c.gridy=1;
		c.insets= new Insets (0,20,0,0);
		accept.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JoinGroupNotification notification=list.getSelectedValue();
				try {
					notification.accept();
				}catch(IllegalArgumentException ex) {
					new ExceptionFrame("Group Limit Reached!");
					return;
				}
				try {
					GroupNotificationDAO notificationDAO=new GroupNotificationDAO();
					notificationDAO.deleteNotification(notification);
				} catch (Exception ea) {
					new ExceptionFrame("\u274C No Invite Selected!");
					return;
				}

				new AcceptRejectFrame("Invite Accepted!", user, frame);
			}

		});
		add(accept,c);

		c.anchor = GridBagConstraints.FIRST_LINE_END;
		JButton reject=new JButton("Reject");
		reject.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
		reject.setForeground(Color.WHITE);
		reject.setBackground(new Color(145,0,0));
		reject.setOpaque(true);
		reject.setBorderPainted(false);
		c.gridx=1;
		c.gridy=1;
		c.insets= new Insets (50,20,0,0);

		reject.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JoinGroupNotification notification=list.getSelectedValue();
				try {
					GroupNotificationDAO notificationDAO=new GroupNotificationDAO();
					notificationDAO.deleteNotification(notification);

				} catch (Exception ea) {
					new ExceptionFrame("\u274C No Invite Selected!");
					return;
				}
				new AcceptRejectFrame("Invite Rejected!", user, frame);			
			}

		});

		//setBackground(new Color(0,0,0,0));


		add(reject,c);
	}

}