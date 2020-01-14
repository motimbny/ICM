package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import Enums.MessageType;
import entity.DBmessage;
import entity.extensionrequest;
import entity.updateRequest;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * The Class SupervisorUpdateRequestController. This window is in Supervisor GUI
 * and opened after press on Update request BTN
 * 
 * @author SHIRA
 */
public class SupervisorUpdateRequestController implements Initializable {

	/** The Main all controllers. */
	private MainAllControllers MainAllControllers;

	/** The list. */
	private ArrayList<Object> list;

	/**
	 * Instantiates a new supervisor update request controller.
	 */
	public SupervisorUpdateRequestController() {
		MainAllControllers = controllers.MainAllControllers.getInstance();
	}

	/** The home BTN. */
	@FXML
	private Button homeBTN;

	/** The show request BTN. */
	@FXML
	private Button showreBTN;

	/** The person BTN. */
	@FXML
	private Button personBTN;

	/** The help BTN. */
	@FXML
	private Button helpBTN;

	/** The Message BTN. */
	@FXML
	private Button MessageBTN;

	/** The logout BTN. */
	@FXML
	private Button logoutBTN;

	/** The request info BTN. */
	@FXML
	private Button requestInfoBTN;

	/** The save update req. */
	@FXML
	private Button saveUpdateReq;

	/** The request ID. */
	@FXML
	private TextField requestID;

	/** The change apprieser BTN. */
	@FXML
	private Button changeApprieserBTN;

	/** The apprieser name. */
	@FXML
	private TextField apprieserName;

	/** The change executer BTN. */
	@FXML
	private Button changeExecuterBTN;

	/** The executer name. */
	@FXML
	private TextField executerName;

	/** The approve apprieser BTN. */
	@FXML
	private Button approveApprieserBTN;

	/** The evluation. */
	@FXML
	private TextField evluation;

	/** The execution. */
	@FXML
	private TextField execution;

	/** The examination. */
	@FXML
	private ComboBox<Integer> examination;

	/** The test. */
	@FXML
	private ComboBox<Integer> test;

	/** The Back to show. */
	@FXML
	private Button BackToShow;
	@FXML
	private Label labelUpdate;

	@FXML
	private Button updateBtn;

	private String currentStage;

	/**
	 * Mouse click event, if "Back" button clicked, open the screen of "Show
	 * requests".
	 *
	 * @param event The Back BTN
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void BackToS(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("SupervisorShowRequests");
		MainAllControllers.changeWin();
	}

	/**
	 * Mouse click event, if "Request details" button clicked, open the screen of
	 * "Request details".
	 *
	 * @param event The Back BTN
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void requestInfoWindow(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("SupervisorRequestDetalies");
		MainAllControllers.changeWin();
	}

	/**
	 * Mouse click event, if "Back" button clicked, open the screen of "Show
	 * requests".
	 *
	 * @param event The Back BTN
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
	 * @param event the event
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
	 * if Change appriser.
	 *
	 * @param event the event
	 */
	@FXML
	void changeApprieser(MouseEvent event) {
		listOfIt();
		showListOfIT("itAppraiser");
	}

	/**
	 * Change executer.
	 *
	 * @param event the event
	 */
	@FXML
	private void changeExecuter(MouseEvent event) {
		listOfIt();
		showListOfIT("itPerformanceLeader");

	}

	/**
	 * This method create new DBmessage to send to server and update the changes
	 * 
	 * @param event 
	 * @throws IOException
	 */
	@FXML
	void UpdateChange(MouseEvent event) throws IOException {
		int id = (Integer.parseInt(requestID.getText()));
		ArrayList<Object> arry = new ArrayList<Object>();
		DBmessage dbm;
		arry.add(id);
		arry.add(executerName.getText());
		arry.add(apprieserName.getText());
		arry.add(MainAllControllers.user.getName());
		dbm = new DBmessage(MessageType.changeExecuter2, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {
		}
		goShowReqPage(event);
	}

	/**
	 * This method show the list of IT and set the position after change in the
	 * table
	 *
	 * @param NameOfPositionChange the name of position change
	 */
	private void showListOfIT(String NameOfPositionChange) {
		Stage popupwindow = new Stage();
		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("List of IT");
		Label label1 = new Label("Please choose " + NameOfPositionChange + ":");
		label1.setFont(new Font("Arial", 16));
		TableView<String> EmployeesTable = new TableView<>();
		TableColumn<String, String> employeeName = new TableColumn<>();
		EmployeesTable.getColumns().addAll(employeeName);
		Collection<String> rows = new ArrayList<>();
		employeeName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
		while (list == null) {
		}
		for (Object r : list)
			rows.add(r.toString());
		ObservableList<String> details = FXCollections.observableArrayList(rows);
		EmployeesTable.setItems(details);
		EmployeesTable.setOnMouseClicked((MouseEvent ev) -> {
			switch (NameOfPositionChange) {
			case "itAppraiser": {
				apprieserName.setText(EmployeesTable.getSelectionModel().getSelectedItem());
			}
				break;

			case "itPerformanceLeader": {
				executerName.setText(EmployeesTable.getSelectionModel().getSelectedItem());
			}
				break;

			default:
				break;
			}
			popupwindow.close();
		});
		VBox layout = new VBox(10);
		layout.getStylesheets().add("CSS/it.css");
		layout.getChildren().addAll(label1, EmployeesTable);
		layout.setAlignment(Pos.CENTER);
		Scene scene1 = new Scene(layout, 350, 250);
		popupwindow.setScene(scene1);
		popupwindow.showAndWait();
	}

	/**
	 * This method create new DBmessage to send to server and update the changes
	 */
	private void changeExecuter() {
		int id = (Integer.parseInt(requestID.getText()));
		ArrayList<Object> arry = new ArrayList<Object>();
		DBmessage dbm;
		arry.add(id);
		arry.add(executerName.getText());
		arry.add(apprieserName.getText());
		dbm = new DBmessage(MessageType.changeExecuter, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {
		}
	}

	/**
	 * This method set the changes to relevant fields.
	 *
	 * @param list the new to fields
	 */
	public void setToFields(ArrayList<Object> list) {
		this.requestID.setText("" + list.get(0));
		this.apprieserName.setText((String) list.get(1));
		this.executerName.setText((String) list.get(2));
		this.currentStage = (String) list.get(3);
		if (!this.currentStage.equals("supervisorApprovel")) {
			updateBtn.setVisible(true);
			saveUpdateReq.setVisible(false);
		}
	}

	/**
	 * 
	 * Initializes GUI components before this window open.
	 * 
	 * @param arg0 the arg 0
	 * @param arg1 the arg 1
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		DBmessage dbm;
		ArrayList<Object> arry = new ArrayList<Object>();
		arry.add(MainAllControllers.request);
		dbm = new DBmessage(MessageType.SupervisorUpdateRequest, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {
		}
	}

	/**
	 * This method crates a new DBmsg to send to server of list of IT
	 * 
	 */
	public void listOfIt() {
		DBmessage dbm;
		ArrayList<Object> arry = new ArrayList<Object>();
		arry.add(MainAllControllers.request);
		dbm = new DBmessage(MessageType.SupervisorListIt, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {
		}
	}

	/**
	 * This method sets a list of IT.
	 *
	 * @param arrayList the new list of IT
	 */
	public void setListOfIT(ArrayList<Object> arrayList) {
		this.list = arrayList;
	}

	/**
	 * This method save the changes.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void SaveChanges(MouseEvent event) throws IOException {
		changeExecuter();
		goShowReqPage(event);
	}
}
