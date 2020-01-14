package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Enums.MessageType;
import entity.DBmessage;
import entity.updateRequest;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * The Class SupervisorTimeRequestController. This window is in Supervisor GUI
 * and display after "Update request time" pressed .
 */
public class SupervisorTimeRequestController implements Initializable {

	/** The Main all controllers. */
	private MainAllControllers MainAllControllers;

	/**
	 * Instantiates a new supervisor time request controller.
	 */
	public SupervisorTimeRequestController() {
		MainAllControllers = controllers.MainAllControllers.getInstance();
	}

	/** The home BTN. */
	@FXML
	private Button homeBTN;

	/** The show request BTN. */
	@FXML
	private Button showreBTN;

	/** The Message BTN. */
	@FXML
	private Button MessageBTN;

	/** The person BTN. */
	@FXML
	private Button personBTN;

	/** The help BTN. */
	@FXML
	private Button helpBTN;

	/** The logout BTN. */
	@FXML
	private Button logoutBTN;

	/** The request info BTN. */
	@FXML
	private Button requestInfoBTN;

	/** The request ID. */
	@FXML
	private TextField requestID;

	/** The dent evluation BTN. */
	@FXML
	private Button dentEvluationBTN;

	/** The evluation. */
	@FXML
	private TextField evluation;

	/** The approve evluation BTN. */
	@FXML
	private Button approveEvluationBTN;

	/** The deny execution BTN. */
	@FXML
	private Button denyExwcutionBTN;

	/** The execution. */
	@FXML
	private TextField execution;

	/** The approve execution BTN. */
	@FXML
	private Button approveExwcutionBTN;

	/** The Back to show. */
	@FXML
	private Button BackToShow;

	/** The saved. */
	@FXML
	private Label saved;

	/**
	 * This method crates a new DBmsg to send to server after "Approve" BTN clicked
	 * to approve and update the evaluation time of the request
	 *
	 * @param event The Approve BTN
	 */
	@FXML
	void approveEvluation(MouseEvent event) {
		DBmessage dbm;
		ArrayList<Object> arry = new ArrayList<Object>();
		arry.add(MainAllControllers.request);
		dbm = new DBmessage(MessageType.SupervisorApproveEvluationTime, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {
		}
	}

	/**
	 * This method crates a new DBmsg to send to server after "Approve" BTN clicked
	 * to approve and update the execution time of the request
	 *
	 * @param event The Approve BTN
	 */
	@FXML
	void approveExwcution(MouseEvent event) {
		DBmessage dbm;
		ArrayList<Object> arry = new ArrayList<Object>();
		arry.add(MainAllControllers.request);
		dbm = new DBmessage(MessageType.SupervisorApproveExecutionTime, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {
		}
	}

	/**
	 * This method crates a new DBmsg to send to server after "Deny" BTN clicked to
	 * deny and update the execution time of the request
	 *
	 * @param event The Deny BTN
	 */
	@FXML
	void denyExwcution(MouseEvent event) {
		DBmessage dbm;
		ArrayList<Object> arry = new ArrayList<Object>();
		arry.add(MainAllControllers.request);
		dbm = new DBmessage(MessageType.SupervisorDenyExecutionTime, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {
		}
	}

	/**
	 * This method crates a new DBmsg to send to server after "Deny" BTN clicked to
	 * deny and update the evaluation time of the request
	 *
	 * @param event the event
	 */
	@FXML
	void dentEvluation(MouseEvent event) {
		DBmessage dbm;
		ArrayList<Object> arry = new ArrayList<Object>();
		arry.add(MainAllControllers.request);
		dbm = new DBmessage(MessageType.SupervisorDenyEvluationTime, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {
		}
	}

	/**
	 * Back to Show requests.
	 *
	 * @param event the Back button
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void BackToS(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("SupervisorShowRequests");
		MainAllControllers.changeWin();
	}

	/**
	 * Mouse click event, if "help" button clicked, open the screen of "help".
	 *
	 * @param event The help BTN
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void goHelpPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("SupervisorHelp");
		MainAllControllers.changeWin();
	}

	/**
	 * Mouse click event, if "Home" button clicked, open the screen of "Home".
	 *
	 * @param event The Home BTN
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void goHomePage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("SupervisorHome");
		MainAllControllers.changeWin();
	}

	/**
	 * Mouse click event, if "logOut" button clicked, open the screen of "LogOut"
	 * and clean the fields.
	 *
	 * @param event The Logout BTN
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void goLogoutPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("login");
		MainAllControllers.changeWin();
		MainAllControllers.logOutUser();
	}

	/**
	 * Mouse click event, if "Personal info" button clicked, open the screen of
	 * "Personal information".
	 *
	 * @param event The Personal info BTN
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void goPersonalPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("SupervisorPersonalInfo");
		MainAllControllers.changeWin();
	}

	/**
	 * Mouse click event, if "Show requests" button clicked, open the screen of
	 * "Show requests".
	 *
	 * @param event The Show request BTN
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void goShowReqPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("SupervisorShowRequests");
		MainAllControllers.changeWin();
	}

	/**
	 * Mouse click event, if "Messages" button clicked, open the screen of
	 * "Messages".
	 *
	 * @param event The Messages BTN
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void messagePage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("SupervisorMessages");
		MainAllControllers.changeWin();
	}

	/**
	 * Request info window.
	 *
	 * @param event The request details BTN
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void requestInfoWindow(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("SupervisorRequestDetalies");
		MainAllControllers.changeWin();
	}

	/**
	 * Sets stage name on table
	 *
	 * @param list the new to fields
	 */
	public void setToFields(ArrayList<Object> list) {
		this.requestID.setText("" + list.get(0));
		this.evluation.setText("" + list.get(1));
		this.execution.setText("" + list.get(2));
		if ((!(list.get(1).equals(0))) && (list.get(3).equals("waitingSupervisorApproveEvaluationTime"))) {
			approveEvluationBTN.setVisible(true);
			dentEvluationBTN.setVisible(true);
		}
		if ((!(list.get(2).equals(0))) && (list.get(3).equals("waitingSupervisorApproveExecutionTime"))) {
			approveExwcutionBTN.setVisible(true);
			denyExwcutionBTN.setVisible(true);
		}
	}

	/**
	 * Initializes GUI components before this window open.
	 *
	 * @param location  the location
	 * @param resources the resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		DBmessage dbm;
		ArrayList<Object> arry = new ArrayList<Object>();
		arry.add(MainAllControllers.request);
		this.requestID.setText("" + MainAllControllers.request);
		dbm = new DBmessage(MessageType.SupervisorTimeRequest, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {
		}

	}

	/**
	 * This method set the label visible
	 */
	public void setOnSucsess() {
		saved.setVisible(true);
	}

}
