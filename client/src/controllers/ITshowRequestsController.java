package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Enums.MessageType;
import Enums.StageName;
import entity.DBmessage;
import entity.RequestUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * The Class ITshowRequestsController. This window is in IT GUI and show a table
 * with the requests
 * 
 * @author SHIRA
 */
public class ITshowRequestsController implements Initializable {

	/** The rows. */
	private ObservableList<RequestUser> rows;

	/** The Main all controllers. */
	private MainAllControllers MainAllControllers;

	/**
	 * Instantiates a new i tshow requests controller.
	 */
	public ITshowRequestsController() {
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

	/** The User name. */
	@FXML
	private Label UserName;

	/** The email. */
	@FXML
	private Label email;

	/** The position. */
	@FXML
	private Label position;

	/** The request table. */
	@FXML
	private TableView<RequestUser> requestTable;

	/** The id. */
	@FXML
	private TableColumn<RequestUser, Integer> id;

	/** The current status. */
	@FXML
	private TableColumn<RequestUser, String> currentStatus;

	/** The current stage. */
	@FXML
	private TableColumn<RequestUser, StageName> currentStage;

	/** The time left. */
	@FXML
	private TableColumn<RequestUser, Integer> timeLeft;

	/** The search. */
	@FXML
	private Button search;

	/** The request id to. */
	@FXML
	private TextField requestIdTo;
	@FXML
	private Label susNotAllowed;

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
	 * This method send to server request to search request based on id that IT
	 * entered.
	 *
	 * @param event The search BTN
	 */
	@FXML
	void searchRequest(MouseEvent event) {
		if (requestIdTo.getText().equals("")) {
			try {
				MainAllControllers.setWindowVar("ITshowRequests");
			} catch (IOException e) {
				e.printStackTrace();
			}
			MainAllControllers.changeWin();
		} else {
			ArrayList<Object> arry = new ArrayList<Object>();
			arry.add(MainAllControllers.user.getName());
			arry.add(Integer.parseInt(requestIdTo.getText()));
			DBmessage dbm;
			dbm = new DBmessage(MessageType.SearchReqIT, arry);
			try {
				MainAllControllers.sendToAbsServer(dbm);
			} catch (IOException e) {
			}
		}
	}

	/**
	 * If double click on request from the requests table pressed , The details of
	 * this request will appear
	 *
	 * @param event Double click on request from requests table
	 */
	@FXML
	void showRequestDetails(MouseEvent event) {
		requestTable.setOnMouseClicked((MouseEvent ev) -> {
			if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
				if (!requestTable.getItems().get(requestTable.getSelectionModel().getSelectedIndex()).getCurrentStatus()
						.equals("Suspend")) {
					try {

						MainAllControllers.request = requestTable.getItems()
								.get(requestTable.getSelectionModel().getSelectedIndex()).getId();
						MainAllControllers.setWindowVar("ITHandleRequest");
						MainAllControllers.changeWin();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else
					susNotAllowed.setVisible(true);
			}
		});
	}

	@FXML
	/**
	 * This method set the label visible
	 * 
	 * @param event The BTN
	 */
	void visableSus(MouseEvent event) {
		susNotAllowed.setVisible(false);
	}

	/**
	 * This method sets the text in table.
	 *
	 * @param list the new text in table
	 */
	public void setTextInTable(ArrayList<Object> list) {
		rows = FXCollections.observableArrayList();
		for (Object r : list)
			rows.add((RequestUser) r);
		requestTable.setItems(rows);
	}

	/**
	 * Initializes GUI components before this window open.
	 *
	 * @param location  the location
	 * @param resources the resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		currentStatus.setCellValueFactory(new PropertyValueFactory<>("currentStatus"));
		currentStage.setCellValueFactory(new PropertyValueFactory<>("currentStage"));
		timeLeft.setCellValueFactory(new PropertyValueFactory<>("timeLeft"));
		requestServer();
	}

	/**
	 * This method crates a new DBmsg to send to server to show the IT all of his
	 * request that are active and closed
	 */
	public void requestServer() {
		ArrayList<Object> arry = new ArrayList<Object>();
		arry.add(MainAllControllers.user.getName());
		DBmessage dbm;
		dbm = new DBmessage(MessageType.ShowReqIT, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {
		}
	}

	/**
	 * This method clear the table.
	 */
	public void clearTable() {
		requestTable.getItems().clear();
	}

	/**
	 * This method gets the requests table.
	 *
	 * @return the requests table
	 */
	public TableView<RequestUser> getRequestsTable() {
		return requestTable;
	}

}
