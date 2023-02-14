package it.unipv.sfw.findme.view.finder.filters;

import java.util.List;
import it.unipv.sfw.findme.model.booking.Booking;


public interface Filterable {
	
	public List<Booking> filter(List<Booking> freeRooms, FilterCheckBox CheckBox);


}