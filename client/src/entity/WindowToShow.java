package entity;

import java.io.IOException;

import controllers.ITCCCEvaluationReportController;
import controllers.ITCCCRequestMoreInfoController;
import controllers.ITExtensionRequestController;
import controllers.ITHandleRequestController;
import controllers.ITHelpController;
import controllers.ITHomeController;
import controllers.ITMeaningAssessmentEvaluationReportController;
import controllers.ITPersonalInfoController;
import controllers.ITRequestDetailsController;
import controllers.ITTestFailurReportController;
import controllers.ITshowRequestsController;
import controllers.LoginController;
import controllers.SupervisorEmployeesManagmentController;
import controllers.SupervisorExtentionRequestController;
import controllers.SupervisorHelpController;
import controllers.SupervisorHomeController;
import controllers.SupervisorPersonalInfoController;
import controllers.SupervisorShowRequestsController;
import controllers.UserAddRequestController;
import controllers.UserHelpController;
import controllers.UserHomeController;
import controllers.UserPersonalInfoController;
import controllers.UserRequestDetailsController;
import controllers.UserShowRequestsController;

public class WindowToShow
{
	public Object controller;
	private String pathfxml, windowName;

	public void setWindowToShow(String str) throws IOException
	{
		switch (str) {
		case "login":
			initLogin();
			break; 
		case "userHome":
			initUserHome();
			break;
		case "UserShowRequests":
			initUserShowRequests();
			break;
		case "UserRequestDetails":
			initUserRequestDetails();
			break;
		case "UserAddRequest":
			initUserAddRequest();
			break;
		case "UserPersonalInfo":
			initUserPersonalInfo();
			break;
		case "UserHelp":
			initUserHelp();
			break;
		case "SupervisorHome":
			initSupervisorHome();
			break;
		case "SupervisorShowRequests":
			initSupervisorShowRequests();
			break;
		case "SupervisorHelp":
			initSupervisorHelp();
			break;
		case "SupervisorPersonalInfo":
			initSupervisorPersonalInfo();
			break;
		case "SupervisorEmployeesManagment":
			initSupervisorEmployeesManagment();
			break;
		case "ITHome":
			initITHome();
			break;
		case "ITPersonalInfo":
			initITPersonalInfo();
			break;
		case "ITHelp":
			initITHelp();
			break;
		case "ITshowRequests":
			initITshowRequests();
			break;	
		case "ITHandleRequest":
			initITHandleRequest();
			break;
		case "ITTestFailurReport":
			initITTestFailurReport();
			break;
		case "ITCCCEvaluationReport":
			initITCCCEvaluationReport();
			break;
		case "ITCCCRequestMoreInfo":
			initITCCCRequestMoreInfo();
			break;
		case "ITExtensionRequest":
			initITExtensionRequest();
			break;
		case "ITMeaningAssessmentEvaluationReport":
			initITMeaningAssessmentEvaluationReport();
			break;
		case "ITRequestDetails":
			initITRequestDetails();
			break;
			
		
		}
	}	
	public String getPathfxml() {
		return pathfxml;
	}

	public String getWindowName() {
		return windowName;
	}

	// ------------------------------------------------------------------//
	public void initLogin() {
		LoginController controller = new LoginController();
		pathfxml = "/Fxml/Login.fxml";
		windowName = "ICM-Login";
	}

	public void initUserHome() {
		UserHomeController controller = new UserHomeController(); // UserHomeController
		pathfxml = "/Fxml/UserHome.fxml";
		windowName = "ICM-UserHome";
	}

	public void initUserShowRequests() throws IOException {
		UserShowRequestsController controller = new UserShowRequestsController(); // UserShowRequestsController
		pathfxml = "/Fxml/UserShowRequests.fxml";
		windowName = "ICM-UserShowRequests";
	}

	public void initUserAddRequest() {
		UserAddRequestController controller = new UserAddRequestController();
		pathfxml = "/Fxml/UserAddRequest.fxml";
		windowName = "ICM-UserAddRequest";
	}

	private void initUserPersonalInfo() {
		UserPersonalInfoController controller = new UserPersonalInfoController();
		pathfxml = "/Fxml/UserPersonalInfo.fxml";
		windowName = "ICM-UserPersonalInfo";

	}
	
	private void initUserRequestDetails() {
		UserRequestDetailsController controller = new UserRequestDetailsController(); // UserRequestDetailsController
		pathfxml = "/Fxml/UserRequestDetails.fxml";
		windowName = "ICM-UserRequestDetails";
	}

	private void initUserHelp() {
		UserHelpController controller = new UserHelpController(); // UserAddRequestController
		pathfxml = "/Fxml/UserHelp.fxml";
		windowName = "ICM-UserHelp";

	}

	public void initSupervisorHome() {
		SupervisorHomeController controller = new SupervisorHomeController(); // SupervisorHomeController
		pathfxml = "/Fxml/SupervisorHome.fxml";
		windowName = "ICM-SupervisorHome";
	}

	public void initSupervisorShowRequests() throws IOException {
		SupervisorShowRequestsController controller = new SupervisorShowRequestsController(); // SupervisorShowRequestsController
		pathfxml = "/Fxml/SupervisorShowRequests.fxml";
		windowName = "ICM-SupervisorShowRequests";
	}


	public void initSupervisorHelp() {
		SupervisorHelpController controller = new SupervisorHelpController(); // SupervisorHelpController
		pathfxml = "/Fxml/SupervisorHelp.fxml";
		windowName = "ICM-SupervisorHelp";
	}

	public void initSupervisorPersonalInfo() {
		SupervisorPersonalInfoController controller = new SupervisorPersonalInfoController(); // SupervisorPersonalInfoController
		pathfxml = "/Fxml/SupervisorPersonalInfo.fxml";
		windowName = "ICM-SupervisorPersonalInfo";
	}

	public void initSupervisorEmployeesManagment() {
		SupervisorEmployeesManagmentController controller = new SupervisorEmployeesManagmentController(); // SupervisorEmployeesManagmentController
		pathfxml = "/Fxml/SupervisorEmployeesManagment.fxml";
		windowName = "ICM-SupervisorEmployeesManagment";
	}

	public void initSupervisorExtentionRequest() {
		SupervisorExtentionRequestController controller = new SupervisorExtentionRequestController(); // SupervisorExtentionRequestController
		pathfxml = "/Fxml/SupervisorExtentionRequest.fxml";
		windowName = "ICM-SupervisorExtentionRequest";
	}

	public void initITHome() {
		ITHomeController controller = new ITHomeController();
		pathfxml = "/Fxml/ITHome.fxml";
		windowName = "ICM-ITHome";
	}

	public void initITPersonalInfo() {
		ITPersonalInfoController controller = new ITPersonalInfoController();
		pathfxml = "/Fxml/ITPersonalInfo.fxml";
		windowName = "ICM-ITPersonalInfo";
	}
	
	public void initITHelp() {
		ITHelpController controller = new ITHelpController();
		pathfxml = "/Fxml/ITHelp.fxml";
		windowName = "ICM-ITHelp";
	}
	
	public void initITshowRequests()
	{
		ITshowRequestsController controller = new ITshowRequestsController();
		pathfxml = "/Fxml/ITshowRequest.fxml";
		windowName = "ICM-ITshowRequests";
	}
	
	public void initITTestFailurReport()
	{
		ITTestFailurReportController controller = new ITTestFailurReportController();
		pathfxml = "/Fxml/ITTestFailurReport.fxml";
		windowName = "ICM-ITTestFailurReport";	
	}
	
	public void initITCCCEvaluationReport()
	{
		ITCCCEvaluationReportController controller = new ITCCCEvaluationReportController();
		pathfxml = "/Fxml/ITCCCEvaluationReport.fxml";
		windowName = "ICM-ITCCCEvaluationReport";
	}
	
	public void initITCCCRequestMoreInfo()
	{
		ITCCCRequestMoreInfoController controller = new ITCCCRequestMoreInfoController();
		pathfxml = "/Fxml/ITCCCRequestMoreInfo.fxml";
		windowName = "ICM-ITCCCRequestMoreInfo";		
	}
	
	public void initITExtensionRequest()
	{
		ITExtensionRequestController controller = new ITExtensionRequestController();
		pathfxml = "/Fxml/ITExtensionRequest.fxml";
		windowName = "ICM-ITExtensionRequest";		
	}
	
	public void initITMeaningAssessmentEvaluationReport()
	{
		ITMeaningAssessmentEvaluationReportController controller = new ITMeaningAssessmentEvaluationReportController();
		pathfxml = "/Fxml/ITMeaningAssessmentEvaluationReport.fxml";
		windowName = "ICM-ITMeaningAssessmentEvaluationReport";		
	}
	
	public void initITHandleRequest()
	{
		ITHandleRequestController controller = new ITHandleRequestController();
		pathfxml = "/Fxml/ITHandleRequest.fxml";
		windowName = "ICM-ITHandleRequest";	
	}
	
	public void initITRequestDetails()
	{
		ITRequestDetailsController controller = new ITRequestDetailsController();
		pathfxml = "/Fxml/ITRequestDetails.fxml";
		windowName = "ICM-ITRequestDetails";			
	}
}
