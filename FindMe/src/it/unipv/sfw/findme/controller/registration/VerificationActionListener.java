package it.unipv.sfw.findme.controller.registration;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

import it.unipv.sfw.findme.model.exceptions.ExceptionFrame;
import it.unipv.sfw.findme.model.register.Registration;
import it.unipv.sfw.findme.view.registration.RegistrationGUI;
import it.unipv.sfw.findme.view.registration.VerificationSuccessFrame;


public class VerificationActionListener implements ActionListener{
	
	private JTextField text;
	private int code;
	private RegistrationGUI mainRegisterFrame;
	private Registration registration;
	
	public VerificationActionListener(JTextField text, int code, RegistrationGUI mainRegisterFrame, Registration registration) {
		this.text=text;
		this.code=code;
		this.mainRegisterFrame=mainRegisterFrame;
		this.registration=registration;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(text.getText().equals(Integer.toString(code))) {
			registration.register();
			VerificationSuccessFrame v=new VerificationSuccessFrame(mainRegisterFrame);
			}
			else {
				new ExceptionFrame("Wronge Code!");
			}
			
		}
		
	}

