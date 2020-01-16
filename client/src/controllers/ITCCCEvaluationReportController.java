package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Enums.MessageType;
import entity.DBmessage;
import entity.Evluationreport;
import entity.Request;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * The Class ITCCCEvaluationReportController. This window is in IT GUI and show
 * the evaluation report of IT
 * 
 * @author SHIRA
 */
public class ITCCCEvaluationReportController implements Initializable {

	/** The Main all controllers. */
	private MainAllControllers MainAllControllers;

	/**
	 * Instantiates a new ITCCC evaluation report controller.
	 */
	public ITCCCEvaluationReportController() {
		MainAllControllers = controllers.MainAllControllers.getInstance();
	}

	/** The home BTN. */
	@FXML
	private Button homeBTN;

	/** The show request BTN. */
	@FXML
	private Button showRequestBTN;

	/** The personal info BTN. */
	@FXML
	private Button personalInfoBTN;

	/** The help BTN. */
	@FXML
	private Button helpBTN;

	/** The logout BTN. */
	@FXML
	private Button logoutBTN;

	/** The Location. */
	@FXML
	private TextField Location;

	/** The description of change required. */
	@FXML
	private TextArea descriptionOfChangeRequired;

	/** The result of change. */
	@FXML
	private TextArea resultOfChange;

	/** The time estimated. */
	@FXML
	private TextField timeEstimated;

	/** The constraints and risks. */
	@FXML
	private TextArea constraintsAndRisks;

	/** The require more info BTN. */
	@FXML
	private Button requireMoreInfoBTN;

	/** The Deny CC evaluation report. */
	@FXML
	private Button DenyCCEvaluationReport;

	/** The Approve CC evaluation report. */
	@FXML
	private Button ApproveCCEvaluationReport;

	/** The request ID. */
	@FXML
	private TextField requestID;

	/** The back BTN. */
	@FXML
	private Button backBTN;

	/**
	 * Mouse click event, if "Back" button clicked, open the screen of "IT Handle
	 * Request".
	 *
	 * @param event The Back BTN
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void back(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITHandleRequest");
		MainAllControllers.changeWin();
	}

	/**
	 * If approve BTN pressed, this method create new DBmessage to send to server
	 *
	 * @param event the Approve BTN
	 */
	@FXML
	void approveEvaluationReport(MouseEvent event) {
		ArrayList<Object> arry = new ArrayList<Object>();
		arry.add(MainAllControllers.request);// request id
		DBmessage dbm;
		dbm = new DBmessage(MessageType.approveEvaluationReport, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					try {
						MainAllControllers.setWindowVar("ITshowRequests");
					} catch (IOException e) {
						e.printStackTrace();
					}
					MainAllControllers.changeWin();
				}
			});
		} catch (IOException e) {
		}
	}

	/**
	 * If Deny BTN pressed, this method create new DBmessage to send to server
	 *
	 * @param event the Deny BTN
	 */
	@FXML
	void denyEvaluationReport(MouseEvent event) {
		ArrayList<Object> arry = new ArrayList<Object>();
		arry.add(MainAllControllers.request);// request id
		DBmessage dbm;
		dbm = new DBmessage(MessageType.denyEvaluationReport, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					try {
						MainAllControllers.setWindowVar("ITshowRequests");
					} catch (IOException e) {
						e.printStackTrace();
					}
					MainAllControllers.changeWin();
				}
			});
		} catch (IOException e) {
		}
	}

	/**
	 * Mouse click event, if "help" button clicked, open the screen of "help".
	 *
	 * @param event The help BTN
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void goHelpPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITHelp");
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
		MainAllControllers.setWindowVar("ITHome");
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
	void goPersonalInfo(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITPersonalInfo");
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
	void goShowReq(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITshowRequests");
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
	void logoutPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("login");
		MainAllControllers.changeWin();
		MainAllControllers.logOutUser();
	}

	/**
	 * Require more info.
	 *
	 * @param event the BTN
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void requireMoreInfo(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITCCCRequestMoreInfo");
		MainAllControllers.changeWin();
	}

	/**
	 * This method sets the text in fields.
	 *
	 * @param ev the new text in fields
	 */
	void setTextInFields(Evluationreport ev) {
		requestID.setText("" + ev.getRequestID());
		Location.setText(ev.getLocation());
		timeEstimated.setText("" + ev.getTimeEstimated());
		descriptionOfChangeRequired.setText(ev.getDescriptionOfChangeRequired());
		resultOfChange.setText(ev.getResultOfChange());
		constraintsAndRisks.setText(ev.getConstraintsAndRisks());

	}

	/**
	 * Initializes GUI components before this window open.
	 *
	 * @param location  the location
	 * @param resources the resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ArrayList<Object> arry = new ArrayList<Object>();
		int s = MainAllControllers.request;
		arry.add(s);// request id
		String job = MainAllControllers.itHandlejob;
		requestID.setText("" + s);
		if (job.equals("CEOControlCommitte")) {
			requireMoreInfoBTN.setVisible(true);
			DenyCCEvaluationReport.setVisible(true);
			ApproveCCEvaluationReport.setVisible(true);
		} else
			backBTN.setVisible(true);
		DBmessage dbm;
		dbm = new DBmessage(MessageType.ITshowEvaluationReport, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {
		}
	}

}
