package it.unipv.sfw.findme.view.users.general_user;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import it.unipv.sfw.findme.model.users.general_user.Users;


public class NotificationRightPanel extends JPanel{
	
	
	public NotificationRightPanel(Users user, UserGUI main) {
		setLayout (new GridBagLayout());
		setBackground(new Color(145,0,0));
		GridBagConstraints c=new GridBagConstraints();

		JButton notificationsButton=user.checkNotifications();
		notificationsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				user.getNotifications().clear();
				main.removePanel();
				main.addSecondPanel(user.notificationPanel(user, main));
				main.revalidate();
				main.repaint();
				
			}
			
		});
		c.gridx=0;
		c.gridy=0;
		add(notificationsButton, c);

		ImageIcon refreshIcon=new ImageIcon("Resources/Images/refresh-icon.png");
		Image image2 = refreshIcon.getImage();
		Image newimg2 = image2.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);  
		refreshIcon = new ImageIcon(newimg2);

		
		NotificationRightPanel temp=this;
		JButton refreshButton=new JButton(refreshIcon);
		refreshButton.setBackground(Color.WHITE);
		refreshButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				user.getNotifications().clear();
				main.removeHeadPanel(new UsersHeadPanel(user.getName(), user.getLastName(), user.getEmail(), user, main));
			}

		});
		c.gridx=0;
		c.gridy=1;
		add(refreshButton, c);
		
	}
	
	

}
