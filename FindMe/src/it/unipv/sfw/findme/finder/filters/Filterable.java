package it.unipv.sfw.findme.finder.filters;

import java.util.List;

import it.unipv.sfw.findme.booking.Booking;

public interface Filterable {
	
	public List<Booking> filter(List<Booking> freeRooms, FilterCheckBox CheckBox);


}