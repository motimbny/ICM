package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Enums.MessageTypeS;
import entity.DBSmessage;
import entity.DBmessage;

public class ITMeaningAssessmentEvaluationReportSController {

	private int reqId;
	private Connection connection;

	public ITMeaningAssessmentEvaluationReportSController(DBmessage msg, Connection connection)
	{
		ArrayList<Object> arry = msg.getObjs();
		this.reqId = (int) arry.get(0);
		this.connection = connection;
	}

	public DBSmessage getITreqLocation() {
		
		Statement stmt;
		DBSmessage dbs;
		ArrayList<Object> toSend = new ArrayList<Object>();
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, infoSystem  FROM request WHERE id=" + reqId+ "");
			while (rs.next() != false) 
			{
				toSend.add(rs.getString(2));
			}
			dbs = new DBSmessage(MessageTypeS.ITgetLocation, toSend);
			return dbs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
