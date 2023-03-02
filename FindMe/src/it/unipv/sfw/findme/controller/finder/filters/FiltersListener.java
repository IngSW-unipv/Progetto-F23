package it.unipv.sfw.findme.controller.finder.filters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import it.unipv.sfw.findme.model.booking.Booking;
import it.unipv.sfw.findme.model.exceptions.ExceptionFrame;
import it.unipv.sfw.findme.model.users.general_user.Users;
import it.unipv.sfw.findme.view.finder.FinderMainPanel;
import it.unipv.sfw.findme.view.finder.filters.FilterCheckBox;
import it.unipv.sfw.findme.view.users.general_user.UserGUI;

public class FiltersListener implements ActionListener{
	private FinderMainPanel panel;
	private List<Booking> freeRooms;
	private List<Booking> filteredRooms;
	private List<Booking> filterTemporary;
	private HashMap<String, List<Booking>> allFiltered;
	private LinkedList<FilterCheckBox> allFilters;
	private UserGUI frame;
	private Users user;
	public FiltersListener(FinderMainPanel panel, LinkedList<FilterCheckBox> allFilters, UserGUI frame, Users user) {
		this.filteredRooms=new ArrayList<Booking>();
		this.allFiltered=new HashMap<String, List<Booking>>();
		this.panel=panel;
		this.allFilters=allFilters;
		this.frame=frame;
		this.user=user;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		this.freeRooms=this.panel.getFreeRooms();
		this.allFiltered.clear();
		this.filterTemporary=this.panel.getFreeRooms();
		for(FilterCheckBox singleFilter: allFilters) {
			if(singleFilter.isSelected()==true) {
				filterTemporary = singleFilter.filter(filterTemporary, singleFilter);
				
				
			}
		}
		filteredRooms = filterTemporary;
	
		this.panel.removeAll();
		this.panel.revalidate();
		this.panel.repaint();


		this.panel.add(new FinderMainPanel(this.filteredRooms, this.panel.getFreeRooms(), this.frame, this.user));

		this.panel.revalidate();
		this.panel.repaint();

		if(this.filteredRooms.size()== 0) {
			new ExceptionFrame("No room found with required features");
		}

	}
}


