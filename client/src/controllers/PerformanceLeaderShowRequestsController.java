package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class PerformanceLeaderShowRequestsController 
{
	private MainAllControllers MainAllControllers;
	public PerformanceLeaderShowRequestsController()
	{
		MainAllControllers=controllers.MainAllControllers.getInstance();
	}

	@FXML
	private Button homeBTN;

	@FXML
	private Button showreBTN;

	@FXML
	private Button teamMemberBTN;

	@FXML
	private Button personBTN;

	@FXML
	private Button helpBTN;

	@FXML
	private Button logoutBTN;

	@FXML
	private TableView<?> requestsTable;

	@FXML
	private TableColumn<?, ?> RequestID;

	@FXML
	private TableColumn<?, ?> RequestStatus;

	@FXML
	private TableColumn<?, ?> timeLeft;

	@FXML
	private Button SubmitPerformanceLeaderShowReq;

	@FXML
	private TextField timeEstimated;

	@FXML
	private Button ChangePerformanceLeaderShowReq;

	@FXML
	void changeCompleted(MouseEvent event) {

	}

	@FXML
	void goHelpPage(MouseEvent event) {

	}

	@FXML
	void goHomePage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("PerformanceLeaderHome");
    	MainAllControllers.changeWin();
	}

	@FXML
	void goLogoutPage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("login");
    	MainAllControllers.changeWin();
    	MainAllControllers.user=null;
	}

	@FXML
	void goPersonalPage(MouseEvent event) {

	}

	@FXML
	void goShowReqPage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("PerformanceLeaderShowRequests");
    	MainAllControllers.changeWin();
	}

	@FXML
	void goTeamMember(MouseEvent event) {

	}

	@FXML
	void submitEstimatedTime(MouseEvent event) {

	}
}
