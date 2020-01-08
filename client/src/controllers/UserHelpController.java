package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
/**
 * User Help screen Controller 
 * @author SHIRA
 */
public class UserHelpController {
	private MainAllControllers MainAllControllers;

	public UserHelpController() {
		MainAllControllers = controllers.MainAllControllers.getInstance();
	}
	/**
     * buttons and labels of User help screen
     */
	@FXML
	private Button homeBTN;

	@FXML
	private Button addreBTN;

	@FXML
	private Button showreBTN;

	@FXML
	private Button personBTN;

	@FXML
	private Button helpBTN;

	@FXML
	private Button logoutBTN;
	
	/**
	 * Mouse click event, if "Add request" button clicked, open the screen of "Add new request"
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void addreBTNE(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("UserAddRequest");
		MainAllControllers.changeWin();

	}
    /**
     * Mouse click event, if "Home" button clicked, open the screen of "Home"
     * @param event
     * @throws IOException
     */
	@FXML
	void homeBack(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("userHome");
		MainAllControllers.changeWin();

	}
	 /**
     * Mouse click event, if "help" button clicked, open the screen of "help"
     * @param event
     * @throws IOException
     */
	@FXML
	void helpBTNE(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("UserHelp");
		MainAllControllers.changeWin();
	}
	/**
     * Mouse click event, if "logOut" button clicked, open the screen of "LogOut" and clean the fields
     * @param event
     * @throws IOException
     */
	@FXML
	void logoutBTNE(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("login");
		MainAllControllers.changeWin();
    	MainAllControllers.logOutUser();
	}
	 /**
     * Mouse click event, if "Personal info" button clicked, open the screen of "Personal information"
     * @param event
     * @throws IOException
     */
	@FXML
	void personBTNE(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("UserPersonalInfo");
		MainAllControllers.changeWin();
	}
	/**
     * Mouse click event, if "Show requests" button clicked, open the screen of "Show requests"
     * @param event
     * @throws IOException
     */
	@FXML
	void showreBTNE(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("UserShowRequests");
		MainAllControllers.changeWin();
	}

}
