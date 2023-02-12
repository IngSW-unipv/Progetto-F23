package it.unipv.sfw.findme.groups;

import java.awt.*;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import it.unipv.sfw.findme.exceptions.ExceptionFrame;
import it.unipv.sfw.findme.users.general_user.UserGUI;
import it.unipv.sfw.findme.users.general_user.Users;
import it.unipv.sfw.findme.users.student.Students;

public class NewGroupNameFrame  extends JFrame{

	public NewGroupNameFrame(Users user, UserGUI studentsGUI) {
		setSize(400,200);
		setTitle("Insert Group Name");

		setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();

		JLabel insertName=new JLabel("Insert Group Name");
		insertName.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		insertName.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=0;
		c.insets= new Insets(0,0,10,0);
		add(insertName, c);

		JTextField nameField=new JTextField(10);
		ConfirmListener confirm=new ConfirmListener(user, nameField, this, studentsGUI);
		nameField.addActionListener(confirm);
		c.gridx=0;
		c.gridy=1;
		add(nameField, c);

		JButton okButton=new JButton("OK");
		okButton.addActionListener(confirm);
		okButton.setFont(new Font("Comic Sans MS", Font.BOLD,10));
		okButton.setBackground(new Color(145,0,0));
		okButton.setForeground(Color.white);
		okButton.setOpaque(true);
		okButton.setBorderPainted(false);
		c.gridx=0;
		c.gridy=2;
		c.insets= new Insets(10,0,0,0);
		add(okButton, c);
		ImageIcon icon=new ImageIcon("Resources/Images/logo4.png");
		setIconImage(icon.getImage());

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);

	}
}


class ConfirmListener implements ActionListener{
	private Students user;
	private JTextField name;
	private JFrame frame;
	private UserGUI studentsGUI;

	public ConfirmListener(Users user, JTextField name, JFrame frame, UserGUI studentsGUI) {
		this.user=(Students)user;
		this.name=name;
		this.frame=frame;
		this.studentsGUI=studentsGUI;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			checkName(this.name.getText());
		}catch(IllegalArgumentException ex) {
			new ExceptionFrame("Field can't be empty");
			return;
		}

		this.user.createGroup(this.name.getText());
		this.studentsGUI.removePanel();
		this.studentsGUI.addSecondPanel(new GroupsPanel(this.user, this.studentsGUI));
		this.studentsGUI.revalidate();
		this.studentsGUI.repaint();
		frame.dispose();
	}

	public void checkName(String nameGroup){
		if(nameGroup.isEmpty()==true) {
			throw new IllegalArgumentException();
		}
	}

}
