package it.unipv.sfw.findme.controller.users.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import it.unipv.sfw.findme.model.database.CSVtoDB;
import it.unipv.sfw.findme.model.database.DBConnection;
import it.unipv.sfw.findme.model.exceptions.ExceptionFrame;


public class UpdateCourseListener implements ActionListener{

	private String code;
	private String path;

	public UpdateCourseListener(String code, String path) {
		this.code=code;
		this.path=path;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Connection conn=DBConnection.connect();
		try {

			String query="delete from schedule where Course_ID=?";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, this.code);
			preparedStmt.execute();

			CSVtoDB converter=new CSVtoDB(this.code, this.path);

			conn.close();
			new ExceptionFrame("Course Updated");

		}catch (Exception e1) {
			System.out.println("errore query");
			e1.printStackTrace();	
		}

	}

}