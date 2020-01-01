package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Enums.MessageType;
import Enums.Position;
import entity.DBmessage;
import entity.Request;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class ITHandleRequestController implements Initializable {
	private MainAllControllers MainAllControllers;

	public ITHandleRequestController() {
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
	private Text requestIdField;

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
	private Pane TestStage;

	@FXML
	private Pane EvaluationStage;

	@FXML
	private Pane PerformanceStage;

	@FXML
	private Pane examinationAndDecisionStage;

	@FXML
	void CreateEvaluationReport(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITMeaningAssessmentEvaluationReport");
		MainAllControllers.changeWin();
	}

	@FXML
	void ReportFailure(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITTestFailurReport");
		MainAllControllers.changeWin();
	}

	@FXML
	void ReqForTimeExtension(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITExtensionRequest");
		MainAllControllers.changeWin();
	}

	@FXML
	void ShowRequestDetails(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITRequestDetails");
		MainAllControllers.changeWin();
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
	void ViewEvaluationReport(MouseEvent event) throws IOException {
		MainAllControllers.setWindowVar("ITCCCEvaluationReport");
		MainAllControllers.changeWin();
	}

	@FXML
	void changeCompleted(MouseEvent event) {

	}

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		int s = MainAllControllers.request;
		requestIdField.setText("Request ID: " + s);
		ArrayList<Object> arry = new ArrayList<Object>();
		arry.add(MainAllControllers.user.getName()); //it name
		arry.add(s);  //request id
		DBmessage dbm;
		dbm = new DBmessage(MessageType.ITjobInReq, arry); 
		try {
			MainAllControllers.sendToAbsServer(dbm); 
		} catch (IOException e) {
		}		
	}

	public void setPane(ArrayList<Object> list)
	{
		String job = (String)list.get(0);
		switch(job)
		{
		case "Appraiser":
			EvaluationStage.setVisible(true);
			break;
		case "ControlCommitte":
			examinationAndDecisionStage.setVisible(true);
			break;
		case "PerformanceLeader":
			PerformanceStage.setVisible(true);
			break;
		case "Tester":
			TestStage.setVisible(true);
			break;
		}
	}
}
