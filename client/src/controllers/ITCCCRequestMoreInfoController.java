package controllers;

import java.io.IOException;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import java.util.ArrayList;

import Enums.MessageType;
import entity.DBmessage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * The Class ITCCCRequestMoreInfoController. This window is in IT GUI and
 * display to the IT the option to add more information to the request
 * 
 * @author SHIRA
 */
public class ITCCCRequestMoreInfoController implements Initializable {

	/** The Main all controllers. */
	private MainAllControllers MainAllControllers;

	/**
	 * Instantiates a new ITCCC request more info controller.
	 */
	public ITCCCRequestMoreInfoController() {
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

	/** The required information. */
	@FXML
	private TextArea requiredInformation;

	/** The send CC require more info. */
	@FXML
	private Button sendCCRequireMoreInfo;

	/** The request ID. */
	@FXML
	private TextField requestID;

	/** The date. */
	@FXML
	private TextField date;

	/** The request was submitted. */
	@FXML
	private Label requestWasSubmitted;

	/** The fill all fields. */
	@FXML
	private Label fillAllFields;

	/** The Back to show. */
	@FXML
	private Button BackToShow;

	/**
	 * Mouse click event, if "Back" button clicked, open the screen of "IT Handle
	 * Request".
	 *
	 * @param event The Back BTN
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void BackToS(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITHandleRequest");
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
	 * If it press on Submit BTN this method create DBMessage to send to server to
	 * require more info.
	 *
	 * @param event The Submit BTN
	 */
	@FXML
	void submitRequireMoreInfo(MouseEvent event) {
		if (requiredInformation.getText().equals("")) {
			fillAllFields.setVisible(true);
		} else {
			fillAllFields.setVisible(false);
			ArrayList<Object> arr = new ArrayList<Object>();
			arr.add(Integer.parseInt(requestID.getText()));
			arr.add(date.getText());
			arr.add(requiredInformation.getText());
			DBmessage dbm;
			dbm = new DBmessage(MessageType.ITsubmitRequireMoreInfo, arr);
			try {
				MainAllControllers.sendToAbsServer(dbm);
			} catch (IOException e) {
			}
		}
	}

	/**
	 * This method set the label visible.
	 */
	public void setOnSucsess() {
		requestWasSubmitted.setVisible(true);
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
		arry.add(s); // request id
		requestID.setText("" + s);
		LocalDate myObj = LocalDate.now();
		date.setText("" + myObj);
	}

}
