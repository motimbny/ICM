package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Enums.MessageTypeS;
import Enums.StageName;
import entity.DBSmessage;
import entity.DBmessage;
import entity.RequestUser;

public class ITHandleRequestSController {

	private String user;
	private int reqId;
	private Connection connection;

	public ITHandleRequestSController(DBmessage msg, Connection connection) {
		ArrayList<Object> arry = msg.getObjs();
		this.user = (String) arry.get(0);
		this.reqId = (int) arry.get(1);
		this.connection = connection;
	}

	public DBSmessage getITjob() {
		Statement stmt;
		DBSmessage dbs;
		ArrayList<Object> toSend = new ArrayList<Object>();
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT id, itAppraiser, itCCC1, itCCC2, itCCC3, itPerformanceLeader, itTester FROM requeststages WHERE ( itAppraiser='"
							+ user + "' OR itCCC1='" + user + "' OR itCCC2='" + user + "' OR itCCC3='" + user
							+ "' OR itPerformanceLeader='" + user + "' OR itTester='" + user + "' ) AND id=" + reqId
							+ "");
			while (rs.next() != false) 
			{
				if (rs.getString(2).equals(user))
					toSend.add("Appraiser");
				else if (rs.getString(3).equals(user) || rs.getString(4).equals(user) || rs.getString(5).equals(user))
					toSend.add("ControlCommitte");
				else if (rs.getString(6).equals(user))
					toSend.add("PerformanceLeader");
				else if (rs.getString(7).equals(user))
					toSend.add("Tester");
			}
			dbs = new DBSmessage(MessageTypeS.ITjobInReq, toSend);
			return dbs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}
}
