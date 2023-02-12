package it.unipv.sfw.findme.rooms;

import java.sql.Date;
import java.time.LocalDate;
import javax.swing.JPanel;

import it.unipv.sfw.findme.booking.Booking;
import it.unipv.sfw.findme.exceptions.ExceptionFrame;
import it.unipv.sfw.findme.groups.Group;
import it.unipv.sfw.findme.notifications.LabNotification;


public class Lab extends Rooms{


	private final int minimumGroupSize=3;

	public Lab(String code, String type, String seats, String LIM, String outlets, String disabledAccess) {
		super(code, type, seats, LIM, outlets, disabledAccess);
		this.soloBookable=false;
	}


	@Override
	public void book(Group group, Booking booking) {
		
		
		try {
			if(group.getStudentsNumber()<minimumGroupSize) {
				throw new IllegalArgumentException();
			}
		}catch(IllegalArgumentException e) {
			new ExceptionFrame("Required at least 3 Students to book this Room!");
			return;
		}		
		
		
		LabNotification notification=new LabNotification(booking.getBookingID(), group.getGroupID(), this.code, booking.getDate(), booking.getStartTime(), booking.getEndTime(), null, false);
		ReasonFrame reasonFrame=new ReasonFrame(notification);		
	}

}
