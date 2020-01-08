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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SupervisorShowRequestsController implements Initializable {
	private ObservableList<requestSuper> rows;
	private MainAllControllers MainAllControllers;

	public SupervisorShowRequestsController() {
		MainAllControllers = controllers.MainAllControllers.getInstance();
	}

	@FXML
	private Button homeBTN;

	@FXML
	private Button showreBTN;

	@FXML
	private Button personBTN;

	@FXML
	private Button helpBTN;

	@FXML
	private Button logoutBTN;

	@FXML
	private TableView<requestSuper> requestTable;

	@FXML
	private TableColumn<requestSuper, Integer> RequestID;

	@FXML
	private TableColumn<requestSuper, String> RequestStatus;

	@FXML
	private TableColumn<requestSuper, String> RequestProcessStage;

	@FXML
	private Button SuprvisorUpdateRequestBTN;

	@FXML
	private Button SuprvisorCloseRequestBTN;

	@FXML
	private Button SuprvisorSuspendRequestBTN;

	@FXML
	private Button SuprvisorExtensionRequestBTN;

	@FXML
	private Button MessageBTN;

	@FXML
	private Button search;
	@FXML
	private Button SuprvisorTime;
	@FXML
	private TextField requestIdTo;
	@FXML
	private Label susNot;

	@FXML
	private Label closeNot;
	@FXML
	private Label stagenotmatch;

	@FXML
	void closeRequest(MouseEvent event) {
		Stage popupwindow = new Stage();
		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Alert pop up");
		Label label1 = new Label("Are you sure you want to close request?");
		label1.setFont(new Font("Arial", 14));
		Button button1 = new Button("Yes");
		Button button2 = new Button("No");
		button1.addEventHandler(ActionEvent.ACTION, (e) -> popupwindow.close());
		button1.addEventHandler(ActionEvent.ACTION, (e) -> {
			if (requestTable.getItems().get(requestTable.getSelectionModel().getSelectedIndex()).getCurrentStatus()
					.contentEquals("WaitingCloser")) {
				String x = requestTable.getId();

				this.closeRequest(
						requestTable.getItems().get(requestTable.getSelectionModel().getSelectedIndex()).getId());
			} else
				closeNot.setVisible(true);
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

	@FXML
	void visNot(MouseEvent event) {
		closeNot.setVisible(false);
		susNot.setVisible(false);
	}

	@FXML
	void goHelpPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("SupervisorHelp");
		MainAllControllers.changeWin();
	}

	@FXML
	void goHomePage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("SupervisorHome");
		MainAllControllers.changeWin();
	}

	@FXML
	void goLogoutPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("login");
		MainAllControllers.changeWin();
		MainAllControllers.logOutUser();
	}

	@FXML
	void goPersonalPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("SupervisorPersonalInfo");
		MainAllControllers.changeWin();
	}

	@FXML
	void goShowReqPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("SupervisorShowRequests");
		MainAllControllers.changeWin();
	}

	@FXML
	void messagePage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("SupervisorMessages");
		MainAllControllers.changeWin();
	}

	@FXML
	void viewTime(MouseEvent event) throws IOException {
		ArrayList<Object> arry = new ArrayList<Object>();
		int idadd = requestTable.getItems().get(requestTable.getSelectionModel().getSelectedIndex()).getId();
		DBmessage dbm;
		arry.add(idadd);
		dbm = new DBmessage(MessageType.approveTime, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {
		}

	}

	public void setTimeWindow(ArrayList<Object> list) throws IOException {
		if(list.get(1).equals(1))
		{
			MainAllControllers.request = (int)list.get(0);
			MainAllControllers.setWindowVar("SupervisorTimeRequest");
			MainAllControllers.changeWin();
		}
		else if(list.get(1).equals(0))
		{
			stagenotmatch.setVisible(true);
		}
	}

	@FXML
	void suspendRequest(MouseEvent event) {
		Stage popupwindow = new Stage();
		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Alert pop up");
		Label label1 = new Label("Are you sure you want to suspend the request?");
		label1.setFont(new Font("Arial", 14));
		Button button1 = new Button("Yes");
		Button button2 = new Button("No");
		button1.addEventHandler(ActionEvent.ACTION, (e) -> popupwindow.close());
		button1.addEventHandler(ActionEvent.ACTION, (e) -> {
			if (requestTable.getSelectionModel().getSelectedIndex() == -1)
				susNot.setVisible(true);
			else if (requestTable.getItems().get(requestTable.getSelectionModel().getSelectedIndex()).getCurrentStatus()
					.contentEquals("Active")) {

				this.suspendRequest(
						requestTable.getItems().get(requestTable.getSelectionModel().getSelectedIndex()).getId());
			} else
				susNot.setVisible(true);
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

	private void suspendRequest(int id) {
		ArrayList<Object> arry = new ArrayList<Object>();
		int idadd = id;
		DBmessage dbm;
		arry.add(idadd);
		dbm = new DBmessage(MessageType.suspendRequest, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {
		}

	}

	private void closeRequest(int id) {

		ArrayList<Object> arry = new ArrayList<Object>();
		int idadd = id;
		DBmessage dbm;
		arry.add(idadd);
		dbm = new DBmessage(MessageType.closeRequest, arry);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {
		}

	}

	@FXML
	void viewExtensionReport(MouseEvent event) throws IOException {
		try {
			MainAllControllers.request = requestTable.getItems()
					.get(requestTable.getSelectionModel().getSelectedIndex()).getId();
			MainAllControllers.setWindowVar("SupervisorExtentionRequest");
			MainAllControllers.changeWin();
		} catch (Exception e) {
		}
	}

	@FXML
	void UpdateRequest(MouseEvent event) throws IOException {
		try {
			if (requestTable.getItems().get(requestTable.getSelectionModel().getSelectedIndex()).getCurrentStage()
					.equals("supervisorApprovel")) {
				MainAllControllers.nowWin = "ITRequestSuperviser";
				MainAllControllers.request = requestTable.getItems()
						.get(requestTable.getSelectionModel().getSelectedIndex()).getId();
				MainAllControllers.setWindowVar("SupervisorUpdateRequest");
				MainAllControllers.changeWin();
			} else
				stagenotmatch.setVisible(true);

		} catch (Exception e) {

		}

	}

	@FXML
	void searchRequest(MouseEvent event) {
		if (requestIdTo.getText().equals("")) {
			try {
				MainAllControllers.setWindowVar("SupervisorShowRequests");
			} catch (IOException e) {
				e.printStackTrace();
			}
			MainAllControllers.changeWin();
		} else {
			ArrayList<Object> arry = new ArrayList<Object>();
			arry.add(Integer.parseInt(requestIdTo.getText()));
			DBmessage dbm;
			dbm = new DBmessage(MessageType.SearchReqSupervisor, arry);
			try {
				MainAllControllers.sendToAbsServer(dbm);
			} catch (IOException e) {
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		RequestID.setCellValueFactory(new PropertyValueFactory<>("id"));
		RequestStatus.setCellValueFactory(new PropertyValueFactory<>("currentStatus"));
		RequestProcessStage.setCellValueFactory(new PropertyValueFactory<>("currentStage"));
		requestServer();

	}

	public void requestServer() {
		DBmessage dbm;
		dbm = new DBmessage(MessageType.superviserRequestShow, null);
		try {
			MainAllControllers.sendToAbsServer(dbm);
		} catch (IOException e) {
		}
	}

	public void clearTable() {
		requestTable.getItems().clear();
	}

	public void setTextInTable(ArrayList<Object> list) {
		rows = FXCollections.observableArrayList();
		for (Object r : list)
			rows.add((requestSuper) r);
		requestTable.setItems(rows);
	}

	public void setvisable() {
		stagenotmatch.setVisible(true);

	}

}
