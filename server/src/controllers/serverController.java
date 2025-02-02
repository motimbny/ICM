package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Enums.MessageTypeS;
import boundries.mainServer;
import boundries.mainServerABS;
import entity.ConnectToDB;
import entity.DBSmessage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * The Class serverController.
 */
public class serverController {

	/** The connect. */
	@FXML
	private Button connect;

	/** The disconnect. */
	@FXML
	private Button disconnect;

	/** The messagebox. */
	@FXML
	private TextArea messagebox;

	/** The dbuser. */
	@FXML
	private TextField dbuser;

	/** The dbscheme. */
	@FXML
	private TextField dbscheme;

	/** The port. */
	@FXML
	private TextField port;

	/** The dbpassword. */
	@FXML
	private PasswordField dbpassword;

	/** The connecti. */
	private ConnectToDB connecti;

	/** The main server ABS. */
	private mainServerABS mainServerABS;

	/**
	 * This method is to connect to the data base
	 *
	 * @param event the connect BTN
	 */
	@FXML
	void connect(MouseEvent event) {
		messagebox.clear();
		if (dbpassword.getText().equals("") || dbuser.getText().equals("") || dbscheme.getText().equals("")
				|| port.getText().equals("")) {
			messagebox.setText("ERROR!!! Please provide all fields before connecting");
		} else {
			mainServerABS = new mainServerABS(Integer.parseInt(port.getText()), this);
			connecti = new ConnectToDB(dbpassword.getText(), dbuser.getText(), dbscheme.getText());
			mainServerABS.connectToDb(connecti.Connect());
			mainServerABS.startServer();
		}
		mainServer.NUM_OF_REQUEST = getNumOfRequest();
	}

	/**
	 * This method return the total number of the requests.
	 *
	 * @return the number of requests
	 */
	private int getNumOfRequest() {
		Statement stmt;
		DBSmessage dbs;
		ResultSet rs = null;
		ArrayList<Object> toSend = new ArrayList<Object>();
		try {
			stmt = connecti.Connect().createStatement();
			rs = stmt.executeQuery("SELECT COUNT(*) FROM request");
			rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			return rs.getInt(1);
		} catch (SQLException e) {
			System.out.println("Cant count number of request");
		}
		return 0;
	}

	/**
	 * This method disconnect the server.
	 *
	 * @param event disconnect BTN
	 */
	@FXML
	void disconnect(MouseEvent event) {
		mainServerABS.stopServer();
	}

	/**
	 * This method send a message to the server GUI text area.
	 *
	 * @param str the message
	 */
	public void showOnScreen(String str) {
		messagebox.setText(str);
	}

}
