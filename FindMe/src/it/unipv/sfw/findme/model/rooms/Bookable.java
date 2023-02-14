package it.unipv.sfw.findme.model.rooms;


import it.unipv.sfw.findme.model.booking.Booking;
import it.unipv.sfw.findme.model.groups.Group;

public interface Bookable {

	public void book(Group group, Booking booking);
	
	default public void soloBook(String user, Booking booking) throws IllegalAccessError{
		throw new IllegalAccessError();
	}

}
