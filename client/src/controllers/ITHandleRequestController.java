package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ITHandleRequestController 
{
	private MainAllControllers MainAllControllers;
    public ITHandleRequestController()
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
    private TextField ReqID;

    @FXML
    private Button TestApprovalBTN;

    @FXML
    private Button ReqForTimeExtensionBTN;

    @FXML
    private Button ReportFailureBTN;

    @FXML
    private Button ShowRequestDetailsBTN;

    @FXML
    private Button ViewEvaluationReportBTN;

    @FXML
    private Button CreateEvaluationReportBTN;

    @FXML
    private Button ChangePerformanceLeaderShowReq;

    @FXML
    private TextField timeEstimatedPerformance;

    @FXML
    private Button SubmitTimeEstimateBTN;

    @FXML
    private TextField timeEstimatedEvaluation;

    @FXML
    private Button SubmitTimeEstimateBTN1;

    @FXML
    void CreateEvaluationReport(MouseEvent event) throws IOException 
    {
  	  //send req id
    	MainAllControllers.setWindowVar("ITMeaningAssessmentEvaluationReport");
    	MainAllControllers.changeWin();
    }

    @FXML
    void ReportFailure(MouseEvent event) throws IOException 
    {
    	  //send req id
    	MainAllControllers.setWindowVar("ITTestFailurReport");
    	MainAllControllers.changeWin();
    }

    @FXML
    void ReqForTimeExtension(MouseEvent event) throws IOException 
    {
    	  //send req id
    	MainAllControllers.setWindowVar("ITExtensionRequest");
    	MainAllControllers.changeWin();
    }

    @FXML
    void ShowRequestDetails(MouseEvent event) {

    }

    @FXML
    void SubmitTimeEstimateEvaluation(MouseEvent event) {

    }

    @FXML
    void SubmitTimeEstimatePerformance(MouseEvent event) {

    }

    @FXML
    void TestApproval(MouseEvent event) {

    }

    @FXML
    void ViewEvaluationReport(MouseEvent event) throws IOException 
    {
  //send req id
    	MainAllControllers.setWindowVar("ITCCCEvaluationReport");
    	MainAllControllers.changeWin();
    }

    @FXML
    void changeCompleted(MouseEvent event) {

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

}
