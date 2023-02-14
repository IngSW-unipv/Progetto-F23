package it.unipv.sfw.findme.controller.users.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTable;
import it.unipv.sfw.findme.view.users.admin.ConfirmFilePanel;
import it.unipv.sfw.findme.view.users.general_user.UserGUI;

public class FileChooseListner implements ActionListener{

	private JFileChooser choose;
	private JFrame thisFrame;
	private UserGUI frame;
	private JTable table;

	public FileChooseListner(JFileChooser choose, JFrame thisFrame, UserGUI frame, JTable table) {
		this.choose=choose;
		this.thisFrame=thisFrame;
		this.frame=frame;
		this.table=table;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String path;
		if (e.getActionCommand().equals(javax.swing.JFileChooser.APPROVE_SELECTION)) {
			File selectedFile = this.choose.getSelectedFile();
			path=selectedFile.getAbsolutePath();
			this.frame.removePanel();
			this.frame.addSecondPanel(new ConfirmFilePanel(path, this.frame, this.table));
			this.frame.revalidate();
			this.frame.repaint();
			this.thisFrame.dispose();
		} else if (e.getActionCommand().equals(javax.swing.JFileChooser.CANCEL_SELECTION)) {
			this.thisFrame.dispose();
		}
	}

}