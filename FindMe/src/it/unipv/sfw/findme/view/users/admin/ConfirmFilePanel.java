package it.unipv.sfw.findme.view.users.admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import it.unipv.sfw.findme.controller.users.admin.UpdateCourseListener;
import it.unipv.sfw.findme.controller.users.general_user.NewPanelListener;
import it.unipv.sfw.findme.view.users.general_user.UserGUI;


public class ConfirmFilePanel extends JPanel{
	
	public ConfirmFilePanel(String path, UserGUI frame, JTable table) {
		setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();
		
		setBackground(Color.white);
		
		c.anchor = GridBagConstraints.WEST;
		JLabel pathLabelInfo=new JLabel("Selected file: ");
		pathLabelInfo.setFont(new Font("Comic Sans MS", Font.BOLD,17));
		pathLabelInfo.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=0;
		c.insets= new Insets(0,0,20,0);
		add(pathLabelInfo, c);
		
		c.anchor = GridBagConstraints.EAST;
		JLabel pathLabel=new JLabel(path);
		pathLabel.setFont(new Font("Comic Sans MS", Font.BOLD,13));
		c.gridx=1;
		c.gridy=0;
		add(pathLabel, c);
		
		c.anchor = GridBagConstraints.WEST;
		JLabel changeInfo=new JLabel("Change Info: ");
		changeInfo.setFont(new Font("Comic Sans MS", Font.BOLD,17));
		changeInfo.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=2;
		add(changeInfo, c);
		//
		JButton changeFile=new JButton("Change");
		changeFile.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		changeFile.setForeground(Color.white);
		changeFile.setBackground(new Color(145,0,0));
		changeFile.setOpaque(true);
		changeFile.setBorderPainted(false);
		changeFile.addActionListener(new NewPanelListener(frame, new ViewCoursesPanel(frame)));
		c.gridx=1;
		c.gridy=2;
		add(changeFile, c);
		
		int row=table.getSelectedRow();
		String code=(String) table.getValueAt(row, 0);
		JLabel selectedReplace=new JLabel("Selected Course to be Updated: ");
		selectedReplace.setFont(new Font("Comic Sans MS", Font.BOLD,17));
		selectedReplace.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=1;
		add(selectedReplace, c);
		
		c.anchor = GridBagConstraints.WEST;
		JLabel codeLabel=new JLabel(code);
		codeLabel.setFont(new Font("Comic Sans MS", Font.BOLD,13));
		c.gridx=1;
		c.gridy=1;
		add(codeLabel, c);
		
		
		JLabel updateSchedule=new JLabel("Update Schedule: ");
		updateSchedule.setFont(new Font("Comic Sans MS", Font.BOLD,17));
		updateSchedule.setForeground(new Color(145,0,0));
		
		c.gridx=0;
		c.gridy=4;
		add(updateSchedule, c);
		JButton updateButton=new JButton("Update");
		updateButton.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		updateButton.setForeground(Color.white);
		updateButton.setBackground(new Color(145,0,0));
		updateButton.setOpaque(true);
		updateButton.setBorderPainted(false);
		UpdateCourseListener update=new UpdateCourseListener(code, path);
		updateButton.addActionListener(update);
		c.gridx=1;
		c.gridy=4;
		add(updateButton, c);
		
	}
}



