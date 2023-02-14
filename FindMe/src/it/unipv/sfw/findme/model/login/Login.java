package it.unipv.sfw.findme.model.login;


import it.unipv.sfw.findme.model.users.general_user.UsersDAO;
import it.unipv.sfw.findme.model.users.student.Student;
import it.unipv.sfw.findme.view.login.LoginGUI;

public class Login {
	String emailOrID;
	String passwordString;
	LoginGUI frame;

	public Login(String emailOrID, char[] password, LoginGUI frame) {
			this.emailOrID=emailOrID;
			this.passwordString=new String(password);
			this.frame=frame;

	}	
	
	public void login() {
		UsersDAO dao=new UsersDAO();
		dao.selectUser(new Student(null, null, null, emailOrID, passwordString, null));
		dao.getUser().GUI(frame);
	}
	
	
	public void fieldCheck() throws IllegalArgumentException{
		if(emailOrID.isEmpty()==true || passwordString.isEmpty()==true) {
			throw new IllegalArgumentException();
		}
	}
	
}
