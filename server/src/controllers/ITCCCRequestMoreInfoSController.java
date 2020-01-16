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
import entity.updateRequest;

/**
 * The Class ITCCCRequestMoreInfoSController.
 */
public class ITCCCRequestMoreInfoSController {

	/** The id request. */
	private int idReq;

	/** The connection. */
	private Connection connection;

	/** The required info. */
	private String requiredInfo;

	/** The date. */
	private String date;

	/**
	 * Instantiates a new ITCCC request more info S controller.
	 *
	 * @param msg        the msg
	 * @param connection the connection
	 */
	public ITCCCRequestMoreInfoSController(DBmessage msg, Connection connection) {
		ArrayList<Object> arry = msg.getObjs();
		this.idReq = (int) arry.get(0);
		this.date = (String) arry.get(1);
		this.requiredInfo = (String) arry.get(2);
		this.connection = connection;
	}

	/**
	 * If IT require more info and press the Submit BTN this method update the data
	 * base.
	 *
	 * @return the object
	 */
	public Object submitRequireMoreInfo() {
		Statement stmt;
		PreparedStatement ps;
		DBSmessage dbs = null;
		ArrayList<Object> toSend = new ArrayList<Object>();
		try {
			ps = connection.prepareStatement("INSERT INTO moreinfo VALUES(?,?,?)");
			ps.setInt(1, this.idReq);
			ps.setString(2, this.date);
			ps.setString(3, this.requiredInfo);
			ps.executeUpdate();
			ps.close();

			stmt = connection.createStatement();
			stmt.executeUpdate("UPDATE request SET currentStage='meaningAssessment' WHERE id=" + idReq + "");
			stmt.executeUpdate("DELETE FROM evluationreport WHERE id=" + idReq + "");
			stmt.executeUpdate("UPDATE requeststages SET currentStage='meaningAssessment' WHERE id=" + idReq + "");
			dbs = new DBSmessage(MessageTypeS.ITsubmitRequireMoreInfo, toSend);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dbs;
	}

}
