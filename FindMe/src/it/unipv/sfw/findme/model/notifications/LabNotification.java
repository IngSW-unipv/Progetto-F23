package it.unipv.sfw.findme.model.notifications;

import java.sql.Date;

import it.unipv.sfw.findme.model.notifications.dao.LabNotificationDAO;

public class LabNotification implements Notification{
	
	private String bookingID;
	private String groupID;
	private String room;
	private String startTime;
	private String endTime;
	private String reason;
	private Date date;
	private boolean locked;

	

	public LabNotification(String bookingID, String groupID, String room, Date date ,String startTime, String endTime, String reason, boolean locked) {
		super();
		this.bookingID=bookingID;
		this.groupID = groupID;
		this.room = room;
		this.startTime = startTime;
		this.endTime = endTime;
		this.reason = reason;
		this.date=date;
		this.locked=locked;
	}

	@Override
	public void accept() {
		LabNotificationDAO dao=new LabNotificationDAO();
		dao.update(this);
		
	}

	public String getGroupID() {
		return groupID;
	}

	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getBookingID() {
		return bookingID;
	}

	public void setBookingID(String bookingID) {
		this.bookingID = bookingID;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	@Override
	public String toString() {
		
		return "The Group: "+this.groupID+" requested to use the lab "+this.room+" the day "+this.date.toString()+" from "+this.startTime+" to "+this.endTime;
	}
	
}