package it.unipv.sfw.findme.view.finder.filters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import it.unipv.sfw.findme.model.booking.Booking;
import it.unipv.sfw.findme.model.rooms.Rooms;
import it.unipv.sfw.findme.model.rooms.dao.RoomDAO;


public class FilterRoomType extends FilterCheckBox{
	
	public FilterRoomType() {
		super();
	}
	
	
	@Override
	public List<Booking>  filter(List<Booking> freeRooms, FilterCheckBox filterLab){
		
		this.filteredRooms=new ArrayList<Booking>();
		String[] temp;
		HashMap<String, Rooms> uniRooms;
		try {
			RoomDAO dao=new RoomDAO();
			uniRooms = dao.selectAllRooms();
		for(Booking book: freeRooms) {
			String code=book.getRoom().getCode();
			Rooms room=uniRooms.get(code);
			if (room.getType().equals(filterLab.getIdentifier())) {
				this.filteredRooms.add(book);
			}
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.filteredRooms;
	}

}
