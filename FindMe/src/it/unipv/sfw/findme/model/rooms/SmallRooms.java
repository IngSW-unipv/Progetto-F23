package it.unipv.sfw.findme.model.rooms;

import it.unipv.sfw.findme.model.booking.Booking;
import it.unipv.sfw.findme.model.booking.dao.RoomsBookingDAO;
import it.unipv.sfw.findme.model.exceptions.ExceptionFrame;
import it.unipv.sfw.findme.model.groups.Group;


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
