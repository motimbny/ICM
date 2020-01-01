package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Enums.MessageTypeS;
import entity.DBSmessage;
import entity.DBmessage;

public class ITExtensionRequestSController {
	private String user;
	private int reqId;
	private Connection connection;

	public ITExtensionRequestSController(DBmessage msg, Connection connection) {
		ArrayList<Object> arry = msg.getObjs();
		this.user = (String) arry.get(0);
		this.reqId = (int) arry.get(1);
		this.connection = connection;
	}
	
	public DBSmessage getITreqStage() {
		Statement stmt;
		DBSmessage dbs;
		ArrayList<Object> toSend = new ArrayList<Object>();
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT id, currentStage FROM requeststages WHERE ( itAppraiser='"
							+ user + "' OR itCCC1='" + user + "' OR itCCC2='" + user + "' OR itCCC3='" + user
							+ "' OR itPerformanceLeader='" + user + "' OR itTester='" + user + "' ) AND id=" + reqId+ "");
			while (rs.next() != false) 
			{
				toSend.add(rs.getString(2));
			}
			dbs = new DBSmessage(MessageTypeS.ITgetReqStage, toSend);
			return dbs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}
}
