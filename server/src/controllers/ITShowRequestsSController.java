package controllers;

import java.sql.Connection;
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

/**
 * The Class ITShowRequestsSController.
 */
public class ITShowRequestsSController {

	/** The user. */
	private String user;

	/** The user id request. */
	private int userIdReq;

	/** The connection. */
	private Connection connection;

	/**
	 * Instantiates a new IT show requests S controller.
	 *
	 * @param msg        the msg
	 * @param connection the connection
	 */
	public ITShowRequestsSController(DBmessage msg, Connection connection) {
		ArrayList<Object> arry = msg.getObjs();
		this.user = (String) arry.get(0);
		if (arry.size() == 2)
			userIdReq = (int) arry.get(1);
		this.connection = connection;
	}

	/**
	 * This method gets the request details from data base .
	 *
	 * @return the DB smessage
	 */
	public DBSmessage showRequest() {
		Statement stmt;
		Statement stmt1;
		Statement stmt2;
		DBSmessage dbs;
		ArrayList<Object> toSend = new ArrayList<Object>();
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT id, currentStatus, currentStage FROM requeststages WHERE ( itAppraiser='"
							+ user + "' OR itCCC1='" + user + "' OR itCCC2='" + user + "' OR itCCC3='" + user
							+ "' OR itPerformanceLeader='" + user + "' OR itTester='" + user
							+ "' ) AND currentStatus <> 'Closed'");
			while (rs.next() != false) {
				StageName name = null;
				String timeStage = "";
				String startTime = "";
				switch (rs.getString(3)) {
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
				int id = rs.getInt(1), x = 0, timeleft;
				Date today = new Date();
				DateFormat d = new SimpleDateFormat("yyyy-MM-dd");
				Date start = null;
				if (timeStage.equals(""))
					timeleft = 0;
				else {
					stmt1 = connection.createStatement();
					ResultSet daters = stmt1
							.executeQuery("SELECT " + timeStage + " FROM requeststages WHERE id=" + id + "");
					while (daters.next() != false) {
						x = daters.getInt(1);
					}
					stmt2 = connection.createStatement();
					ResultSet startrs = stmt2
							.executeQuery("SELECT " + startTime + " FROM requesttime WHERE id=" + id + "");
					while (startrs.next() != false) {
						start = startrs.getDate(1);
					}
					float diffrence = (today.getDate() - start.getDate());
					timeleft = (int) (x - diffrence);
				}
				RequestUser toAdd = new RequestUser(rs.getInt(1), rs.getString(2), name, timeleft);
				toSend.add(toAdd);
			}
			dbs = new DBSmessage(MessageTypeS.ShowReqIT, toSend);
			return dbs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This method is used when the IT want a specific request by using the search
	 * bar
	 *
	 * @return the DB smessage
	 */
	public DBSmessage showSPRequest() {
		Statement stmt;
		Statement stmt1;
		Statement stmt2;
		DBSmessage dbs;
		ArrayList<Object> toSend = new ArrayList<Object>();
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT id, currentStatus, currentStage FROM requeststages WHERE ( itAppraiser='"
							+ user + "' OR itCCC1='" + user + "' OR itCCC2='" + user + "' OR itCCC3='" + user
							+ "' OR itPerformanceLeader='" + user + "' OR itTester='" + user + "' ) AND id=" + userIdReq
							+ " AND currentStatus <> 'Closed'");
			while (rs.next() != false) {
				StageName name = null;
				String timeStage = "";
				String startTime = "";
				switch (rs.getString(3)) {
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
				int id = rs.getInt(1), x = 0, timeleft;
				Date today = new Date();
				DateFormat d = new SimpleDateFormat("yyyy-MM-dd");
				Date start = null;
				if (timeStage.equals(""))
					timeleft = 0;
				else {
					stmt1 = connection.createStatement();
					ResultSet daters = stmt1
							.executeQuery("SELECT " + timeStage + " FROM requeststages WHERE id=" + id + "");
					while (daters.next() != false) {
						x = daters.getInt(1);
					}
					stmt2 = connection.createStatement();
					ResultSet startrs = stmt2
							.executeQuery("SELECT " + startTime + " FROM requesttime WHERE id=" + id + "");
					while (startrs.next() != false) {
						start = startrs.getDate(1);
					}
					float diffrence = (today.getDate() - start.getDate());
					timeleft = (int) (x - diffrence);
				}
				RequestUser toAdd = new RequestUser(rs.getInt(1), rs.getString(2), name, timeleft);
				toSend.add(toAdd);
			}
			dbs = new DBSmessage(MessageTypeS.SearchReqIT, toSend);
			return dbs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This method get the number of all request which is associate with him.
	 *
	 * @return the DB smessage
	 */
	public DBSmessage numOfRequest() {
		Statement stmt;
		DBSmessage dbs;
		ArrayList<Object> toSend = new ArrayList<Object>();
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM requeststages WHERE itAppraiser='" + user
					+ "' OR itCCC1='" + user + "' OR itCCC2='" + user + "' OR itCCC3='" + user
					+ "' OR itPerformanceLeader='" + user + "' OR itTester='" + user + "'");
			if (!rs.next())
				toSend.add(0);
			else
				toSend.add(rs.getInt(1));
			dbs = new DBSmessage(MessageTypeS.IThomeRequestNum, toSend);
			return dbs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
