package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Enums.MessageType;
import entity.DBmessage;
import entity.ITemployee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * IT manager employees management screen Controller . This This window is in IT
 * Manager GUI and display table with all employees and IT Manager can change
 * between them
 *
 * @author SHIRA
 */
public class ITManagerEmployeesManagmentController implements Initializable {

	/** The Main all controllers. */
	private MainAllControllers MainAllControllers;

	/** The rows. */
	private ObservableList<ITemployee> rows;

	/**
	 * Instantiates a new IT manager employees managment controller.
	 */
	public ITManagerEmployeesManagmentController() {
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

	/** The Message BTN. */
	@FXML
	private Button MessageBTN;

	/** The personal info BTN. */
	@FXML
	private Button personalInfoBTN;

	/** The help BTN. */
	@FXML
	private Button helpBTN;

	/** The logout BTN. */
	@FXML
	private Button logoutBTN;

	/** The change pos. */
	@FXML
	private Button changePos;

	/** The combo. */
	@FXML
	private ChoiceBox<String> combo;

	/** The Employees table. */
	@FXML
	private TableView<ITemployee> EmployeesTable;

	/** The employee id. */
	@FXML
	private TableColumn<ITemployee, Integer> employeeId;

	/** The employee name. */
	@FXML
	private TableColumn<ITemployee, String> employeeName;

	/** The employee last name. */
	@FXML
	private TableColumn<ITemployee, String> employeeLastName;

	/** The employee mail. */
	@FXML
	private TableColumn<ITemployee, String> employeeMail;

	/** The employee pos. */
	@FXML
	private TableColumn<ITemployee, String> employeePos;

	/** The submit chnage. */
	@FXML
	private Label submitChnage;

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
	 * This method crates a new DBmsg to send to server to show all of the employees
	 * 
	 */
	public void requestServer() {
		DBmessage dbm;
		dbm = new DBmessage(MessageType.ShowEmployeeList, null);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {
		}
	}

	/**
	 * This method sets the employees details on table
	 *
	 * @param list the new text in table
	 */
	public void setTextInTable(ArrayList<Object> list) {
		rows = FXCollections.observableArrayList();
		for (Object r : list)
			rows.add((ITemployee) r);
		EmployeesTable.setItems(rows);
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
	 * This method change the position of the employee according to the IT Manager
	 * choose
	 *
	 * @param event The Change position BTN
	 */
	@FXML
	void changep(MouseEvent event) {
		ArrayList<Object> arry = new ArrayList<Object>();
		DBmessage dbm;
		String nameIT = EmployeesTable.getItems().get(EmployeesTable.getSelectionModel().getSelectedIndex())
				.getEmployeeName();
		String pos = combo.getValue();
		arry.add(nameIT);
		arry.add(pos);
		dbm = new DBmessage(MessageType.SwitchPositions, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {
		}
	}

	/**
	 * Sets the label visible.
	 */
	public void setVisable() {
		submitChnage.setVisible(true);
	}

	/**
	 * Initializes GUI components before this window open.
	 *
	 * @param arg0 the arg 0
	 * @param arg1 the arg 1
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		combo.getItems().add("superviser");
		combo.getItems().add("CEO");
		combo.getItems().add("CC");
		employeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
		employeeName.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
		employeeLastName.setCellValueFactory(new PropertyValueFactory<>("employeeLastName"));
		employeeMail.setCellValueFactory(new PropertyValueFactory<>("employeeMail"));
		employeePos.setCellValueFactory(new PropertyValueFactory<>("employeePos"));
		requestServer();

	}

}
