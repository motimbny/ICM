package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * User Help screen Controller .
 * This window show the User's options.
 *
 * @author SHIRA
 */
public class UserHelpController {

	/** The Main all controllers. */
	private MainAllControllers MainAllControllers;

	/**
	 * Instantiates a new user help controller.
	 */
	public UserHelpController() {
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

	/** The person BTN. */
	@FXML
	private Button personBTN;

	/** The help BTN. */
	@FXML
	private Button helpBTN;

	/** The logout BTN. */
	@FXML
	private Button logoutBTN;

	/**
	 * Mouse click event, if "Add request" button clicked, open the screen of "Add
	 * new request".
	 * 
	 * @param event Add Request BTN
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void addreBTNE(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("UserAddRequest");
		MainAllControllers.changeWin();

	}

	/**
	 * Mouse click event, if "Home" button clicked, open the screen of "Home".
	 * 
	 * @param event The Home BTN
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void homeBack(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("userHome");
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
	 * @param event The Show Request BTN
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void showreBTNE(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("UserShowRequests");
		MainAllControllers.changeWin();
	}

}
