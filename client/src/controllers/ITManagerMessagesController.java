package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Enums.MessageType;
import entity.DBmessage;
import entity.Messages;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * The Class ITManagerMessagesController. This window is in IT Manager GUI and
 * display the messages that sent to the IT Manager.
 */
public class ITManagerMessagesController implements Initializable {

	/** The sentby. */
	String sentby;

	/** The subject. */
	String subject;

	/** The rows. */
	private ObservableList<Messages> rows;

	/** The Main all controllers. */
	private MainAllControllers MainAllControllers;

	/**
	 * Instantiates a new IT manager messages controller.
	 */
	public ITManagerMessagesController() {
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

	/** The Message table. */
	@FXML
	private TableView<Messages> MessageTable;

	/** The Sent by. */
	@FXML
	private TableColumn<Messages, String> SentBy;

	/** The mess subject. */
	@FXML
	private TableColumn<Messages, String> messSubject;

	/** The Message content. */
	@FXML
	private TableColumn<Messages, String> MessageContent;

	/** The Date. */
	@FXML
	private TableColumn<Messages, String> Date;

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
	 * This method update the read or unread message status
	 *
	 * @param event the click on the message
	 */
	@FXML
	void UpdateR(MouseEvent event) {
		MessageTable.setOnMouseClicked((MouseEvent ev) -> {
			if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
				sentby = MessageTable.getItems().get(MessageTable.getSelectionModel().getSelectedIndex()).getSentBy();
				subject = MessageTable.getItems().get(MessageTable.getSelectionModel().getSelectedIndex()).getSubject();
				DBmessage dbm;
				String user;
				user = MainAllControllers.user.getName();
				ArrayList<Object> arry = new ArrayList<Object>();
				arry.add(user);
				arry.add(sentby);
				arry.add(subject);
				dbm = new DBmessage(MessageType.MangerUpdateMessages, arry);
				try {
					MainAllControllers.sendToAbsServer(dbm);
				} catch (IOException e) {
				}
			}
		});
	}

	/**
	 * Show message.
	 *
	 * @param event the event
	 */
	@FXML
	void showMessage(MouseEvent event) {

	}

	/**
	 * Initializes GUI components before this window open. change the font of the
	 * message in table
	 *
	 * @param arg0 the arg 0
	 * @param arg1 the arg 1
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		SentBy.setCellValueFactory(new PropertyValueFactory<>("sentBy"));
		messSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
		MessageContent.setCellValueFactory(new PropertyValueFactory<>("messageCon"));
		Date.setCellValueFactory(new PropertyValueFactory<>("date"));
		MessageTable.setRowFactory(tv -> new TableRow<Messages>() {
			@Override
			public void updateItem(Messages item, boolean empty) {
				super.updateItem(item, empty);
				if (item == null) {
					setStyle("");
				} else if (item.getRead() == 0) {
					setStyle("-fx-font-weight: bold;");
				} else if (item.getRead() == 1) {
					setStyle("");
				}
			}
		});
		requestServer();
	}

	/**
	 * This method crates a new DBmsg to send to server to show all of the IT
	 * Manager messages
	 */
	public void requestServer() {
		DBmessage dbm;
		String user;
		user = MainAllControllers.user.getName();
		ArrayList<Object> arry = new ArrayList<Object>();
		arry.add(user);
		dbm = new DBmessage(MessageType.MangerShowMessages, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {
		}
	}

	/**
	 * This method sets the messages table.
	 *
	 * @param list the new text in table
	 */
	public void setTextTable(ArrayList<Object> list) {
		Messages toad;
		rows = FXCollections.observableArrayList();
		for (Object r : list) {
			rows.add((Messages) r);
		}
		MessageTable.setItems(rows);
	}

}
