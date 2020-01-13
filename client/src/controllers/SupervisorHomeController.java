package controllers;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

import Enums.MessageType;
import entity.DBmessage;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * Supervisor home screen controller. This window is in Supervisor GUI , this is
 * the first window after successful login.
 *
 * @author SHIRA
 */
public class SupervisorHomeController implements Initializable {

	/** The Main all controllers. */
	private MainAllControllers MainAllControllers;

	/** The time. */
	@FXML
	private Label time;

	/** The minute. */
	private int minute;

	/** The hour. */
	private int hour;

	/** The second. */
	private int second;

	/** The clock. */
	private Thread clock;

	/**
	 * Instantiates a new supervisor home controller.
	 */
	public SupervisorHomeController() {
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

	/** The hello supervisor. */
	@FXML
	private Label helloSupervisor;

	/** The Existing requests. */
	@FXML
	private Label ExistingRequests;

	/** The super mess. */
	@FXML
	private Label superMess;

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
	 * Set requests number in the screen .
	 *
	 * @param num The number of requests of this user.
	 */
	void setRequestNumber(int num) {
		ExistingRequests.setText("Existing Requests : " + Integer.toString(num));
	}

	/**
	 * Sets the number of messages in the screen.
	 *
	 * @param num the new messages number
	 */
	void setMessagesNumber(int num) {
		superMess.setText("You have: " + num + " unread messages");
	}

	/**
	 * Initializes GUI components before this window open. Get the user name from
	 * the DB and show it in this screen Also get the number of request of this user
	 * from DB
	 *
	 * @param location  the location
	 * @param resources the resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		helloSupervisor.setText("   Hello " + MainAllControllers.user.getName());
		ArrayList<Object> arry = new ArrayList<Object>();
		arry.add(MainAllControllers.user.getName());
		DBmessage dbm;
		dbm = new DBmessage(MessageType.supervisorHomeRequestNum, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {
		}

	}

}
