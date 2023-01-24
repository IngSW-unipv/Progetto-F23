package it.unipv.sfw.findme.finder.filters;

import java.util.ArrayList;
import java.util.List;

import it.unipv.sfw.findme.booking.Booking;


public class FilterSeatsNumber extends FilterCheckBox{
	
	public FilterSeatsNumber() {
		super();
	}
	
	@Override
	
	public List<Booking> filter(List<Booking> freeRooms, FilterCheckBox seatsCheckBox) {
		this.filteredRooms=new ArrayList<Booking>();
		for(Booking book: freeRooms) {
			if(book.getRoom().getSeats()==seatsCheckBox.getSeats()) {
				filteredRooms.add(book);
			}
		}
		return this.filteredRooms;
	}

}
