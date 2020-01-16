package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Enums.MessageTypeS;
import entity.DBSmessage;
import entity.DBmessage;
import entity.Messages;

/**
 * The Class MessagesShowController.
 */
public class MessagesShowController {

	/** The connection. */
	private Connection connection;

	/** The db. */
	private DBmessage db;

	/**
	 * Instantiates a new messages show controller.
	 *
	 * @param db         the db
	 * @param connection the connection
	 */
	public MessagesShowController(DBmessage db, Connection connection) {
		this.db = db;
		this.connection = connection;
	}

	/**
	 * This method get the details of the message from the data base and send it to
	 * client, show it in the IT Manager GUI.
	 *
	 * @return the DB smessage
	 */
	public DBSmessage MgetMessagesToShow() {
		Statement stmt;
		DBSmessage dbs;
		String towho = (String) db.getObjs().get(0);
		ArrayList<Object> toSend = new ArrayList<Object>();
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM messages WHERE toWho='" + towho + "'");
			while (rs.next() != false) {
				Messages toAdd = new Messages(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getInt(6));
				toSend.add(toAdd);
			}
			dbs = new DBSmessage(MessageTypeS.MangerShowMessages, toSend);
			return dbs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This method update the details of the IT Manager messages in the data base.
	 *
	 * @return the DB smessage
	 */
	public DBSmessage MUpdateMessagesToShow() {
		Statement stmt;
		String towho = (String) db.getObjs().get(0);
		String senyby = (String) db.getObjs().get(1);
		String subject = (String) db.getObjs().get(2);
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate("UPDATE messages SET read1=1 WHERE toWho='" + towho + "' AND sentBy='" + senyby
					+ "' AND subject='" + subject + "' ");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return MgetMessagesToShow();
	}

	/**
	 * This method get the details of the message from the data base and send it to
	 * client, show it in the Supervisor GUI.
	 *
	 * @return the DB smessage
	 */
	public DBSmessage SgetMessagesToShow() {
		Statement stmt;
		DBSmessage dbs;
		String towho = (String) db.getObjs().get(0);
		ArrayList<Object> toSend = new ArrayList<Object>();
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM messages WHERE toWho='" + towho + "'");
			while (rs.next() != false) {
				Messages toAdd = new Messages(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getInt(6));
				toSend.add(toAdd);
			}
			dbs = new DBSmessage(MessageTypeS.SuperviserShowMessages, toSend);
			return dbs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This method update the details of the Supervisor messages in the data base.
	 *
	 * @return the DB smessage
	 */
	public DBSmessage SUpdateMessagesToShow() {
		Statement stmt;
		String towho = (String) db.getObjs().get(0);
		String senyby = (String) db.getObjs().get(1);
		String subject = (String) db.getObjs().get(2);
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate("UPDATE messages SET read1=1 WHERE toWho='" + towho + "' AND sentBy='" + senyby
					+ "' AND subject='" + subject + "' ");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return SgetMessagesToShow();
	}
}
