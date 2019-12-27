package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class TesterShowRequestsController 
{
	private MainAllControllers MainAllControllers;
	public TesterShowRequestsController()
	{
		MainAllControllers=controllers.MainAllControllers.getInstance();
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
	private TableView<?> requestsTable;

	@FXML
	private TableColumn<?, ?> RequestID;

	@FXML
	private TableColumn<?, ?> RequestDescription;

	@FXML
	private TableColumn<?, ?> PatchMadeOnReq;

	@FXML
	private Button ApproveTesterShowReq;

	@FXML
	private Button FailureTesterShowReq;

	@FXML
	void ApproveRequest(MouseEvent event) {

	}

	@FXML
	void FailureRequest(MouseEvent event) {

	}

	@FXML
	void goHelpPage(MouseEvent event) {

	}

	@FXML
	void goHomePage(MouseEvent event) {

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
    	MainAllControllers.setWindowVar("TesterShowRequests");
    	MainAllControllers.changeWin();
	}

}
