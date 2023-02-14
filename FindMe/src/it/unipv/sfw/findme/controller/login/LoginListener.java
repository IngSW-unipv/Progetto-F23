package it.unipv.sfw.findme.controller.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import it.unipv.sfw.findme.model.exceptions.ExceptionFrame;
import it.unipv.sfw.findme.model.login.Login;
import it.unipv.sfw.findme.view.login.LoginGUI;

public class LoginListener implements ActionListener{

	private LoginGUI frame;
	private JTextField email;
	private JPasswordField password;

	public LoginListener(JTextField email, JPasswordField password, LoginGUI frame) {
		this.email=email;
		this.password=password;
		this.frame=frame;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Login logger=new Login(this.email.getText(), this.password.getPassword(), this.frame);
		
		try {
			logger.fieldCheck();
		}catch(IllegalArgumentException ex) {
			new ExceptionFrame("Fields can't be empty");
			return;
		}
		
		logger.login();
		  
		
	}

}
