package controllers;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Enums.MessageType;
import entity.DBmessage;
import entity.extensionrequest;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * Supervisor extension request screen controller. This window is in Supervisor
 * GUI, and opened if the supervisor press on request from table and click on
 * View extension request BTN
 *
 * @author SHIRA
 */
public class SupervisorExtensionRequestController implements Initializable {

	/** The Main all controllers. */
	private MainAllControllers MainAllControllers;

	/**
	 * Instantiates a new supervisor extension request controller.
	 */
	public SupervisorExtensionRequestController() {
		MainAllControllers = controllers.MainAllControllers.getInstance();
	}

	/** The home BTN. */
	@FXML
	private Button homeBTN;

	/** The show request BTN. */
	@FXML
	private Button showreBTN;

	/** The personal info BTN. */
	@FXML
	private Button personBTN;

	/** The Message BTN. */
	@FXML
	private Button MessageBTN;

	/** The help BTN. */
	@FXML
	private Button helpBTN;

	/** The logout BTN. */
	@FXML
	private Button logoutBTN;

	/** The back. */
	@FXML
	private Button BACK;

	/** The approve. */
	@FXML
	private Button APPROVE;

	/** The deny. */
	@FXML
	private Button DENY;

	/** The request ID. */
	@FXML
	private TextField requestID;

	/** The request STG. */
	@FXML
	private TextField requestSTG;

	/** The request IT. */
	@FXML
	private TextField requestIT;

	/** The request Exp. */
	@FXML
	private TextArea requestEXp;

	/** The answer. */
	@FXML
	private Label answer;

	/** The request time. */
	@FXML
	private TextField requestTime;

	/**
	 * If the supervisor press on Approve BTN, the relevant details of the request
	 * will update and will save in DB
	 *
	 * @param event The Approve BTN
	 */
	@FXML
	void approveEX(MouseEvent event) {
		DBmessage dbm;
		ArrayList<Object> arry = new ArrayList<Object>();
		arry.add(MainAllControllers.request);
		arry.add("approve");
		arry.add(requestSTG.getText());
		arry.add(requestTime.getText());
		dbm = new DBmessage(MessageType.superviserExtensionRequestAnswer, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {
		}
	}

	/**
	 * If the supervisor press on Deny BTN, the relevant details of the request will
	 * update and will save in DB
	 *
	 * @param event the event
	 */
	@FXML
	void denyEXT(MouseEvent event) {
		DBmessage dbm;
		ArrayList<Object> arry = new ArrayList<Object>();
		arry.add(MainAllControllers.request);
		arry.add("denied");
		arry.add(requestSTG.getText());
		arry.add(requestTime.getText());
		dbm = new DBmessage(MessageType.superviserExtensionRequestAnswer, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {
		}
	}

	/**
	 * Mouse click event, if "Show request" button clicked, open the screen of "Show
	 * request".
	 *
	 * @param event The Show request BTN
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void backToShow(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("SupervisorShowRequests");
		MainAllControllers.changeWin();
	}

	/**
	 * Mouse click event, if "help" button clicked, open the screen of "help".
	 *
	 * @param event The Help BTN
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
	 * @param event The Show requests BTN
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void goShowReqPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("SupervisorShowRequests");
		MainAllControllers.changeWin();
	}

	/**
	 * This method sets the request details in relevant fields
	 *
	 * @param ev The Extension request object
	 */
	public void setToFields(extensionrequest ev) {
		this.requestID.setText(Integer.toString(ev.getId()));
		this.requestSTG.setText(ev.getStage());
		this.requestIT.setText(ev.getItHandler());
		this.requestEXp.setText(ev.getReason());
		this.requestTime.setText(Integer.toString(ev.getTime()));
	}

	/**
	 * Initializes GUI components before this window open.
	 *
	 * @param arg0 the location
	 * @param arg1 the resources
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		DBmessage dbm;
		ArrayList<Object> arry = new ArrayList<Object>();
		arry.add(MainAllControllers.request);
		dbm = new DBmessage(MessageType.superviserExtensionRequest, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {
		}
	}

	/**
	 * This method set the answer label visible.
	 */
	public void showAnswer() {
		answer.setVisible(true);
	}

}
