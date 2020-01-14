package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Enums.MessageType;
import entity.DBmessage;
import entity.requestSuper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * The Class ITManagerShowRequestsController. This window is in IT Manager GUI
 * and show a table with the requests
 */
public class ITManagerShowRequestsController implements Initializable {

	/** The rows. */
	private ObservableList<requestSuper> rows;

	/** The Main all controllers. */
	private MainAllControllers MainAllControllers;

	/**
	 * Instantiates a new IT manager show requests controller.
	 */
	public ITManagerShowRequestsController() {
		MainAllControllers = controllers.MainAllControllers.getInstance();
	}

	/** The home BTN. */
	@FXML
	private Button homeBTN;

	/** The show request BTN 1. */
	@FXML
	private Button showRequestBTN1;

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

	/** The message BTN. */
	@FXML
	private Button messageBTN;

	/** The help BTN. */
	@FXML
	private Button helpBTN;

	/** The logout BTN. */
	@FXML
	private Button logoutBTN;

	/** The request table. */
	@FXML
	private TableView<requestSuper> requestTable;

	/** The Request ID. */
	@FXML
	private TableColumn<requestSuper, Integer> RequestID;

	/** The Request status. */
	@FXML
	private TableColumn<requestSuper, Integer> RequestStatus;

	/** The Request process stage. */
	@FXML
	private TableColumn<requestSuper, Integer> RequestProcessStage;

	/** The Renew request request BTN. */
	@FXML
	private Button RenewRequestRequestBTN;

	/** The search. */
	@FXML
	private Button search;

	/** The request id to. */
	@FXML
	private TextField requestIdTo;

	/** The select R. */
	@FXML
	private Label selectR;

	/** The ok R. */
	@FXML
	private Label okR;

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
	 * After IT Manager choose a request from table and press on Renew BTN this
	 * method check if it possible according to the status of the request and update
	 * the fields
	 *
	 * @param event The renew BTN
	 */
	@FXML
	void renewRequest(MouseEvent event) {
		Stage popupwindow = new Stage();
		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Alert pop up");
		Label label1 = new Label("Are you sure you want to renew the request?");
		label1.setFont(new Font("Arial", 14));
		Button button1 = new Button("Yes");
		Button button2 = new Button("No");
		button1.addEventHandler(ActionEvent.ACTION, (e) -> popupwindow.close());
		button1.addEventHandler(ActionEvent.ACTION, (e) -> {
			if (requestTable.getSelectionModel().getSelectedIndex() == -1)
				selectR.setVisible(true);
			else if (requestTable.getItems().get(requestTable.getSelectionModel().getSelectedIndex()).getCurrentStatus()
					.contentEquals("Suspend")) {

				this.renewRequest(
						requestTable.getItems().get(requestTable.getSelectionModel().getSelectedIndex()).getId());
			} else
				selectR.setVisible(true);
		});
		button2.setOnAction(e -> popupwindow.close());
		button1.setStyle("-fx-border-color:green");
		button2.setStyle("-fx-border-color:red");
		VBox layout = new VBox(10);
		layout.getStylesheets().add("CSS/it.css");
		layout.getChildren().addAll(label1, button1, button2);
		layout.setAlignment(Pos.CENTER);
		Scene scene1 = new Scene(layout, 350, 250);
		popupwindow.setScene(scene1);
		popupwindow.showAndWait();

	}

	/**
	 * This method crate a DBmsg to send to server after renew request is possible
	 *
	 * @param id the request id
	 */
	private void renewRequest(int id) {
		ArrayList<Object> arry = new ArrayList<Object>();
		int idadd = id;
		DBmessage dbm;
		arry.add(idadd);
		dbm = new DBmessage(MessageType.renewRequest, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {
		}
	}

	/**
	 * This method set the label visible
	 *
	 * @param event The click on the request
	 */
	@FXML
	void visable(MouseEvent event) {
		selectR.setVisible(false);
	}

	/**
	 * This method send to server request to search request based on id that IT
	 * Manager entered.
	 *
	 * @param event The search BTN
	 */
	@FXML
	void searchRequest(MouseEvent event) {
		if (requestIdTo.getText().equals("")) {
			try {
				MainAllControllers.setWindowVar("ITManagerShowRequests");
			} catch (IOException e) {
				e.printStackTrace();
			}
			MainAllControllers.changeWin();
		} else {
			ArrayList<Object> arry = new ArrayList<Object>();
			arry.add(Integer.parseInt(requestIdTo.getText()));
			DBmessage dbm;
			dbm = new DBmessage(MessageType.SearchReqManager, arry);
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
				try {
					MainAllControllers.request = requestTable.getItems()
							.get(requestTable.getSelectionModel().getSelectedIndex()).getId();
					MainAllControllers.setWindowVar("ITManagerRequestDetails");
					MainAllControllers.changeWin();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initializes GUI components before this window open.
	 *
	 * @param location  the location
	 * @param resources the resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		RequestID.setCellValueFactory(new PropertyValueFactory<>("id"));
		RequestStatus.setCellValueFactory(new PropertyValueFactory<>("currentStatus"));
		RequestProcessStage.setCellValueFactory(new PropertyValueFactory<>("currentStage"));
		requestServer();

	}

	/**
	 * This method crates a new DBmsg to send to server to show the IT Manager all
	 * of his request that are active and closed
	 */
	public void requestServer() {
		DBmessage dbm;
		dbm = new DBmessage(MessageType.MangerRequestShow, null);
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
	 * This method sets the request details in the rows of the table
	 *
	 * @param list The list of Request user
	 */
	public void setTextTable(ArrayList<Object> list) {
		rows = FXCollections.observableArrayList();
		for (Object r : list)
			rows.add((requestSuper) r);
		requestTable.setItems(rows);
	}
}
