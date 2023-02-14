package it.unipv.sfw.findme.model.groups;


import java.util.HashMap;
import it.unipv.sfw.findme.model.email.EmailTemplate;
import it.unipv.sfw.findme.model.exceptions.ExceptionFrame;
import it.unipv.sfw.findme.model.groups.dao.GroupDAO;
import it.unipv.sfw.findme.model.notifications.dao.JoinGroupNotificationDAO;
import it.unipv.sfw.findme.model.users.general_user.UsersDAO;
import it.unipv.sfw.findme.model.users.student.Student;

public class Group {

	private String groupID;
	private String groupAdmin;
	private final int STUDENTSLIMIT=25;
	private int studentsNumber;
	private HashMap<String, Student> studentsList;

	public Group(String ID, String admin) {
		this.studentsList=new HashMap<String, Student>();
		this.groupID=ID;
		this.groupAdmin=admin;
		this.studentsNumber++;
	}


	public void addStudent(String ID, Student user){
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
		Student student;

		try {
			student=(Student)daoUser.checkUser(new Student(null, null, null, emailOrID, null, null));
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

			JoinGroupNotificationDAO notifyDAO=new JoinGroupNotificationDAO();
			notifyDAO.insertNotification(group, student);
			EmailTemplate eTemp=new EmailTemplate(student.getEmail(), "Notification", "You have been invited by "+group.getGroupAdmin()+" to join the Group: "+group.getGroupID());
			eTemp.start();

		} catch (Exception e) {
			new ExceptionFrame("User Already invited to the Group!");
			return;
		}	


	}

	public HashMap<String, Student> getStudentsList(){
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
