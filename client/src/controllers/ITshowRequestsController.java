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

public class ITshowRequestsController implements Initializable 
{
	private ObservableList<RequestUser> rows;
	private MainAllControllers MainAllControllers;
	public ITshowRequestsController() 
	{
		MainAllControllers = controllers.MainAllControllers.getInstance();
	}

	@FXML
	private Button homeBTN;

	@FXML
	private Button showRequestBTN;

	@FXML
	private Button personalInfoBTN;

	@FXML
	private Button helpBTN;

	@FXML
	private Button logoutBTN;

	@FXML
	private Label UserName;

	@FXML
	private Label email;

	@FXML
	private Label position;

	@FXML
	private TableView<RequestUser> requestTable;

	@FXML
	private TableColumn<RequestUser, Integer> id;

	@FXML
	private TableColumn<RequestUser, String> currentStatus;

	@FXML
	private TableColumn<RequestUser, StageName> currentStage;

	@FXML
	private TableColumn<RequestUser, Integer> timeLeft;

	@FXML
	private Button search;

	@FXML
	private TextField requestIdTo;

	@FXML
	void goHelpPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITHelp");
		MainAllControllers.changeWin();
	}

	@FXML
	void goHomePage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITHome");
		MainAllControllers.changeWin();
	}

	@FXML
	void goPersonalInfo(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITPersonalInfo");
		MainAllControllers.changeWin();
	}

	@FXML
	void goShowReq(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITshowRequests");
		MainAllControllers.changeWin();
	}

	@FXML
	void logoutPage(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("login");
		MainAllControllers.changeWin();
    	MainAllControllers.logOutUser();
	}

	@FXML
	void searchRequest(MouseEvent event) 
	{
		if (requestIdTo.getText().equals("")) 
		{
			try {
				MainAllControllers.setWindowVar("ITshowRequests");
			} catch (IOException e) {
				e.printStackTrace();
			}
			MainAllControllers.changeWin();
		} else 
		{
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

	@FXML
	void showRequestDetails(MouseEvent event) {
		requestTable.setOnMouseClicked((MouseEvent ev) -> {
			if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
				try {
					MainAllControllers.request = requestTable.getItems()
							.get(requestTable.getSelectionModel().getSelectedIndex()).getId();
					MainAllControllers.setWindowVar("ITHandleRequest");
					MainAllControllers.changeWin();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void setTextInTable(ArrayList<Object> list) 
	{
		rows = FXCollections.observableArrayList();
		for (Object r : list)
			rows.add((RequestUser) r);
		requestTable.setItems(rows);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		currentStatus.setCellValueFactory(new PropertyValueFactory<>("currentStatus"));
		currentStage.setCellValueFactory(new PropertyValueFactory<>("currentStage"));
		timeLeft.setCellValueFactory(new PropertyValueFactory<>("timeLeft"));
		requestServer();
	}

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

	public void clearTable() {
		requestTable.getItems().clear();
	}

	public TableView<RequestUser> getRequestsTable() {
		return requestTable;
	}

}
