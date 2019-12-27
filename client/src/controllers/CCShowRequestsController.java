package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class CCShowRequestsController 
{
	private MainAllControllers MainAllControllers;
	public CCShowRequestsController()
	{
		MainAllControllers=controllers.MainAllControllers.getInstance();
	}
	
	@FXML
	private Button home;

	@FXML
	private Button showRequests;

	@FXML
	private Button personalInfo;

	@FXML
	private Button help;

	@FXML
	private Button logOut;

	@FXML
	private TableView<?> requestsTable;

	@FXML
	private TableColumn<?, ?> RequestID;

	@FXML
	private TableColumn<?, ?> RequestStatus;

	@FXML
	private TableColumn<?, ?> TimeLeft;

	@FXML
	private Button ViewEvaluationReportBTN;

	@FXML
	void ViewEvaluationReport(MouseEvent event) {

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
    	MainAllControllers.setWindowVar("CCShowRequests");
    	MainAllControllers.changeWin();
	}

}
