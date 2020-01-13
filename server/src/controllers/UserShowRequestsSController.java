package controllers;

import java.sql.Connection;
//import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import Enums.MessageTypeS;
import Enums.StageName;
import entity.DBSmessage;
import entity.DBmessage;
import entity.RequestUser;

/**
 * The Class UserShowRequestsSController.
 */
public class UserShowRequestsSController {
	
	/** The user. */
	private String user;
	
	/** The user id req. */
	private int userIdReq;
	
	/** The connection. */
	private Connection connection;

	/**
	 * Instantiates a new user show requests S controller.
	 *
	 * @param msg the msg
	 * @param connection the connection
	 */
	public UserShowRequestsSController(DBmessage msg, Connection connection) {
		ArrayList<Object> arry = msg.getObjs();

		this.user = (String) arry.get(0);
		if (arry.size() == 2)
			userIdReq = (int) arry.get(1);
		this.connection = connection;
	}

	/**
	 * Show request.
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
			ResultSet rs = stmt.executeQuery(
					"SELECT id, currentStatus, currentStage FROM request WHERE userSubFullName='" + user + "'");
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
				RequestUser toAdd = new RequestUser(id, rs.getString(2), name, timeleft);
				toSend.add(toAdd);
			}

			dbs = new DBSmessage(MessageTypeS.ShowReqUser, toSend);
			return dbs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Show SP request.
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
					.executeQuery("SELECT id, currentStatus, currentStage FROM request WHERE userSubFullName='" + user
							+ "'AND id=" + userIdReq + "");
			while (rs.next() != false) {
				StageName name = null;
				String timeStage = "";
				String startTime= "";
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
					startTime="meaningAssessmentStart";
					break;
				case "waitingExecutionTime":
					name = StageName.waitingExecutionTime;
					timeStage = "timeExaminationDecision";
					startTime="examinationAndDecisionStart";
					break;
				case "waitingSupervisorApproveExecutionTime":
					name = StageName.waitingSupervisorApproveExecutionTime;
					timeStage = "timeExaminationDecision";
					startTime="examinationAndDecisionStart";
					break;
				case "examinationAndDecision":
					name = StageName.examinationAndDecision;
					timeStage = "timeExaminationDecision";
					startTime="examinationAndDecisionStart";
					break;
				case "execution":
					name = StageName.execution;
					timeStage = "timePerform";
					startTime="executiondStart";
					break;
				case "testing":
					name = StageName.testing;
					timeStage = "timeTest";
					startTime="testingStart";
					break;
				case "closing":
					name = StageName.closing;
					break;
				case "Closed":
					name = StageName.Closed;
					break;
				}
				int id=rs.getInt(1), x=0, timeleft;
				Date today=new Date();
				DateFormat d=new SimpleDateFormat("yyyy-MM-dd");
				Date start=null;
				if (timeStage.equals(""))
					timeleft = 0;
				else {
					stmt1 = connection.createStatement();
					ResultSet daters = stmt1.executeQuery("SELECT " + timeStage + " FROM requeststages WHERE id=" + id + "");
					while (daters.next() != false) {
						x = daters.getInt(1);}
					stmt2 = connection.createStatement();
					ResultSet startrs = stmt2.executeQuery("SELECT " + startTime + " FROM requesttime WHERE id=" + id + "");
					while(startrs.next()!=false)
					{
						start=startrs.getDate(1);
					}
					float diffrence=(today.getDate()-start.getDate());
					timeleft=(int) (x-diffrence);
				}
				RequestUser toAdd = new RequestUser(id, rs.getString(2), name, timeleft);
				toSend.add(toAdd);
			}
			dbs = new DBSmessage(MessageTypeS.SearchReqUser, toSend);
			return dbs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Num of request.
	 *
	 * @return the DB smessage
	 */
	public DBSmessage numOfRequest() {
		Statement stmt;
		DBSmessage dbs;
		ArrayList<Object> toSend = new ArrayList<Object>();
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM request WHERE userSubFullName='" + user + "'");
			if (!rs.next())
				toSend.add(0);
			else
				toSend.add(rs.getInt(1));
			dbs = new DBSmessage(MessageTypeS.homeRequestNum, toSend);
			return dbs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Mnum of request.
	 *
	 * @return the object
	 */
	public Object MnumOfRequest() {
		Statement stmt;
		DBSmessage dbs;
		ArrayList<Object> toSend = new ArrayList<Object>();
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM request");
			if (!rs.next())
				toSend.add(0);
			else
				toSend.add(rs.getInt(1));
			toSend.add(numofMessages(user));
			dbs = new DBSmessage(MessageTypeS.MhomeRequestNum, toSend);
			return dbs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Numof messages.
	 *
	 * @param name the name
	 * @return the int
	 */
	public int numofMessages(String name)
	{
		Statement stmt;
		DBSmessage dbs;
		int num;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM messages WHERE toWho='"+name+"' AND read1=0");
			if (!rs.next())
				num=0;
			else
				return rs.getInt(1);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return 0;
	}

}
