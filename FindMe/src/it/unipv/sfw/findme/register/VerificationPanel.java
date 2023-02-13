package it.unipv.sfw.findme.register;

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
import it.unipv.sfw.findme.exceptions.ExceptionFrame;
import it.unipv.sfw.findme.login.LoginGUI;


public class VerificationPanel{
	public VerificationPanel(int number, Registration registration, RegisterGUI frame) {
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
		JTextField t=new JTextField(3);
		//
		JButton b=new JButton("OK");
		b.setFont(new Font("Comic Sans MS", Font.PLAIN,10));
		b.setForeground(Color.white);
		b.setBackground(new Color(145,0,0));
		//
		secondPanel.add(l);
		secondPanel.add(t);
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(t.getText().equals(Integer.toString(number))) {
				registration.register();
				VerificationSuccess v=new VerificationSuccess(frame);
				}
				else {
					new ExceptionFrame("Wronge Code!");
				}
				
			}
			
		});
		secondPanel.add(b);


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

class VerificationSuccess extends JFrame{

	public VerificationSuccess(RegisterGUI mainRegisterFrame){
		JPanel p=new JPanel();
		p.setBackground(Color.WHITE);
		
		JLabel l=new JLabel ("Verification Successful! \u2714", SwingConstants.CENTER);
		l.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		l.setForeground(new Color(145,0,0));
		p.add(l);
		JButton b=new JButton("OK");
		b.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
		b.setForeground(Color.white);
		b.setBackground(new Color(145,0,0));

		add(p);
		FinalButton fb=new FinalButton(this, mainRegisterFrame);
		b.addActionListener(fb);
		p.add(b);

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
	private VerificationSuccess f;
	private RegisterGUI mainRegisterFrame;
	public FinalButton(VerificationSuccess f, RegisterGUI mainRegisterFrame) {
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