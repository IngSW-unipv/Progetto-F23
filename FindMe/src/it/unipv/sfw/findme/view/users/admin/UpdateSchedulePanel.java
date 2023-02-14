package it.unipv.sfw.findme.view.users.admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import it.unipv.sfw.findme.controller.users.admin.FileChooseListner;
import it.unipv.sfw.findme.view.users.general_user.UserGUI;


public class UpdateSchedulePanel extends JPanel{

	public UpdateSchedulePanel(UserGUI frame, JTable table) {
		setLayout (new GridBagLayout());
		setBackground(Color.white);
		GridBagConstraints c=new GridBagConstraints();

		JLabel selectFile=new JLabel("Select File to Import: ");
		selectFile.setFont(new Font("Comic Sans MS", Font.BOLD,17));
		selectFile.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=0;
		add(selectFile, c);
		//
		JButton browseButton=new JButton("Browse");
		browseButton.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		browseButton.setForeground(Color.white);
		browseButton.setBackground(new Color(145,0,0));
		browseButton.setOpaque(true);
		browseButton.setBorderPainted(false);
		browseButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent evt) {
		    	BrowseFrame browseFrame=new BrowseFrame(frame, table);
		    }
		});
		c.gridx=1;
		c.gridy=0;
		c.insets= new Insets(0,20,0,0);
		add(browseButton, c);
		
		JButton back=new JButton("Back");
		back.setFont(new Font("Comic Sans MS", Font.PLAIN,13));
		back.setForeground(Color.white);
		back.setBackground(new Color(145,0,0));
		back.setOpaque(true);
		back.setBorderPainted(false);
		back.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent evt) {
		    	frame.removePanel();
		    	frame.addSecondPanel(new ViewCoursesPanel(frame));
				frame.revalidate();
				frame.repaint();
		    }
		});
		c.gridx=2;
		c.gridy=1;
		c.insets= new Insets(30,0,0,0);
		add(back, c);
	}

}


class BrowseFrame extends JFrame{

	public BrowseFrame(UserGUI frame, JTable table) {
		JFileChooser choose=new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
		choose.setFileFilter(filter);
		choose.setMultiSelectionEnabled(false);
		FileChooseListner chooseListener=new FileChooseListner(choose, this, frame, table);
		choose.addActionListener(chooseListener);
		add(choose);

		setSize(500,500);
		setTitle("Choose File");

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);

	}
}


