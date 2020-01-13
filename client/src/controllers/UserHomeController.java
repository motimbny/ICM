package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Enums.MessageType;
import entity.DBmessage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * User home screen Controller.
 * This window is in User GUI , this is the first window after successful login.
 *
 * @author SHIRA
 */
public class UserHomeController implements Initializable {

	/**
	 * Instantiates a new user home controller.
	 */
	public UserHomeController() {
		MainAllControllers = controllers.MainAllControllers.getInstance();
	}

	/** The home BTN. */
	@FXML
	private Button homeBTN;

	/** The add request BTN. */
	@FXML
	private Button addreBTN;

	/** The show request BTN. */
	@FXML
	private Button showreBTN;

	/** The personal information BTN. */
	@FXML
	private Button personBTN;

	/** The help BTN. */
	@FXML
	private Button helpBTN;

	/** The logout BTN. */
	@FXML
	private Button logoutBTN;

	/** The hello user. */
	@FXML
	private Label helloUser;

	/** The request existing number. */
	@FXML
	private Label reqExNum;

	/** The Main all controllers. */
	private MainAllControllers MainAllControllers;

	/**
	 * Mouse click event, if "Add request" button clicked, open the screen of "Add
	 * new request".
	 * 
	 * @param event The Add request BTN
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void addreBTNE(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("UserAddRequest");
		MainAllControllers.changeWin();

	}

	/**
	 * Mouse click event, if "help" button clicked, open the screen of "help".
	 * 
	 * @param event The Help BTN
	 * @throws IOException Signals that an I/O exception has occurred.
	 */

	@FXML
	void helpBTNE(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("UserHelp");
		MainAllControllers.changeWin();
	}

	/**
	 * Mouse click event, if "logOut" button clicked, open the screen of "LogOut"
	 * and clean the fields.
	 * 
	 * @param event The LogOut BTN
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void logoutBTNE(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("login");
		MainAllControllers.changeWin();
		MainAllControllers.logOutUser();
	}

	/**
	 * Mouse click event, if "Personal info" button clicked, open the screen of
	 * "Personal information".
	 * 
	 * @param event The Personal info
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void personBTNE(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("UserPersonalInfo");
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
	void showreBTNE(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("UserShowRequests");
		MainAllControllers.changeWin();

	}

	/**
	 * Set requests number in the screen .
	 * 
	 * @param num The number of requests of this user.
	 */
	void setRequestNumber(int num) {
		reqExNum.setText(Integer.toString(num));
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
		helloUser.setText("Hello " + MainAllControllers.user.getName());
		ArrayList<Object> arry = new ArrayList<Object>();
		arry.add(MainAllControllers.user.getName());
		DBmessage dbm;
		dbm = new DBmessage(MessageType.homeRequestNum, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {
		}
	}

}
