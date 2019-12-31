package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ITshowRequestsController {
	private MainAllControllers MainAllControllers;

	public ITshowRequestsController() {
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
	private TableView<?> requestTable;

	@FXML
	private TableColumn<?, ?> RequestID;

	@FXML
	private TableColumn<?, ?> RequestStatus;

	@FXML
	private TableColumn<?, ?> RequestProcessStage;

	@FXML
	private TableColumn<?, ?> TimeLeft;

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
		MainAllControllers.user = null;
	}

	@FXML
	void searchRequest(MouseEvent event) {

	}

	@FXML
	void showRequestDetails(MouseEvent event) {

	}

}
