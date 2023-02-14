package it.unipv.sfw.findme.view.users.professor;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import it.unipv.sfw.findme.controller.users.professor.RoomChooserListener;
import it.unipv.sfw.findme.model.notifications.ProfessorNotification;
import it.unipv.sfw.findme.model.rooms.Rooms;
import it.unipv.sfw.findme.model.rooms.dao.RoomDAO;
import it.unipv.sfw.findme.model.users.general_user.Users;
import it.unipv.sfw.findme.view.users.general_user.UserGUI;


public class RoomChooserFrame extends JFrame {

	public RoomChooserFrame(ProfessorNotification notification, Users user, UserGUI frame) {
		JPanel p=new JPanel();
		try {

			p.setLayout (new GridBagLayout());
			p.setBackground(Color.white);
			GridBagConstraints c=new GridBagConstraints();
			RoomDAO dao=new RoomDAO();

			HashMap<String, Rooms> allRooms=dao.selectAllRooms();
			ArrayList<Rooms> roomsList=new ArrayList<Rooms>();
			for(HashMap.Entry<String, Rooms> entry : allRooms.entrySet()) {
				roomsList.add(entry.getValue());
			}
			Collections.sort(roomsList);
			JComboBox<String> roomsBox=new JComboBox(roomsList.toArray());
			roomsBox.setFont(new Font("Comic Sans MS", Font.BOLD,15));
			roomsBox.setForeground(new Color(145,0,0));
			roomsBox.setBackground(Color.WHITE);
			roomsBox.setFocusable(false);

			JLabel label = new JLabel("Which Room will the other professor have to use?");
			label.setForeground(new Color(145,0,0));
			label.setFont(new Font("Comic Sans MS", Font.BOLD,13));
			c.gridx=0;
			c.gridy=0;
			p.add(label, c);
			//
			c.gridx=1;
			c.gridy=0;
			c.insets= new Insets(0,10,0,0);
			p.add(roomsBox, c);
			//
			JButton ok=new JButton("OK");
			ok.setFont(new Font("Comic Sans MS", Font.BOLD,13));
			ok.setForeground(Color.white);
			ok.setBackground(new Color(145,0,0));
			ok.setOpaque(true);
			ok.setBorderPainted(false);
			RoomChooserListener roomListener=new RoomChooserListener(roomsBox, notification, user, frame, null);
			ok.addActionListener(roomListener);
			c.gridx=0;
			c.gridy=1;
			p.add(ok, c);

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		add(p);
		setSize(500,200);
		setTitle("Choose Room");
		ImageIcon icon=new ImageIcon("Resources/Images/logo4.png");
		setIconImage(icon.getImage());

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}
