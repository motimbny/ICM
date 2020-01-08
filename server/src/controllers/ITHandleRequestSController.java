package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Enums.MessageTypeS;
import Enums.StageName;
import entity.DBSmessage;
import entity.DBmessage;
import entity.RequestUser;
import entity.updateRequest;

public class ITHandleRequestSController {

	private String user;
	private int reqId;
	private Connection connection;
	private DBmessage msg;

	public ITHandleRequestSController(DBmessage msg, Connection connection) {
		this.connection = connection;
		this.msg = msg;
	}

	public DBSmessage getITjob() {

		ArrayList<Object> arry = msg.getObjs();
		this.user = (String) arry.get(0);
		this.reqId = (int) arry.get(1);
		Statement stmt;
		DBSmessage dbs;
		ArrayList<Object> toSend = new ArrayList<Object>();
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT id, currentStage, itAppraiser, itCCC1, itCCC2, itCCC3, itPerformanceLeader, itTester FROM requeststages WHERE ( itAppraiser='"
							+ user + "' OR itCCC1='" + user + "' OR itCCC2='" + user + "' OR itCCC3='" + user
							+ "' OR itPerformanceLeader='" + user + "' OR itTester='" + user + "' ) AND id=" + reqId
							+ "");
			while (rs.next() != false) {
				if (rs.getString(3).equals(user))
					toSend.add("Appraiser");
				else if (rs.getString(4).equals(user))
					toSend.add("CEOControlCommitte");
				else if (rs.getString(5).equals(user) || rs.getString(6).equals(user))
					toSend.add("ControlCommitte");
				else if (rs.getString(7).equals(user))
					toSend.add("PerformanceLeader");
				else if (rs.getString(8).equals(user))
					toSend.add("Tester");
			toSend.add(rs.getString(2));
			}
			dbs = new DBSmessage(MessageTypeS.ITjobInReq, toSend);
			return dbs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public Object addTimeEstimated() {
		int id = (int) msg.getObjs().get(0);
		int timeEstimatedEvaluation = (int) msg.getObjs().get(1);
		String stage;
		ArrayList<Object> arr = new ArrayList<Object>();
		Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT currentStage FROM requeststages WHERE id=" + id + "");
			while (rs.next() != false) 
			{
				stage = rs.getString(1);
				if (stage.equals("waitingEvaluationTime")) 
				{
					stmt = connection.createStatement();
					stmt.executeUpdate("UPDATE requeststages SET timeEvaluation=" + timeEstimatedEvaluation+ " WHERE id=" + id + "");
					stmt.executeUpdate("UPDATE request SET currentStage='waitingSupervisorApproveEvaluationTime' WHERE id=" + id + "");
					stmt.executeUpdate("UPDATE requeststages SET currentStage='waitingSupervisorApproveEvaluationTime' WHERE id=" + id + "");
					arr.add(1);
				} else
					arr.add(0);
				
			}
			DBSmessage dbs = new DBSmessage(MessageTypeS.ITaddTimeEstimated, arr);
			return dbs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public Object addTimeEstimatedPerformance() {
		ArrayList<Object> arr = new ArrayList<Object>();
		int id = (int) msg.getObjs().get(0);
		int timeEstimatedPerform = (int) msg.getObjs().get(1);
		Statement stmt;
		String stage;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT currentStage FROM requeststages WHERE id=" + id + "");
			while (rs.next() != false) 
			{
				stage = rs.getString(1);
				if (stage.equals("waitingExecutionTime")) 
				{
					stmt = connection.createStatement();
					stmt.executeUpdate("UPDATE requeststages SET timePerform=" + timeEstimatedPerform + " WHERE id=" + id + "");
					stmt.executeUpdate("UPDATE request SET currentStage='waitingSupervisorApproveExecutionTime' WHERE id=" + id + "");
					stmt.executeUpdate("UPDATE requeststages SET currentStage='waitingSupervisorApproveExecutionTime' WHERE id=" + id + "");
					arr.add(1);
				}
				else
				{
					arr.add(0);
				}	
			}
			DBSmessage dbs = new DBSmessage(MessageTypeS.addTimeEstimatedPerformance, arr);
			return dbs;
		}
		catch (SQLException e) {}
		return null;
	}

	public Object saveChangeCompleted() {
		ArrayList<Object> arry = msg.getObjs();
		this.reqId = (int) arry.get(0);
		Statement stmt;
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate("UPDATE request SET currentStage='testing' WHERE id=" + reqId + "");
			stmt.executeUpdate("UPDATE requeststages SET currentStage='testing' WHERE id=" + reqId + "");
			stmt.executeUpdate("UPDATE requeststages SET timeTest=7 WHERE id=" + reqId + "");
			stmt.executeUpdate("UPDATE requesttime SET executionEND='"+formatter.format(date)+"' WHERE id="+reqId+"");
			stmt.executeUpdate("UPDATE requesttime SET testingStart='"+formatter.format(date)+"' WHERE id="+reqId+"");
		} catch (SQLException e) {
		}
		return null;
	}

	public Object saveTestApproval() {
		ArrayList<Object> arry = msg.getObjs();
		this.reqId = (int) arry.get(0);
		Statement stmt;
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate("UPDATE request SET currentStage='closing' WHERE id=" + reqId + "");
			stmt.executeUpdate("UPDATE requeststages SET currentStage='closing' WHERE id=" + reqId + "");
			stmt.executeUpdate("UPDATE requesttime SET testingExepted='"+formatter.format(date)+"' WHERE id="+reqId+"");
		} catch (SQLException e) {
		}
		return null;
	}

	public DBSmessage getListOfIT() {
		Statement stmt;
		DBSmessage dbs;
		updateRequest up = null;
		ArrayList<Object> listOfIT = new ArrayList<Object>();
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM itemployees WHERE employeePos='regular'");
			while (rs.next() != false) {
				listOfIT.add(rs.getString(2).toString());
			}
			dbs = new DBSmessage(MessageTypeS.ITShowEmployeeList, listOfIT);
			return dbs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public DBSmessage saveTester() {
		DBSmessage dbs;
		Statement stmt;
		ArrayList<Object> send = new ArrayList<Object>();
		ArrayList<Object> arry = msg.getObjs();
		this.reqId = (int) arry.get(0);
		String itTester = (String) arry.get(1);
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate("UPDATE requeststages SET itTester='" + itTester + "' WHERE id=" + reqId + "");
			send.add(1);
			dbs = new DBSmessage(MessageTypeS.ITSaveTester, send);
			return dbs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
