package it.unipv.sfw.findme.finder.filters;

import java.util.List;

public interface Filterable {
	
	public List<Booking> filter(List<Booking> freeRooms, FilterCheckBox CheckBox);


}