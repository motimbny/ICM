package entity;

import java.io.IOException;

import controllers.ITCCCEvaluationReportController;
import controllers.ITCCCRequestMoreInfoController;
import controllers.ITExtensionRequestController;
import controllers.ITHandleRequestController;
import controllers.ITHelpController;
import controllers.ITHomeController;
import controllers.ITManagerEmployeesManagmentController;
import controllers.ITManagerHelpController;
import controllers.ITManagerHomeController;
import controllers.ITManagerMessagesController;
import controllers.ITManagerPersonalInfoController;
import controllers.ITManagerReportsController;
import controllers.ITManagerRequestDetailsController;
import controllers.ITManagerShowRequestsController;
import controllers.ITMeaningAssessmentEvaluationReportController;
import controllers.ITPersonalInfoController;
import controllers.ITRequestDetailsController;
import controllers.ITTestFailurReportController;
import controllers.ITshowRequestsController;
import controllers.LoginController;
import controllers.SupervisorExtensionRequestController;
import controllers.SupervisorHelpController;
import controllers.SupervisorHomeController;
import controllers.SupervisorMessagesController;
import controllers.SupervisorPersonalInfoController;
import controllers.SupervisorRequestDetailsController;
import controllers.SupervisorShowRequestsController;
import controllers.SupervisorTimeRequestController;
import controllers.SupervisorUpdateRequestController;
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
		case "SupervisorUpdateRequest":
			initSupervisorUpdateRequest();
			break;
		case "SupervisorPersonalInfo":
			initSupervisorPersonalInfo();
			break;
		case "SupervisorExtentionRequest":
			initSupervisorExtentionRequest();
			break;
		case "SupervisorRequestDetalies":
			initSupervisorShowRequestDetailes();
			break;
		case "SupervisorTimeRequest":
	     	initSupervisorTimeRequest();
	     	break;
		case "SupervisorMessages":
	     	initSupervisorMessages();
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
		case "ITManagerHelp":
			initITManagerHelp();
			break;
		case "ITManagerHome":
			initITManagerHome();
			break;
		case "ITManagerPersonalInfo":
			initITManagerPersonalInfo();
			break;
		case "ITManagerShowRequests":
			initITManagerShowRequests();
			break;
		case "ITManagerEmployeesManagment":
			initITManagerEmployeesManagment();
			break;
		case "ITManagerMessages":
			initITManagerMessages();
			break;
		case "ITManagerReports":
			initITManagerReports();
			break;
		case "ITManagerRequestDetails":
			initITManagerRequestDetails();
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
	private void initSupervisorTimeRequest()
	{
		SupervisorTimeRequestController controller = new SupervisorTimeRequestController(); // SupervisorHomeController
		pathfxml = "/Fxml/SuperviserTimeManage.fxml";
		windowName = "ICM-SupervisorRequestTime";
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

	public void initSupervisorShowRequestDetailes() throws IOException {
		SupervisorRequestDetailsController controller = new SupervisorRequestDetailsController(); // SupervisorShowRequestsController
		pathfxml = "/Fxml/SupervisorRequestDetails.fxml";
		windowName = "ICM-SupervisorRequestDetails";
	}
	public void initSupervisorHelp() {
		SupervisorHelpController controller = new SupervisorHelpController(); // SupervisorHelpController
		pathfxml = "/Fxml/SupervisorHelp.fxml";
		windowName = "ICM-SupervisorHelp";
	}
	public void initSupervisorUpdateRequest() {
		SupervisorUpdateRequestController controller = new SupervisorUpdateRequestController(); // SupervisorUpdateRequestController
		pathfxml = "/Fxml/SupervisorUpdateRequest.fxml";
		windowName = "ICM-SupervisorUpdateRequest";
	}

	public void initSupervisorPersonalInfo() {
		SupervisorPersonalInfoController controller = new SupervisorPersonalInfoController(); // SupervisorPersonalInfoController
		pathfxml = "/Fxml/SupervisorPersonalInfo.fxml";
		windowName = "ICM-SupervisorPersonalInfo";
	}

	public void initSupervisorExtentionRequest() {
		SupervisorExtensionRequestController controller = new SupervisorExtensionRequestController(); // SupervisorExtentionRequestController
		pathfxml = "/Fxml/SupervisorExtentionRequest.fxml";
		windowName = "ICM-SupervisorExtentionRequest";
	}
	
	private void initSupervisorMessages() {
		SupervisorMessagesController controller = new SupervisorMessagesController(); // SupervisorHelpController
		pathfxml = "/Fxml/SupervisorMessages.fxml";
		windowName = "ICM-SupervisorMessages";
		
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
	private void initITManagerMessages() {
		ITManagerMessagesController controller = new ITManagerMessagesController();
		pathfxml = "/Fxml/ITManagerMessages.fxml";
		windowName = "ICM-ITManagerMessages";
	}

	private void initITManagerEmployeesManagment() {
		ITManagerEmployeesManagmentController controller = new ITManagerEmployeesManagmentController();
		pathfxml = "/Fxml/ITManagerEmployeesManagment.fxml";
		windowName = "ICM-ITManagerEmployeesManagment";
		
	}

	private void initITManagerShowRequests() {
		ITManagerShowRequestsController controller = new ITManagerShowRequestsController();
		pathfxml = "/Fxml/ITManagerShowRequests.fxml";
		windowName = "ICM-ITManagerShowRequests";	
	}

	private void initITManagerPersonalInfo() {
		ITManagerPersonalInfoController controller = new ITManagerPersonalInfoController();
		pathfxml = "/Fxml/ITManagerPersonalInfo.fxml";
		windowName = "ICM-ITManagerPersonalInfo";	
	}

	private void initITManagerHome() {
		ITManagerHomeController controller = new ITManagerHomeController();
		pathfxml = "/Fxml/ITManagerHome.fxml";
		windowName = "ICM-ITManagerHome";	
	}
	private void initITManagerHelp() {
		ITManagerHelpController controller = new ITManagerHelpController();
		pathfxml = "/Fxml/ITManagerHelp.fxml";
		windowName = "ICM-ITManagerHelp";		
	}
	private void initITManagerReports() {
		ITManagerReportsController controller = new ITManagerReportsController();
		pathfxml = "/Fxml/ITManagerReports.fxml";
		windowName = "ICM-ITManagerReports";
	}
	private void initITManagerRequestDetails() {
		ITManagerRequestDetailsController controller = new ITManagerRequestDetailsController();
		pathfxml = "/Fxml/ITManagerRequestDetails.fxml";
		windowName = "ICM-ITManagerRequestDetails";
		
	}
}
