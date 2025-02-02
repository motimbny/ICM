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

/**
 * The Class ITMeaningAssessmentEvaluationReportSController.
 */
public class ITMeaningAssessmentEvaluationReportSController {

	/** The request id. */
	private int reqId;

	/** The connection. */
	private Connection connection;

	/** The msg. */
	DBmessage msg;

	/**
	 * Instantiates a new IT meaning assessment evaluation report S controller.
	 *
	 * @param msg        the msg
	 * @param connection the connection
	 */
	public ITMeaningAssessmentEvaluationReportSController(DBmessage msg, Connection connection) {
		this.msg = msg;
		ArrayList<Object> arry = msg.getObjs();
		this.reqId = (int) arry.get(0);
		this.connection = connection;
	}

	/**
	 * This method gets the name of the information system
	 *
	 * @return dbs
	 */
	public DBSmessage getITreqLocation() {
		ArrayList<Object> arr = msg.getObjs();
		this.reqId = (int) arr.get(0);
		Statement stmt;
		DBSmessage dbs;
		ArrayList<Object> toSend = new ArrayList<Object>();
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT infoSystem FROM request WHERE id=" + reqId + "");
			while (rs.next() != false) {
				String stage = rs.getString(1);
				toSend.add(stage);
			}
			dbs = new DBSmessage(MessageTypeS.ITgetLocation, toSend);
			return dbs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This method update the data base after IT press on Submit evaluation report.
	 *
	 * @return the DB smessage
	 */
	public DBSmessage submitEvaluationReport() {
		Statement stmt;
		DBSmessage dbs;
		ArrayList<Object> arry = new ArrayList<Object>();
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			stmt = connection.createStatement();
			PreparedStatement req = connection.prepareStatement("INSERT INTO evluationreport VALUES(?,?,?,?,?,?)");
			req.setInt(1, (int) msg.getObjs().get(0));
			req.setString(2, (String) msg.getObjs().get(1));
			req.setInt(3, Integer.parseInt((String) msg.getObjs().get(2)));
			req.setString(4, (String) msg.getObjs().get(3));
			req.setString(5, (String) msg.getObjs().get(4));
			req.setString(6, (String) msg.getObjs().get(5));
			req.executeUpdate();
			req.close();

			stmt.executeUpdate("UPDATE requesttime SET meaningAssessmentEND='" + formatter.format(date) + "' WHERE id="
					+ reqId + "");
			CheckExceptionsRequest checkExp = new CheckExceptionsRequest((int) msg.getObjs().get(0), connection);
			checkExp.checkException();
			stmt.executeUpdate("UPDATE request SET currentStage='examinationAndDecision' WHERE id=" + reqId + "");
			stmt.executeUpdate("UPDATE requeststages SET currentStage='examinationAndDecision' WHERE id=" + reqId + "");
			stmt.executeUpdate("UPDATE requeststages SET timeExaminationDecision=7 WHERE id=" + reqId + "");
			stmt.executeUpdate("UPDATE requesttime SET examinationAndDecisionStart='" + formatter.format(date)
					+ "' WHERE id=" + reqId + "");

			arry.add(1);
		} catch (SQLException e) {
			e.printStackTrace();
			arry.add(0);
		}
		dbs = new DBSmessage(MessageTypeS.ITSubmitEvaluationReport, arry);
		return dbs;
	}
}
