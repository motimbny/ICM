package controllers;

import java.sql.Connection;
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
import entity.Evluationreport;
import entity.Request;
import entity.updateRequest;

/**
 * The Class ITCCCEvaluationReportSController.
 */
public class ITCCCEvaluationReportSController {

	/** The id request. */
	private int idReq;

	/** The connection. */
	private Connection connection;

	/**
	 * Instantiates a new ITCCC evaluation report S controller.
	 *
	 * @param msg        the msg
	 * @param connection the connection
	 */
	public ITCCCEvaluationReportSController(DBmessage msg, Connection connection) {
		ArrayList<Object> arry = msg.getObjs();
		this.idReq = (int) arry.get(0);
		this.connection = connection;
	}

	/**
	 * This method take the data of evaluation report from data base and send it to
	 * client to show it in evaluation report.
	 *
	 * @return the DB smessage
	 */
	public DBSmessage showEvaluationReport() {
		Statement stmt;
		DBSmessage dbs = null;
		ArrayList<Object> toSend = new ArrayList<Object>();
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, location, timeEstimated, description,"
					+ " result, constraints FROM evluationreport WHERE id='" + idReq + "'");
			while (rs.next() != false) {
				Evluationreport evtoSend = new Evluationreport(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6));
				toSend.add(evtoSend);
			}
			dbs = new DBSmessage(MessageTypeS.ITshowEvaluationReport, toSend);
			return dbs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dbs;
	}

	/**
	 * if IT Approve evaluation report this method update the data base.
	 *
	 * @return the DB smessage
	 */
	public DBSmessage approveEvaluationReport() {
		Statement stmt;
		updateRequest up = null;
		ArrayList<Object> toSend = new ArrayList<Object>();
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate("UPDATE requesttime SET examinationAndDecisiondEND='" + formatter.format(date)
					+ "' WHERE id=" + idReq + "");
			CheckExceptionsRequest checkExp = new CheckExceptionsRequest(idReq, connection);
			checkExp.checkException();
			stmt.executeUpdate("UPDATE request SET currentStage='waitingExecutionTime' WHERE id=" + idReq + "");
			stmt.executeUpdate("UPDATE requeststages SET currentStage='waitingExecutionTime' WHERE id=" + idReq + "");
			toSend.add(1);
			DBSmessage dbs = new DBSmessage(MessageTypeS.ITshowReqAgain, toSend);
			return dbs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * if IT deny evaluation report this method update the data base.
	 *
	 * @return the object
	 */
	public Object denyEvaluationReport() {
		Statement stmt;
		String name = "";
		updateRequest up = null;
		ArrayList<Object> toSend = new ArrayList<Object>();
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate("UPDATE requesttime SET examinationAndDecisiondEND='" + formatter.format(date)
					+ "' WHERE id=" + idReq + "");
			CheckExceptionsRequest checkExp = new CheckExceptionsRequest(idReq, connection);
			checkExp.checkException();
			stmt.executeUpdate("UPDATE request SET currentStage='closing' WHERE id=" + idReq + "");
			stmt.executeUpdate("UPDATE requeststages SET currentStage='closing' WHERE id=" + idReq + "");
			toSend.add(1);
			DBSmessage dbs = new DBSmessage(MessageTypeS.ITshowReqAgain, toSend);
			return dbs;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
