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

public class ITMeaningAssessmentEvaluationReportSController {

	private int reqId;
	private Connection connection;
	DBmessage msg;
	public ITMeaningAssessmentEvaluationReportSController(DBmessage msg, Connection connection)
	{
		this.msg=msg;
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

	public DBSmessage submitEvaluationReport() {
		Statement stmt;
		DBSmessage dbs;
		ArrayList<Object> toSend = new ArrayList<Object>();
		try {
			stmt = connection.createStatement();
			PreparedStatement req = connection.prepareStatement("INSERT INTO evluationreport VALUES(?,?,?,?,?,?)");
			req.setInt(1,(int) msg.getObjs().get(0));
			req.setString(2,(String) msg.getObjs().get(1));
			req.setString(3,(String) msg.getObjs().get(2));
			req.setString(4,(String) msg.getObjs().get(3));
			req.setString(5,(String) msg.getObjs().get(4));
			req.setString(6,(String) msg.getObjs().get(5));
			req.executeUpdate();	
 			req.close();
 			stmt.executeUpdate("UPDATE request SET currentStage='examinationAndDecision' WHERE id="+reqId+"");
			stmt.executeUpdate("UPDATE requeststages SET currentStage='examinationAndDecision' WHERE id="+reqId+"");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
