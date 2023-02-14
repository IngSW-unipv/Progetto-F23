package it.unipv.sfw.findme.controller.users.student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JList;
import it.unipv.sfw.findme.model.exceptions.ExceptionFrame;
import it.unipv.sfw.findme.model.notifications.JoinGroupNotification;
import it.unipv.sfw.findme.model.notifications.dao.JoinGroupNotificationDAO;
import it.unipv.sfw.findme.model.users.general_user.Users;
import it.unipv.sfw.findme.view.users.general_user.UserGUI;
import it.unipv.sfw.findme.view.users.general_user.UsersHeadPanel;

public class AcceptNotificationListener implements ActionListener{
	
	private JList<JoinGroupNotification> list;
	private Users user;
	private UserGUI frame;

	public AcceptNotificationListener(JList<JoinGroupNotification> list, Users user, UserGUI frame) {
		this.list = list;
		this.user = user;
		this.frame = frame;
	}

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
			JoinGroupNotificationDAO notificationDAO=new JoinGroupNotificationDAO();
			notificationDAO.deleteNotification(notification);
		} catch (Exception ea) {
			new ExceptionFrame("No Invite Selected!");
			return;
		}
		frame.removePanel();
		user.getNotifications().clear();
    	frame.addSecondPanel(user.notificationPanel(user, frame));
    	UserGUI tempFrame=frame;
    	tempFrame.removeHeadPanel(new UsersHeadPanel(user.getName(), user.getLastName(), user.getEmail(), user, frame));
    	tempFrame.revalidate();
    	tempFrame.repaint();
    	new ExceptionFrame("Invite Accepted!");
		
	}

}
