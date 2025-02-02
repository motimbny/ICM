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
import entity.DBSmessage;
import entity.DBmessage;
import entity.ITemployee;
import entity.extensionrequest;
import entity.updateRequest;
import javafx.scene.input.MouseEvent;

/**
 * The Class SupervisorUpdateRequestSController.
 */
public class SupervisorUpdateRequestSController {

	/** The flag 2. */
	private int flag = 0, flag2 = 0;

	/** The connection. */
	private Connection connection;

	/** The list. */
	private ArrayList<Object> list;

	/** The msg. */
	private DBmessage msg;

	/**
	 * Instantiates a new supervisor update request S controller.
	 *
	 * @param dbm        the dbm
	 * @param connection the connection
	 */
	public SupervisorUpdateRequestSController(DBmessage dbm, Connection connection) {
		this.list = dbm.getObjs();
		this.msg = dbm;
		this.connection = connection;
	}

	/**
	 * This method updates the request IT members
	 *
	 * @return the report
	 */
	public DBSmessage getReport() {
		int numReport = (int) list.get(0);
		Statement stmt;
		Statement stmt1;
		DBSmessage dbs;
		updateRequest up = null;
		ArrayList<Object> toSend = new ArrayList<Object>();
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM requeststages WHERE id='" + numReport + "'");
			while (rs.next() != false) {
				up = new updateRequest(rs.getInt(1), rs.getString(4), rs.getString(8), rs.getString(9), rs.getInt(10),
						rs.getInt(11), rs.getInt(12), rs.getInt(13));
				if (rs.getString(3).equals("supervisorApprovel") && flag == 1) {
					stmt1 = connection.createStatement();
					stmt1.executeUpdate(
							"UPDATE request SET currentStage='waitingEvaluationTime' WHERE id=" + numReport + "");
					stmt1.executeUpdate("UPDATE request SET currentStatus='Active' WHERE id=" + numReport + "");
					stmt1.executeUpdate(
							"UPDATE requeststages SET currentStage='waitingEvaluationTime' WHERE id=" + numReport + "");
					stmt1.executeUpdate("UPDATE requeststages SET currentStatus='Active' WHERE id=" + numReport + "");

				}
				toSend.add(numReport);
				toSend.add(rs.getString(4));
				toSend.add(rs.getString(8));
				toSend.add(rs.getString(3));
			}
			// toSend.add(up);
			if (flag2 == 0)
				dbs = new DBSmessage(MessageTypeS.SupervisorUpdateRequest, toSend);
			else
				dbs = new DBSmessage(MessageTypeS.SupervisorUpdateRequest1, toSend);
			return dbs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This method update the executer in a specific request
	 *
	 * @return the DB smessage
	 */
	public DBSmessage updatechangeExecuter() {
		int num = (int) msg.getObjs().get(0);
		String changeexecuter = (String) msg.getObjs().get(1);
		String changeapprieser = (String) msg.getObjs().get(2);
		Statement stmt;
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate(
					"UPDATE requeststages SET itPerformanceLeader='" + changeexecuter + "' WHERE id=" + num + "");
			stmt.executeUpdate("UPDATE requeststages SET itAppraiser='" + changeapprieser + "' WHERE id=" + num + "");
			flag = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		flag2 = 1;
		return getReport();

	}

	/**
	 * This method return the list of IT available to choose from
	 *
	 * @return the list of IT
	 */
	public DBSmessage getListOfIT() {
		int reqId = (int) msg.getObjs().get(0);
		Statement stmt;
		DBSmessage dbs;
		updateRequest up = null;
		ArrayList<Object> listOfIT = new ArrayList<Object>();
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT * FROM itemployees WHERE (employeePos='IT') AND employeeName <> (SELECT itAppraiser FROM requeststages WHERE id="
							+ reqId + ") AND employeeName <> (SELECT itPerformanceLeader FROM requeststages WHERE id="
							+ reqId + ") AND employeeName <> (SELECT itTester FROM requeststages WHERE id=" + reqId
							+ ") ");
			while (rs.next() != false) {
				listOfIT.add(rs.getString(2).toString());
			}
			dbs = new DBSmessage(MessageTypeS.getListOfIT, listOfIT);
			return dbs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Gets the list of total IT members
	 *
	 * @return the list of IT for user add
	 */
	public DBSmessage getListOfITforUserAdd() {

		Statement stmt;
		DBSmessage dbs;
		updateRequest up = null;
		ArrayList<Object> listOfIT = new ArrayList<Object>();
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM itemployees WHERE employeePos='IT'");
			while (rs.next() != false) {
				listOfIT.add(rs.getString(2).toString());
			}
			dbs = new DBSmessage(MessageTypeS.getListOfIT, listOfIT);
			return dbs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This method return the IT CC or CEO
	 *
	 * @return the cc
	 */
	public DBSmessage getCC() {
		Statement stmt;
		DBSmessage dbs;
		updateRequest up = null;
		ArrayList<Object> listOfIT = new ArrayList<Object>();
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM itemployees WHERE employeePos='CEO'");
			while (rs.next() != false) {
				listOfIT.add(rs.getString(2).toString());
			}
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT * FROM itemployees WHERE employeePos='CC'");
			while (rs.next() != false) {
				listOfIT.add(rs.getString(2).toString());
			}
			dbs = new DBSmessage(MessageTypeS.getListOfIT, listOfIT);
			return dbs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This method return the time report.
	 *
	 * @return the time report
	 */
	public DBSmessage getTimeReport() {
		int id = (int) list.get(0);
		Statement stmt;
		DBSmessage dbs;
		ArrayList<Object> toSend = new ArrayList<Object>();
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT id, currentStage, timeEvaluation, timePerform FROM requeststages WHERE id='" + id + "'");
			while (rs.next() != false) {
				toSend.add(rs.getInt(1));
				toSend.add(rs.getInt(3));
				toSend.add(rs.getInt(4));
				toSend.add(rs.getString(2));
			}
			dbs = new DBSmessage(MessageTypeS.SupervisorTimeRequest, toSend);
			return dbs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This method update the evaluation time of the request in data base after
	 * supervisor approve
	 *
	 * @return the DB smessage
	 */
	public DBSmessage saveApproveEv() {
		int id = (int) list.get(0);
		Statement stmt;
		DBSmessage dbs;
		ArrayList<Object> toSend = new ArrayList<Object>();
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate("UPDATE requeststages SET currentStage='meaningAssessment' WHERE id=" + id + "");
			stmt.executeUpdate("UPDATE request SET currentStage='meaningAssessment' WHERE id=" + id + "");
			stmt.executeUpdate("UPDATE requesttime SET meaningAssessmentStart='" + formatter.format(date)
					+ "' WHERE id=" + id + "");
			toSend.add(1);
			dbs = new DBSmessage(MessageTypeS.SupervisorApproveEvluationTime, toSend);
			return dbs;
		} catch (SQLException e) {
		}

		return null;
	}

	/**
	 * This method update the evaluation time of the request in data base after
	 * supervisor deny
	 *
	 * @return the DB smessage
	 */
	public DBSmessage saveDenyEv() {
		int id = (int) list.get(0);
		Statement stmt;
		DBSmessage dbs;
		ArrayList<Object> toSend = new ArrayList<Object>();
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate("UPDATE requeststages SET currentStage='waitingEvaluationTime' WHERE id=" + id + "");
			stmt.executeUpdate("UPDATE request SET currentStage='waitingEvaluationTime' WHERE id=" + id + "");
			toSend.add(1);
			dbs = new DBSmessage(MessageTypeS.SupervisorDenyEvluationTime, toSend);
			return dbs;
		} catch (SQLException e) {
		}
		return null;
	}

	/**
	 * This method update the execution time of the request in data base after
	 * supervisor approve
	 *
	 * @return the DB smessage
	 */
	public DBSmessage saveApproveEx() {
		int id = (int) list.get(0);
		Statement stmt;
		DBSmessage dbs;
		ArrayList<Object> toSend = new ArrayList<Object>();
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate("UPDATE requeststages SET currentStage='execution' WHERE id=" + id + "");
			stmt.executeUpdate("UPDATE request SET currentStage='execution' WHERE id=" + id + "");
			stmt.executeUpdate(
					"UPDATE requesttime SET executiondStart='" + formatter.format(date) + "' WHERE id=" + id + "");
			toSend.add(1);
			dbs = new DBSmessage(MessageTypeS.SupervisorApproveExecutionTime, toSend);
			return dbs;
		} catch (SQLException e) {
		}

		return null;
	}

	/**
	 * This method update the execution time of the request in data base after
	 * supervisor deny
	 *
	 * @return the DB smessage
	 */
	public DBSmessage saveDenyEx() {
		int id = (int) list.get(0);
		Statement stmt;
		DBSmessage dbs;
		ArrayList<Object> toSend = new ArrayList<Object>();
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate("UPDATE requeststages SET currentStage='waitingExecutionTime' WHERE id=" + id + "");
			stmt.executeUpdate("UPDATE request SET currentStage='waitingExecutionTime' WHERE id=" + id + "");
			toSend.add(1);
			dbs = new DBSmessage(MessageTypeS.SupervisorDenyEvluationTime, toSend);
			return dbs;
		} catch (SQLException e) {
		}
		return null;
	}

	/**
	 * This method Update the change of the request in data base.
	 */
	public void updatechangeSaveInTable() {
		int num = (int) msg.getObjs().get(0);
		String changeexecuter = (String) msg.getObjs().get(1);
		String changeapprieser = (String) msg.getObjs().get(2);
		Statement stmt;
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate(
					"UPDATE requeststages SET itPerformanceLeader='" + changeexecuter + "' WHERE id=" + num + "");
			stmt.executeUpdate("UPDATE requeststages SET itAppraiser='" + changeapprieser + "' WHERE id=" + num + "");
			flag = 1;

			PreparedStatement req = connection.prepareStatement("INSERT INTO updates VALUES(?,?,?)");
			req.setString(1, (String) msg.getObjs().get(3));
			req.setString(2, "employee changed in request " + num + " date is: " + new Date().toString());
			req.setString(3, java.time.LocalDate.now().toString());
			req.executeUpdate();
			req.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
