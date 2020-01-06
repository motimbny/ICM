package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Enums.MessageTypeS;
import entity.DBSmessage;
import entity.DBmessage;
import entity.Evluationreport;

public class ITTestFailurReportSController {

	private int idReq;
	private String summry;
	private String date;
	private Connection connection;

	public ITTestFailurReportSController(DBmessage msg, Connection connection) {
		ArrayList<Object> arry = msg.getObjs();
		this.idReq = Integer.parseInt((String)msg.getObjs().get(0));
		this.date=(String) arry.get(1);
		this.summry = (String) arry.get(2);
		this.connection = connection;
	}

	public DBSmessage submitFailurReport() {
		PreparedStatement ps;
		DBSmessage dbs;
		Statement stmt;
		ArrayList<Object> arr = new ArrayList<Object>();
		try {
			ps = connection.prepareStatement("INSERT INTO failurreport VALUES(?,?,?)");
			ps.setInt(1, this.idReq);
			ps.setString(2, this.date);
			ps.setString(3, this.summry);
			ps.executeUpdate();
			ps.close();
			stmt = connection.createStatement();
			stmt.executeUpdate("UPDATE request SET currentStage='execution' WHERE id="+this.idReq+"");
			stmt.executeUpdate("UPDATE requeststages SET currentStage='execution' WHERE id="+this.idReq+"");
		} catch (SQLException e) {}
		arr.add(1);
		dbs = new DBSmessage(MessageTypeS.ITFailurReport, arr);
		return dbs;
	}

}
