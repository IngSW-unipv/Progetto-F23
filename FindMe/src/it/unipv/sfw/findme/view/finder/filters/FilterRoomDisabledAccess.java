package it.unipv.sfw.findme.view.finder.filters;

import java.util.ArrayList;
import java.util.List;

import it.unipv.sfw.findme.model.booking.Booking;

public class FilterRoomDisabledAccess extends FilterCheckBox {
	
	public FilterRoomDisabledAccess() {
		super();
	}
	
	@Override
	
	public List<Booking> filter(List<Booking> freeRooms, FilterCheckBox seatsCheckBox) {
		this.filteredRooms=new ArrayList<Booking>();
		for(Booking book: freeRooms) {
				if(book.getRoom().isDisabledAccess()== true) {
				filteredRooms.add(book);
			}
		}
		return this.filteredRooms;
	}


}
