package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ITCCCEvaluationReportController 
{
	private MainAllControllers MainAllControllers;
    public ITCCCEvaluationReportController()
    {
    	MainAllControllers=controllers.MainAllControllers.getInstance();
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
	void goHelpPage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITHelp");
    	MainAllControllers.changeWin();
	}

	@FXML
	void goHomePage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITHome");
    	MainAllControllers.changeWin();
	}

	@FXML
	void goPersonalInfo(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITPersonalInfo");
    	MainAllControllers.changeWin();
	}

	@FXML
	void goShowReq(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("ITshowRequests");
    	MainAllControllers.changeWin();
	}

	@FXML
	void logoutPage(MouseEvent event) throws IOException 
	{
    	MainAllControllers.setWindowVar("login");
    	MainAllControllers.changeWin();
    	MainAllControllers.user=null;
	}

    @FXML
    void requireMoreInfo(MouseEvent event) throws IOException 
    {
    	//we need to send request id
    	MainAllControllers.setWindowVar("ITCCCRequestMoreInfo");
    	MainAllControllers.changeWin();
    }

}
