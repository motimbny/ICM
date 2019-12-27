package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class CCEvaluationReportController 
{
	private MainAllControllers MainAllControllers;
	public CCEvaluationReportController()
	{
		MainAllControllers=controllers.MainAllControllers.getInstance();
	}
	
	@FXML
	private Button home;

	@FXML
	private Button showRequest;

	@FXML
	private Button personalInfo;

	@FXML
	private Button Help;

	@FXML
	private Button LogOut;

	@FXML
	private TextField Location;

	@FXML
	private TextArea descriptionOfChangeRequired;

	@FXML
	private TextArea resultOfChange;

	@FXML
	private TextField timeEstimated;

	@FXML
	private TextArea constraintsAndRisks;

	@FXML
	private Button requireMoreInfoBTN;

	@FXML
	private Button DenyCCEvaluationReport;

	@FXML
	private Button ApproveCCEvaluationReport;

	@FXML
	private TextField requestID;

	@FXML
	void approveEvaluationReport(MouseEvent event) {

	}

	@FXML
	void denyEvaluationReport(MouseEvent event) {

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

	@FXML
	void requireMoreInfo(MouseEvent event) {

	}

}
