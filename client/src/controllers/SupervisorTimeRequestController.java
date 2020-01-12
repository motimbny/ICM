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
 * The Class SupervisorTimeRequestController.
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

	/** The showre BTN. */
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

	/** The deny exwcution BTN. */
	@FXML
	private Button denyExwcutionBTN;

	/** The execution. */
	@FXML
	private TextField execution;

	/** The approve exwcution BTN. */
	@FXML
	private Button approveExwcutionBTN;

	/** The Back to show. */
	@FXML
	private Button BackToShow;

	/** The saved. */
	@FXML
	private Label saved;

	/**
	 * Approve evluation.
	 *
	 * @param event the event
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
	 * Approve exwcution.
	 *
	 * @param event the event
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
	 * Deny exwcution.
	 *
	 * @param event the event
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
	 * Dent evluation.
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
	 * Back to S.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void BackToS(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("SupervisorShowRequests");
		MainAllControllers.changeWin();
	}

	/**
	 * Go help page.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void goHelpPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("SupervisorHelp");
		MainAllControllers.changeWin();
	}

	/**
	 * Go home page.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void goHomePage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("SupervisorHome");
		MainAllControllers.changeWin();
	}

	/**
	 * Go logout page.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void goLogoutPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("login");
		MainAllControllers.changeWin();
		MainAllControllers.logOutUser();
	}

	/**
	 * Go personal page.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void goPersonalPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("SupervisorPersonalInfo");
		MainAllControllers.changeWin();
	}

	/**
	 * Go show req page.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void goShowReqPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("SupervisorShowRequests");
		MainAllControllers.changeWin();
	}

	/**
	 * Message page.
	 *
	 * @param event the event
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
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void requestInfoWindow(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("SupervisorRequestDetalies");
		MainAllControllers.changeWin();
	}

	/**
	 * Sets the to fields.
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
	 * Initialize.
	 *
	 * @param location the location
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
	 * Sets the on sucsess.
	 */
	public void setOnSucsess() {
		saved.setVisible(true);
	}

}
