package it.unipv.sfw.findme.rooms;

import java.sql.Date;

import it.unipv.sfw.findme.booking.Booking;
import it.unipv.sfw.findme.booking.RoomsBookingDAO;
import it.unipv.sfw.findme.exceptions.ExceptionFrame;
import it.unipv.sfw.findme.groups.Group;


public class SmallRooms extends Rooms{

	private final int minimumGroupSize=3;

	public SmallRooms(String code, String type, String seats, String LIM, String outlets, String disabledAccess) {
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
		
		String bookingID=booking.getDate()+"-"+booking.getStartTime().split(":")[0]+"-"+booking.getEndTime().split(":")[0]+"-"+this.code+"-"+group.getGroupAdmin();		

		RoomsBookingDAO bookingDAO=new RoomsBookingDAO();
		bookingDAO.insert(new Booking(booking.getStartTime(), booking.getEndTime(), booking.getDate(), this, bookingID, group.getGroupID(), "true"));
		
		new ExceptionFrame("Booking Succesfull!");

	}


}
