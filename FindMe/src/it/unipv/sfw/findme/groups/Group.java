package it.unipv.sfw.findme.groups;


import java.util.HashMap;
import it.unipv.sfw.findme.email.EmailTemplate;
import it.unipv.sfw.findme.exceptions.ExceptionFrame;
import it.unipv.sfw.findme.users.general_user.UsersDAO;
import it.unipv.sfw.findme.users.student.Students;

public class Group {

	private String groupID;
	private String groupAdmin;
	private final int STUDENTSLIMIT=25;
	private int studentsNumber;
	private HashMap<String, Students> studentsList;

	public Group(String ID, String admin) {
		this.studentsList=new HashMap<String, Students>();
		this.groupID=ID;
		this.groupAdmin=admin;
		this.studentsNumber++;
	}


	public void addStudent(String ID, Students user){
		this.studentsList.put(ID, user);
		this.studentsNumber++;
	}

	public void addNewStudent(String emailOrID, Group group){

		try {
			if(this.studentsNumber==this.STUDENTSLIMIT) {
				throw new IllegalArgumentException();
			}

		}catch(IllegalArgumentException e) {
			new ExceptionFrame("Group Limit Reached!");
			return;
		}


		UsersDAO daoUser=new UsersDAO();
		Students student;

		try {
			student=(Students)daoUser.checkUser(new Students(null, null, null, emailOrID, null, null));
		}
		catch(Exception e){
			new ExceptionFrame("Invalid User!");
			return;
		}

		try {
			GroupDAO daoGroup=new GroupDAO();
			daoGroup.checkPartecipant(group, student);
		}
		catch(Exception e){
			new ExceptionFrame("User already part of the Group!");
			return;
		}

		try {

			GroupNotificationDAO notifyDAO=new GroupNotificationDAO();
			notifyDAO.insertNotification(group, student);
			//EmailTemplate eTemp=new EmailTemplate(student.getEmail(), "Notification", "You have been invited by "+group.getGroupAdmin()+" to join the Group: "+group.getGroupID());
			//eTemp.start();

		} catch (Exception e) {
			new ExceptionFrame("User Already invited to the Group!");
			return;
		}	


	}

	public HashMap<String, Students> getStudentsList(){
		return this.studentsList;
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
		return STUDENTSLIMIT;
	}

	@Override
	public String toString() {
		return this.groupID+" users: "+this.studentsNumber;
	}
}
