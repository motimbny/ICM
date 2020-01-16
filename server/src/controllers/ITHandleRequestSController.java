package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Enums.MessageTypeS;
import Enums.StageName;
import entity.DBSmessage;
import entity.DBmessage;
import entity.RequestUser;
import entity.updateRequest;

/**
 * The Class ITHandleRequestSController.
 */
public class ITHandleRequestSController {

	/** The user. */
	private String user;

	/** The request id. */
	private int reqId;

	/** The connection. */
	private Connection connection;

	/** The msg. */
	private DBmessage msg;

	/**
	 * Instantiates a new IT handle request S controller.
	 *
	 * @param msg        the msg
	 * @param connection the connection
	 */
	public ITHandleRequestSController(DBmessage msg, Connection connection) {
		this.connection = connection;
		this.msg = msg;
	}

	/**
	 * This method get the IT job from data base.
	 *
	 * @return the IT job
	 */
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

	/**
	 * This method adds the time estimated that IT entered and save it in the data
	 * base.
	 *
	 * @return the object
	 */
	public Object addTimeEstimated() {
		int id = (int) msg.getObjs().get(0);
		int timeEstimatedEvaluation = (int) msg.getObjs().get(1);
		String stage;
		ArrayList<Object> arr = new ArrayList<Object>();
		Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT currentStage FROM requeststages WHERE id=" + id + "");
			while (rs.next() != false) {
				stage = rs.getString(1);
				if (stage.equals("waitingEvaluationTime")) {
					stmt = connection.createStatement();
					stmt.executeUpdate("UPDATE requeststages SET timeEvaluation=" + timeEstimatedEvaluation
							+ " WHERE id=" + id + "");
					stmt.executeUpdate(
							"UPDATE request SET currentStage='waitingSupervisorApproveEvaluationTime' WHERE id=" + id
									+ "");
					stmt.executeUpdate(
							"UPDATE requeststages SET currentStage='waitingSupervisorApproveEvaluationTime' WHERE id="
									+ id + "");
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

	/**
	 * This method adds the time estimated that performance entered and save it in
	 * the data base.
	 *
	 * @return the object
	 */
	public Object addTimeEstimatedPerformance() {
		ArrayList<Object> arr = new ArrayList<Object>();
		int id = (int) msg.getObjs().get(0);
		int timeEstimatedPerform = (int) msg.getObjs().get(1);
		Statement stmt;
		String stage;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT currentStage FROM requeststages WHERE id=" + id + "");
			while (rs.next() != false) {
				stage = rs.getString(1);
				if (stage.equals("waitingExecutionTime")) {
					stmt = connection.createStatement();
					stmt.executeUpdate(
							"UPDATE requeststages SET timePerform=" + timeEstimatedPerform + " WHERE id=" + id + "");
					stmt.executeUpdate(
							"UPDATE request SET currentStage='waitingSupervisorApproveExecutionTime' WHERE id=" + id
									+ "");
					stmt.executeUpdate(
							"UPDATE requeststages SET currentStage='waitingSupervisorApproveExecutionTime' WHERE id="
									+ id + "");
					arr.add(1);
				} else {
					arr.add(0);
				}
			}
			DBSmessage dbs = new DBSmessage(MessageTypeS.addTimeEstimatedPerformance, arr);
			return dbs;
		} catch (SQLException e) {
		}
		return null;
	}

	/**
	 * This method Save in data base that the change completed and update the
	 * relevant values.
	 *
	 * @return the object
	 */
	public Object saveChangeCompleted() {
		ArrayList<Object> toSend = new ArrayList<Object>();
		ArrayList<Object> arry = msg.getObjs();
		this.reqId = (int) arry.get(0);
		Statement stmt;
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate(
					"UPDATE requesttime SET executionEND='" + formatter.format(date) + "' WHERE id=" + reqId + "");
			CheckExceptionsRequest checkExp = new CheckExceptionsRequest(reqId, connection);
			checkExp.checkException();
			stmt.executeUpdate("UPDATE request SET currentStage='testing' WHERE id=" + reqId + "");
			stmt.executeUpdate("UPDATE requeststages SET currentStage='testing' WHERE id=" + reqId + "");
			stmt.executeUpdate("UPDATE requeststages SET timeTest=7 WHERE id=" + reqId + "");
			stmt.executeUpdate(
					"UPDATE requesttime SET testingStart='" + formatter.format(date) + "' WHERE id=" + reqId + "");
			toSend.add(1);
			DBSmessage dbs = new DBSmessage(MessageTypeS.ITshowReqAgain, toSend);
			return dbs;
		} catch (SQLException e) {
		}
		return null;
	}

	/**
	 * This method Save in data base that the test approval and update the relevant
	 * values.
	 *
	 * @return the object
	 */
	public Object saveTestApproval() {
		ArrayList<Object> toSend = new ArrayList<Object>();
		ArrayList<Object> arry = msg.getObjs();
		this.reqId = (int) arry.get(0);
		Statement stmt;
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate(
					"UPDATE requesttime SET testingExepted='" + formatter.format(date) + "' WHERE id=" + reqId + "");
			CheckExceptionsRequest checkExp = new CheckExceptionsRequest(reqId, connection);
			checkExp.checkException();
			stmt.executeUpdate("UPDATE request SET currentStage='closing' WHERE id=" + reqId + "");
			stmt.executeUpdate("UPDATE requeststages SET currentStage='closing' WHERE id=" + reqId + "");
			toSend.add(1);
			DBSmessage dbs = new DBSmessage(MessageTypeS.ITshowReqAgain, toSend);
			return dbs;
		} catch (SQLException e) {
		}
		return null;
	}

	/**
	 * This method gets the list of IT.
	 *
	 * @return the list of IT
	 */
	public DBSmessage getListOfIT() {
		Statement stmt;
		DBSmessage dbs;
		ArrayList<Object> arry = msg.getObjs();
		this.reqId = (int) arry.get(0);
		updateRequest up = null;
		ArrayList<Object> listOfIT = new ArrayList<Object>();
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT * FROM itemployees WHERE (employeePos='IT') AND employeeName <> (SELECT itAppraiser FROM requeststages WHERE id="
							+ reqId + ") AND employeeName <> (SELECT itPerformanceLeader FROM requeststages WHERE id="
							+ reqId + ")");
			while (rs.next() != false) {
				listOfIT.add(rs.getString(2).toString());
			}
			dbs = new DBSmessage(MessageTypeS.ITShowEmployeeList, listOfIT);
			System.out.println("here in server second");

			return dbs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This method update the IT Tester.
	 *
	 * @return the DB smessage
	 */
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

	/**
	 * This method update the Number of days for each stage.
	 *
	 * @return the DB smessage
	 */
	public DBSmessage numOfDays() {
		DBSmessage dbs;
		Statement stmt;
		ArrayList<Object> send = new ArrayList<Object>();
		ArrayList<Object> arry = msg.getObjs();
		this.reqId = (int) arry.get(0);
		String timeStage = "";
		String startTime = "";
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT currentStage FROM requeststages WHERE id=" + reqId + "");
			while (rs.next() != false) {
				StageName name = null;

				switch (rs.getString(1)) {
				case "supervisorApprovel":
					name = StageName.supervisorApprovel;
					break;
				case "waitingEvaluationTime":
					name = StageName.waitingEvaluationTime;
					break;
				case "waitingSupervisorApproveEvaluationTime":
					name = StageName.waitingSupervisorApproveEvaluationTime;
					break;
				case "meaningAssessment":
					name = StageName.meaningAssessment;
					timeStage = "timeEvaluation";
					startTime = "meaningAssessmentStart";
					break;
				case "waitingExecutionTime":
					name = StageName.waitingExecutionTime;
					timeStage = "timeExaminationDecision";
					startTime = "examinationAndDecisionStart";
					break;
				case "waitingSupervisorApproveExecutionTime":
					name = StageName.waitingSupervisorApproveExecutionTime;
					timeStage = "timeExaminationDecision";
					startTime = "examinationAndDecisionStart";
					break;
				case "examinationAndDecision":
					name = StageName.examinationAndDecision;
					timeStage = "timeExaminationDecision";
					startTime = "examinationAndDecisionStart";
					break;
				case "execution":
					name = StageName.execution;
					timeStage = "timePerform";
					startTime = "executiondStart";
					break;
				case "testing":
					name = StageName.testing;
					timeStage = "timeTest";
					startTime = "testingStart";
					break;
				case "closing":
					name = StageName.closing;
					break;
				case "Closed":
					name = StageName.Closed;
					break;
				}
			}

			int x = 0, timeleft;
			Date today = new Date();
			DateFormat d = new SimpleDateFormat("yyyy-MM-dd");
			Date start = null;
			if (timeStage.equals(""))
				timeleft = 0;
			else {
				stmt = connection.createStatement();
				ResultSet daters = stmt
						.executeQuery("SELECT " + timeStage + " FROM requeststages WHERE id=" + reqId + "");
				while (daters.next() != false) {
					x = daters.getInt(1);
				}
				stmt = connection.createStatement();
				ResultSet startrs = stmt
						.executeQuery("SELECT " + startTime + " FROM requesttime WHERE id=" + reqId + "");
				while (startrs.next() != false) {
					start = startrs.getDate(1);
				}
				float diffrence = (today.getDate() - start.getDate());
				timeleft = (int) (x - diffrence);
			}

			send.add(timeleft);
			dbs = new DBSmessage(MessageTypeS.ITrequestDaysLeft, send);
			return dbs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
