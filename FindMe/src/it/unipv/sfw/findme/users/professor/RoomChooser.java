package it.unipv.sfw.findme.users.professor;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import it.unipv.sfw.findme.database.DBConnection;
import it.unipv.sfw.findme.exceptions.ExceptionFrame;
import it.unipv.sfw.findme.notifications.AcceptRejectFrame;
import it.unipv.sfw.findme.notifications.ProfNotificationDAO;
import it.unipv.sfw.findme.notifications.ProfSwapNotificationDAO;
import it.unipv.sfw.findme.notifications.ProfessorNotification;
import it.unipv.sfw.findme.notifications.ProfessorSwapDraft;
import it.unipv.sfw.findme.rooms.RoomDAO;
import it.unipv.sfw.findme.rooms.Rooms;
import it.unipv.sfw.findme.users.general_user.UserGUI;
import it.unipv.sfw.findme.users.general_user.Users;


public class RoomChooser extends JFrame {
	
	private String selectedRoom;

	public RoomChooser(ProfessorNotification notification, Users user, UserGUI frame) {
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
			ok.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					selectedRoom=(String)roomsBox.getSelectedItem().toString();
					if(notification.getNewDate()!=null) {
						try {
							Connection conn=DBConnection.connect();


							LocalDate myDate =notification.getDate().toLocalDate();
							DayOfWeek dayOfWeek=myDate.getDayOfWeek();
							String query="select Schedule_ID from schedule where Start_Time=? and End_Time=? and Room=? and Day_Of_Week=?";
							PreparedStatement preparedStmt = conn.prepareStatement(query);
							preparedStmt.setString(1, notification.getNewTo());
							preparedStmt.setString(2, notification.getNewFrom());
							preparedStmt.setString(3, selectedRoom);
							preparedStmt.setString(4, dayOfWeek.toString());
							ResultSet result=preparedStmt.executeQuery();
							result.next();
							String newSchedule=result.getString(1);

							ProfSwapNotificationDAO swapDao=new ProfSwapNotificationDAO();
							ProfessorSwapDraft swap=new ProfessorSwapDraft(user.getID(), notification.getSender(), notification.getDate(), notification.getNewDate(),  notification.getScheduleID(), newSchedule, "true");
							swapDao.insertNotification(swap);
							ProfNotificationDAO dao=new ProfNotificationDAO();
							dao.deleteWithNewDate(notification);

							conn.close();

						} catch (Exception ea) {
							new ExceptionFrame("\u274C Error!");
							return;
						}
						notification.accept();
						new AcceptRejectFrame("Swap Request Accepted!", user, frame);
					}
					else {
						new ExceptionFrame("You need to send Swap-Draft before you can accept this request");

					}
					dispose();
				}
			});
			c.gridx=0;
			c.gridy=1;
			p.add(ok, c);

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		add(p);
		setSize(500,200);
		setTitle("Choose Room");
		ImageIcon icon=new ImageIcon("Immagini/logo4.png");
		setIconImage(icon.getImage());

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public String getRoom() {
		return this.selectedRoom;
	}

}
