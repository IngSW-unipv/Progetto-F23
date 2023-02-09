package it.unipv.sfw.findme.users.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import it.unipv.sfw.findme.courses.Course;
import it.unipv.sfw.findme.database.DBConnection;
import it.unipv.sfw.findme.exceptions.ExceptionFrame;
import it.unipv.sfw.findme.users.general_user.NewPanelListener;
import it.unipv.sfw.findme.users.general_user.UserGUI;


public class ViewCoursesPanel extends JPanel{
	
	private ButtonsPanel buttons;
	private JTable table;

	public ViewCoursesPanel(UserGUI frame) {
		setLayout (new BorderLayout());


		table=loadCourses();
		table=resizeColumnWidth(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TableRowSorter<TableModel> rowSorter= new TableRowSorter<>(table.getModel());
		JTextField filterField = new JTextField(50);
		table.setRowSorter(rowSorter);
		table.setDefaultEditor(Object.class, null);

		JPanel tablePanel = new JPanel(new BorderLayout());
		JLabel filter= new JLabel("Filter:");
		filter.setForeground(new Color(145,0,0));
		filter.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		tablePanel.add(filter,BorderLayout.WEST);
		tablePanel.add(filterField, BorderLayout.CENTER);
		
		JScrollPane listScroller = new JScrollPane(table);
		tablePanel.add(listScroller, BorderLayout.NORTH);

		add(tablePanel, BorderLayout.NORTH);
		
		filterField.getDocument().addDocumentListener(new DocumentListener(){

			@Override
			public void insertUpdate(DocumentEvent e) {
				String text = filterField.getText();

				if (text.trim().length() == 0) {
					rowSorter.setRowFilter(null);
				} else {
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				String text = filterField.getText();

				if (text.trim().length() == 0) {
					rowSorter.setRowFilter(null);
				} else {
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				throw new UnsupportedOperationException("Not supported yet.");
			}

		});
		
		buttons=new ButtonsPanel(frame, table);
		add(buttons, BorderLayout.CENTER);
		


	}
	
	public ButtonsPanel getButtonsPanel() {
		return this.buttons;
	}
	
	public JTable getTable() {
		return this.table;
	}

	public JTable loadCourses(){
		String query;
		try {

			Connection conn=DBConnection.connect();

			Statement statement=conn.createStatement();
			query="SELECT count(courses.Course_ID) FROM courses";
			ResultSet rows=statement.executeQuery(query);
			rows.next();
			int nrows=rows.getInt(1);

			query="SELECT count(*) AS NUMBEROFCOLUMNS FROM information_schema.columns WHERE table_name ='courses'";
			ResultSet columns=statement.executeQuery(query);
			columns.next();
			int ncolumns=columns.getInt(1);

			String[] columnNames=new String[ncolumns];
			query="SELECT `COLUMN_NAME` FROM `INFORMATION_SCHEMA`.`COLUMNS` WHERE `TABLE_SCHEMA`='test' AND `TABLE_NAME`='courses'";
			ResultSet names=statement.executeQuery(query);
			int i=0;
			while(names.next()) {
				columnNames[i]=names.getString(1);
				i++;
			}


			String[][] courses=new String[nrows][ncolumns];

			query="SELECT * FROM courses";
			ResultSet all=statement.executeQuery(query);
			int nLines=0;
			while(all.next()) {
				for(i=0; i<ncolumns; i++) {
					courses[nLines][i]=all.getString(1+i);
				}
				nLines++;				
			}

			JTable table=new JTable(courses, columnNames);


			conn.close();
			return table;

		} catch (Exception e1) {
			System.out.println("errore query");
			e1.printStackTrace();
			return null;
		}
	}


	public JTable resizeColumnWidth(JTable table) {
		final TableColumnModel columnModel = table.getColumnModel();
		for (int column = 0; column < table.getColumnCount(); column++) {
			int width = 15; // Min width
			for (int row = 0; row < table.getRowCount(); row++) {
				TableCellRenderer renderer = table.getCellRenderer(row, column);
				Component comp = table.prepareRenderer(renderer, row, column);
				width = Math.max(comp.getPreferredSize().width +1 , width);
			}
			if(width > 300)
				width=300;
			columnModel.getColumn(column).setPreferredWidth(width);
		}
		return table;
	}

}

class ButtonsPanel extends JPanel{

	public ButtonsPanel(UserGUI frame, JTable table) {
		setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		setBackground(Color.white);

		JButton addCourseButton=new JButton("Add Course");
		addCourseButton.setFont(new Font("Comic Sans MS", Font.BOLD,13));
		addCourseButton.setForeground(Color.white);
		addCourseButton.setBackground(new Color(145,0,0));
		addCourseButton.setOpaque(true);
		addCourseButton.setBorderPainted(false);
		addCourseButton.addActionListener(new NewPanelListener(frame, new AddCoursePanel(frame)));
		c.gridx=0;
		c.gridy=1;
		c.insets= new Insets(10,20,0,0);
		add(addCourseButton, c);
		JButton addSchedule=new JButton("Update Schedule");
		addSchedule.setFont(new Font("Comic Sans MS", Font.BOLD,13));
		addSchedule.setForeground(Color.white);
		addSchedule.setBackground(new Color(145,0,0));
		addSchedule.setOpaque(true);
		addSchedule.setBorderPainted(false);
		addSchedule.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if(table.getSelectionModel().isSelectionEmpty()==false) {
					frame.removePanel();
					frame.addSecondPanel(new UpdateSchedulePanel(frame, table));
					frame.revalidate();
					frame.repaint();
				}else {	
				}
			}
		});
		c.gridx=1;
		c.gridy=1;
		c.insets= new Insets(10,20,0,0);
		add(addSchedule, c);
		JButton removeButton=new JButton("Remove");
		RemoveListener remove=new RemoveListener(frame, table);
		removeButton.setFont(new Font("Comic Sans MS", Font.BOLD,13));
		removeButton.setForeground(Color.white);
		removeButton.setBackground(new Color(145,0,0));
		removeButton.setOpaque(true);
		removeButton.setBorderPainted(false);
		removeButton.addActionListener(remove);
		c.insets= new Insets(10,20,0,0);
		c.gridx=2;
		c.gridy=1;
		add(removeButton, c);
		//
		JButton back=new JButton("Back");
		back.setFont(new Font("Comic Sans MS", Font.BOLD,13));
		back.setForeground(Color.white);
		back.setBackground(new Color(145,0,0));
		back.setOpaque(true);
		back.setBorderPainted(false);
		c.gridx=3;
		c.gridy=1;
		c.insets= new Insets(10,80,0,0);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				frame.removePanel();
			}
		});
		add(back, c);
	}
}

class RemoveListener implements ActionListener{
	
	private UserGUI frame;
	private JTable table;
	
	public RemoveListener(UserGUI frame, JTable table) {
		this.frame=frame;
		this.table=table;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int row=table.getSelectedRow();
		String code=(String) table.getValueAt(row, 0);
		
		Admin user=(Admin)frame.getUser();
		user.removeCourse(new Course(code, null, 0));
		
		this.frame.removePanel();
		this.frame.addSecondPanel(new ViewCoursesPanel(this.frame));
		this.frame.revalidate();
		this.frame.repaint();
		
		new ExceptionFrame("Course Removed Successfully!");
		
	}
	
}

