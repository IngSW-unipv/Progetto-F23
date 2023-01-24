package it.unipv.sfw.findme.groups;


import it.unipv.sfw.findme.email.EmailTemplate;
import it.unipv.sfw.findme.exceptions.ExceptionFrame;
public class Group {
	
	
	private String groupID;
	private String groupAdmin;
	private final int studentsLimit=25;
	private int studentsNumber;
	GroupDAO dbg;

	public Group(String ID, String admin) {
		this.groupID=ID;
		this.groupAdmin=admin;
		this.studentsNumber++;
	}

	public String getGroupID() {
		return groupID;
	}

	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}

	public String getGroupAdmin() {
		return groupAdmin;
	}

	public void setGroupAdmin(String groupAdmin) {
		this.groupAdmin = groupAdmin;
	}

	public int getStudentsNumber() {
		return studentsNumber;
	}

	public void setStudentsNumber(int studentsNumber) {
		this.studentsNumber = studentsNumber;
	}

	public int getStudentsLimit() {
		return studentsLimit;
	}
	
	public void addNewStudent(String emailOrID, Group group) {
		
		String email= "";
		try {
			
			email=dbg.check(emailOrID,group);
			
		}
		catch(Exception e) {

			new ExceptionFrame("\u274C User Already invited to the Group!");
			e.printStackTrace();
			return;
		}
		
		try {
		EmailTemplate eTemp=new EmailTemplate(email, "Notification", "You have been invited by "+group.getGroupAdmin()+" to join the Group: "+group.getGroupID());
		eTemp.start();
		}
		
		catch(Exception ex) {
			
			
		}
		
	}
	

	
	
	

}
