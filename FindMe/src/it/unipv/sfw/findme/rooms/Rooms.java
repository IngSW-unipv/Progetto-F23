package it.unipv.sfw.findme.rooms;

import java.util.HashMap;

import it.unipv.sfw.findme.booking.Booking;


public abstract class Rooms implements Bookable, Comparable<Rooms>{
	
	protected int seats;
	protected String code;
	protected String type;
	protected boolean LIM;
	protected boolean outlets;
	protected boolean disabledAccess;
	protected boolean soloBookable;
	protected HashMap<String, Booking> availability;
	protected int maxOccupiedSeats;
	
	public Rooms(String code, String type, String seats, String LIM, String outlets, String disabledAccess) {
		super();
		this.seats=Integer.parseInt(seats);
		this.code=code;
		this.type=type;
		this.LIM=Boolean.parseBoolean(LIM);
		this.outlets=Boolean.parseBoolean(outlets);
		this.disabledAccess=Boolean.parseBoolean(disabledAccess);
		this.availability=new HashMap<String, Booking>();
		this.maxOccupiedSeats=this.seats/2;
	}

	
	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isLIM() {
		return LIM;
	}

	public void setLIM(boolean LIM) {
		this.LIM = LIM;
	}

	public boolean isOutlets() {
		return outlets;
	}
	
	public boolean isSoloBookable() {
		return soloBookable;
	}

	public void setOutlets(boolean outlets) {
		this.outlets = outlets;
	}

	public boolean isDisabledAccess() {
		return disabledAccess;
	}

	public void setDisabledAccess(boolean disabledAccess) {
		this.disabledAccess = disabledAccess;
	}
	
	public int getOccupiedSeats() {
		return this.maxOccupiedSeats;
	}

	public HashMap<String, Booking> getAvailability(){
		return this.availability;
	}

	public void setAvailability(String timeSpan, Booking availability) {
		this.availability.put(timeSpan, availability);
	}
	
	@Override
	public int compareTo(Rooms o) {
		return this.getCode().compareTo(o.getCode());
	}
	
	@Override
	public String toString() {
		return this.code;
	}
}
