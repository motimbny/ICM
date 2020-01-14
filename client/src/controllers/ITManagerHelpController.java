package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * The Class ITManagerHelpController. This window show the IT Manager's options.
 *
 * @author SHIRA
 */
public class ITManagerHelpController {

	/** The Main all controllers. */
	private MainAllControllers MainAllControllers;

	/**
	 * Instantiates a new IT manager help controller.
	 */
	public ITManagerHelpController() {
		MainAllControllers = controllers.MainAllControllers.getInstance();
	}

	/** The home BTN. */
	@FXML
	private Button homeBTN;

	/** The show request BTN. */
	@FXML
	private Button showRequestBTN;

	/** The generate report BTN 1. */
	@FXML
	private Button generateReportBTN1;

	/** The employees mang BTN. */
	@FXML
	private Button employeesMangBTN;

	/** The logout BTN 1. */
	@FXML
	private Button logoutBTN1;

	/** The personal info BTN. */
	@FXML
	private Button personalInfoBTN;

	/** The Message BTN. */
	@FXML
	private Button MessageBTN;

	/** The help BTN. */
	@FXML
	private Button helpBTN;

	/** The logout BTN. */
	@FXML
	private Button logoutBTN;

	/**
	 * Mouse click event, if "Management" button clicked, open the screen of
	 * "Employees management".
	 *
	 * @param event The Management BTN
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void goEmployeesMang(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITManagerEmployeesManagment");
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
		MainAllControllers.setWindowVar("ITManagerHelp");
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
		MainAllControllers.setWindowVar("ITManagerHome");
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
		MainAllControllers.setWindowVar("ITManagerPersonalInfo");
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
		MainAllControllers.setWindowVar("ITManagerShowRequests");
		MainAllControllers.changeWin();
	}

	/**
	 * Mouse click event, if "Reports" button clicked, open the screen of "Reports".
	 *
	 * @param event The Reports BTN
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void gogenerateReport(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITManagerReports");
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
		MainAllControllers.setWindowVar("ITManagerMessages");
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
}
