package it.unipv.sfw.findme.model.exceptions;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ExceptionFrame {
	
	public ExceptionFrame(String message) {
		ExceptionHandler(message);
	}

	public void ExceptionHandler(String message) {
		JFrame mainFrame=new JFrame();
		JPanel mainPanel=new JPanel();
		mainPanel.setBackground(Color.white);
		JLabel label=new JLabel(message);
		label.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		label.setForeground(new Color(145,0,0));
		//
		JButton okButton=new JButton("OK");
		okButton.setFont(new Font("Comic Sans MS", Font.BOLD,10));
		okButton.setForeground(Color.white);
		okButton.setBackground(new Color(145,0,0));
		okButton.setOpaque(true);
		okButton.setBorderPainted(false);
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.dispose();
			}
		});
		mainPanel.add(label);
		mainPanel.add(okButton);
		mainFrame.add(mainPanel);
		
		mainFrame.setSize(400,200);
		mainFrame.setTitle("Message");
		ImageIcon icon=new ImageIcon("Resources/Images/logo4.png");
		mainFrame.setIconImage(icon.getImage());

		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.setVisible(true);
	}

} 
