package it.unipv.sfw.findme.view.registration;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import it.unipv.sfw.findme.controller.registration.VerificationActionListener;
import it.unipv.sfw.findme.model.exceptions.ExceptionFrame;
import it.unipv.sfw.findme.model.register.Registration;
import it.unipv.sfw.findme.view.login.LoginGUI;


public class VerificationFrame{
	public VerificationFrame(int number, Registration registration, RegistrationGUI frame) {
		JFrame mainFrame=new JFrame();
		JPanel mainPanel=new JPanel();


		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBackground(new Color(0,0,0,0));
		
		JLabel info=new JLabel("Check your emails for the verification code", SwingConstants.CENTER);
		info.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		info.setForeground(new Color(145,0,0));
		mainPanel.add(info, BorderLayout.NORTH);

		JPanel secondPanel=new JPanel();
		secondPanel.setBackground(new Color(0,0,0,0));
		
		//
		mainPanel.add(secondPanel, BorderLayout.CENTER);
		
		JLabel l=new JLabel("Insert Code:");
		l.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		l.setForeground(new Color(145,0,0));
		//
		JTextField text=new JTextField(3);
		//
		JButton okButton=new JButton("OK");
		okButton.setFont(new Font("Comic Sans MS", Font.PLAIN,10));
		okButton.setForeground(Color.white);
		okButton.setBackground(new Color(145,0,0));
		//
		secondPanel.add(l);
		secondPanel.add(text);
		VerificationActionListener verifyListener=new VerificationActionListener(text, number, frame, registration);
		okButton.addActionListener(verifyListener);
		secondPanel.add(okButton);


		mainFrame.add(mainPanel);
		mainFrame.setBackground(Color.WHITE);

		mainFrame.setSize(500,300);
		mainFrame.setTitle("Verification");
		
		ImageIcon icon=new ImageIcon("Resources/Images/logo4.png");
		mainFrame.setIconImage(icon.getImage());

		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.setVisible(true);
	}
}

