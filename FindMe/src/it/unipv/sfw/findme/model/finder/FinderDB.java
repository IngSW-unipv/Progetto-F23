package it.unipv.sfw.findme.model.finder;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import it.unipv.sfw.findme.model.booking.Booking;
import it.unipv.sfw.findme.model.database.DBConnection;
import it.unipv.sfw.findme.model.datemanager.DateHolderSingletone;
import it.unipv.sfw.findme.model.datemanager.MonthsSingleton;
import it.unipv.sfw.findme.model.datemanager.MyTimer;
import it.unipv.sfw.findme.model.rooms.Rooms;
import it.unipv.sfw.findme.model.rooms.dao.RoomDAO;

public class FinderDB {
	private HashMap<String, Rooms> allRooms;
	private List<Booking> free;


	public FinderDB(int year, String month, int day, String start, String end) throws Exception {
		this.free= new ArrayList<Booking>();

		int monthNumber=MonthsSingleton.getMonths().get(month);
		DateHolderSingletone.DateHolder(day, monthNumber, year);
		LocalDate myDate = LocalDate.of(year, monthNumber, day);
		DayOfWeek dayOfWeek=myDate.getDayOfWeek();


		semesterChceck(myDate, dayOfWeek.toString());

		try {
			RoomDAO dao=new RoomDAO();
			this.allRooms=dao.selectAllRooms();

		} catch (Exception e) {
			e.printStackTrace();
		}


		clearAvailability(this.allRooms);//resetto lo stato di occupato
		String[] startHour=start.split(":");
		String[] endHour=end.split(":");
		int startNumber=Integer.parseInt(startHour[0]);
		int endNumber=Integer.parseInt(endHour[0]);

		int loopCount=endNumber-startNumber;
		try {
			Properties config= new Properties();
			FileInputStream fis=new FileInputStream("Resources/Property/config.properties");
			config.load(fis);

			for(int i=0; i<loopCount; i++) {
				if(startNumber+i!=13) {
					start=Integer.toString(startNumber+i)+":00";
					end=Integer.toString(startNumber+i+1)+":00";
					Connection conn=DBConnection.connect();

					String query="(select Room, Start_Time, End_Time from schedule where Start_Time=? and End_Time=? and Day_Of_Week=?)\r\n"
							+ "union\r\n"
							+ "(select Room, Start_Time, End_Time from rooms_booking where Start_Time=? and End_Time=? and Locked='true' and rooms_booking.Date=?)\r\n"
							+ "union\r\n"
							+ "(select Room, Start_Time, End_Time from lab_booking where Start_Time=? and End_Time=? and Locked='true' and lab_booking.Date=?)\r\n"
							+ "union\r\n"
							+ "(select Room, Start_Time, End_Time from solo_booking where Start_Time=? and End_Time=? and Locked='true' and solo_booking.Date=?)";
					PreparedStatement preparedStmt = conn.prepareStatement(query);

					preparedStmt.setString(1, start);
					preparedStmt.setString(2, end);
					preparedStmt.setDate(3, Date.valueOf(myDate));
					preparedStmt.setString(4, start);
					preparedStmt.setString(5, end);
					preparedStmt.setDate(6, Date.valueOf(myDate));
					preparedStmt.setString(7, start);
					preparedStmt.setString(8, end);
					preparedStmt.setDate(9, Date.valueOf(myDate));
					preparedStmt.setString(10, start);
					preparedStmt.setString(11, end);
					preparedStmt.setDate(12, Date.valueOf(myDate));
					ResultSet result=preparedStmt.executeQuery();
					while(result.next()) {
						if(this.allRooms.containsKey(result.getString(1))==true) {
							setAvailability(this.allRooms.get(result.getString(1)), result.getString(2), result.getString(3));
							//break;
						}

					}
					conn.close();
				}
			}
			checkAvailability(this.allRooms, endNumber, startNumber, loopCount, start, end);
		} catch (Exception e1) {
			return;
		}

	}

	public void setAvailability(Rooms r, String start, String end) {
		r.setAvailability(start+"-"+end, new Booking(start, end, null, r, null, null, null));

	}

	public void clearAvailability(HashMap<String, Rooms> rooms) {
		for(HashMap.Entry<String, Rooms> room: rooms.entrySet()) {
			room.getValue().getAvailability().clear();
		}
	}

	public void checkAvailability(HashMap<String, Rooms> rooms, int endNumber, int startNumber, int loopCount, String start, String end) {
		this.free.clear();
		for(HashMap.Entry<String, Rooms> room: rooms.entrySet()) {
			HashMap<String, Booking> availability=room.getValue().getAvailability();
			for(int i=0; i<loopCount; i++) {
				if(startNumber+i!=13) {
					start=Integer.toString(startNumber+i)+":00";
					end=Integer.toString(startNumber+i+1)+":00";
					if(availability.containsKey(start+"-"+end)) {
						//System.out.println(room.getValue().getCode()+" OCCUPATO from: "+start+" to "+end+" for "+availability.get(start+"-"+end).getSubject());
					}
					else {
						//this.freeRooms.add(new FreeForBooking(room.getValue().getCode(), start, end, room.getValue().getSeats()));
						this.free.add(new Booking(start, end, null, room.getValue(), null, null, null));
						//System.out.println(room.getValue().getCode()+" LIBERO from: "+start+" to "+end+" posti: "+room.getValue().getSeats());
					}
				}
			}
		}
		Collections.sort(this.free);
	}

	public List<Booking> getFreeRooms(){
		return this.free;
	}

	public void semesterChceck(LocalDate myDate, String dayOfWeek) throws Exception {
		Connection conn=DBConnection.connect();
		String query="select * from semester";
		Statement preparedStmt=conn.createStatement();
		ResultSet result=preparedStmt.executeQuery(query);
		result.next();

		Date start=result.getDate(1);
		Date end=result.getDate(2);
		
		
		MyTimer currentTime=new MyTimer();
		
		
		if(myDate.compareTo(currentTime.getJavaDate())<0) {
			throw new Exception();
		}

		if(dayOfWeek.equals("SUNDAY")) {
			throw new Exception();
		}
		
		//System.out.println(myDate);
		if(myDate.compareTo(start.toLocalDate())>0 && myDate.compareTo(end.toLocalDate())>0) {
			
			throw new Exception();
		}
		conn.close();

	}

}