package it.unipv.sfw.findme.rooms;

public abstract class Rooms {
	
	protected int seats;
	protected String code;
	protected String type;
	protected boolean LIM;
	protected boolean outlets;
	protected boolean disabledAccess;
	
	public Rooms(int seats, String code, String type, boolean lIM, boolean outlets, boolean disabledAccess) {
		super();
		this.seats = seats;
		this.code = code;
		this.type = type;
		LIM = lIM;
		this.outlets = outlets;
		this.disabledAccess = disabledAccess;
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

	public void setLIM(boolean lIM) {
		LIM = lIM;
	}

	public boolean isOutlets() {
		return outlets;
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
	
	

}
