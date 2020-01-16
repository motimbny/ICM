package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Enums.MessageTypeS;
import entity.ConnectToDB;
import entity.DBSmessage;
import entity.DBmessage;
import entity.User;

/**
 * The Class loginSController.
 */
public class loginSController {

	/** The user. */
	private String user;

	/** The password. */
	private String password;

	/** The connection. */
	private Connection connection;

	/**
	 * Instantiates a new login S controller.
	 *
	 * @param msg        the msg
	 * @param connection the connection
	 */
	public loginSController(DBmessage msg, Connection connection) {
		this.user = ((String) msg.getObjs().get(0));
		this.password = ((String) msg.getObjs().get(1));
		this.connection = connection;
	}

	/**
	 * This method check the log in details of user in data base.
	 *
	 * @return the DB smessage
	 */
	public DBSmessage CheckLogIn() {
		Statement stmt;
		DBSmessage dbs;
		User toSend = null;
		boolean flag = false;
		ArrayList<Object> arry = new ArrayList<Object>();
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE userName='" + user + "'");
			if (rs.next() == false) {
				flag = false;
			} else {
				if (password.equals(rs.getString(2))) {
					toSend = new User(rs.getString(1), rs.getString(2), rs.getString(3));
					flag = true;
				}
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (flag == false) {
			dbs = new DBSmessage(MessageTypeS.LoginFail, null);
		} else {
			arry.add(toSend);
			dbs = new DBSmessage(MessageTypeS.Login, arry);
		}
		return dbs;
	}

}
