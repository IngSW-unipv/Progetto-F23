package it.unipv.sfw.findme.rooms;

import it.unipv.sfw.findme.booking.Booking;
import it.unipv.sfw.findme.groups.Group;

public interface Bookable {

	public void book(Group group, Booking booking);
	
	default public void soloBook(String user, Booking booking) throws IllegalAccessError{
		throw new IllegalAccessError();
	}

}
