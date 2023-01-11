package it.unipv.sfw.findme.groups;

public class Group {
	
	
	private String groupID;
	private String groupAdmin;
	private final int studentsLimit=25;
	private int studentsNumber;

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
	
	
	

}
