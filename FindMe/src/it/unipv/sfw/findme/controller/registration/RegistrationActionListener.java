package it.unipv.sfw.findme.controller.registration;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import it.unipv.sfw.findme.model.email.GenerateRandom;
import it.unipv.sfw.findme.model.exceptions.ExceptionFrame;
import it.unipv.sfw.findme.model.register.Registration;
import it.unipv.sfw.findme.view.registration.RegistrationGUI;
import it.unipv.sfw.findme.view.registration.VerificationFrame;

public class RegistrationActionListener implements ActionListener{
	
	private String name;
	private String lastName;
	private String idNumber;
	private String email;
	private String emailTermination;
	private char[] password;
	private char[] confirmPassword;
	private String userType;
	private RegistrationGUI frame;

	
	public RegistrationActionListener(String name, String lastName, String idNumber, String email, String emailTermination, char[] password, char[] confirmPassword, String userType, RegistrationGUI frame) {
		this.name=name;
		this.lastName=lastName;
		this.idNumber=idNumber;
		this.email=email;
		this.emailTermination=emailTermination;
		this.password=password;
		this.confirmPassword=confirmPassword;
		this.userType=userType;
		this.frame=frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Registration registration=new Registration(name, lastName, idNumber, email, emailTermination, password, confirmPassword, userType);
		
		
		try {
			registration.emailCheck();
		} catch (Exception e1) {
			new ExceptionFrame("Email not Valid");
			return;
		}
		
		try {
			registration.passwordCheck();
		} catch (Exception e1) {
			new ExceptionFrame("Password not Valid!");
			return;
		}

		try {
			registration.checkDuplicate();
		} catch (Exception e1) {
			new ExceptionFrame("User already registered!");
			return;
		}
		
		try {
			registration.TypeCheck();
		} catch (Exception e1) {
			new ExceptionFrame("User Type not Valid!");
			return;
		}
		
		int number=new GenerateRandom().getRandom();
		
		registration.emailVerification(number);
		
		VerificationFrame v=new VerificationFrame(number, registration, frame);
		
	}

}
