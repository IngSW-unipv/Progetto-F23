package it.unipv.sfw.findme.groups;


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
		
		try {
			
			dbg.check(emailOrID, group);
		}
		catch(Exception e) {

			return;
			// da mettere parte della expection 
		}
		
	}
	
	
	

}
