package it.unipv.sfw.findme.view.registration;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import it.unipv.sfw.findme.view.login.LoginGUI;

public class VerificationSuccessFrame extends JFrame{

	public VerificationSuccessFrame(RegistrationGUI mainRegisterFrame){
		JPanel mainPanel=new JPanel();
		mainPanel.setBackground(Color.WHITE);
		
		JLabel label=new JLabel ("Verification Successful!", SwingConstants.CENTER);
		label.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		label.setForeground(new Color(145,0,0));
		mainPanel.add(label);
		JButton b=new JButton("OK");
		b.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
		b.setForeground(Color.white);
		b.setBackground(new Color(145,0,0));

		add(mainPanel);
		FinalButton finalButton=new FinalButton(this, mainRegisterFrame);
		b.addActionListener(finalButton);
		mainPanel.add(b);

		setSize(300,200);
		setTitle("Success");
		
		ImageIcon icon=new ImageIcon("Resources/Images/logo4.png");
		setIconImage(icon.getImage());

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}

class FinalButton implements ActionListener{
	private VerificationSuccessFrame f;
	private RegistrationGUI mainRegisterFrame;
	public FinalButton(VerificationSuccessFrame f, RegistrationGUI mainRegisterFrame) {
		this.f=f;
		this.mainRegisterFrame=mainRegisterFrame;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		LoginGUI back=new LoginGUI();
		this.mainRegisterFrame.dispose();
		this.f.dispose();

	}
}