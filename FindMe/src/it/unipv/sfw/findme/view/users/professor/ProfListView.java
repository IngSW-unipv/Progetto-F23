package it.unipv.sfw.findme.view.users.professor;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import it.unipv.sfw.findme.model.booking.Booking;

public class ProfListView extends JPanel{

	public ProfListView(ArrayList<String> l){
		
		setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		
		c.gridx=0;
		c.gridy=0;
		
		JLabel text = new JLabel("Lista Prof Ordinati per Num Prenotazioni");
		add(text,c);
		
		JList<Booking> list=new JList(l.toArray());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane listScroller = new JScrollPane(list);
		c.gridy=1;
		add(listScroller,c);

		
		
	}
	
	
}
