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
/**
 * WindowTOShow class initialize the window that are going to appear
 * Make a controller, load the FXML screen and set name to the window.
 * @author SHIRA
 *
 */
public class WindowToShow
{
	
	/** The controller. */
	public Object controller;
	
	/** The window name. */
	private String pathfxml, windowName;

/**
 * Set the initialize method for each screen.
 *
 * @param str is the screen that are going to initialize
 * @throws IOException Signals that an I/O exception has occurred.
 */
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


	/**
	 * Gets the pathfxml.
	 *
	 * @return the pathfxml
	 */
	public String getPathfxml() {
		return pathfxml;
	}

	/**
	 * Gets the window name.
	 *
	 * @return the window name
	 */
	public String getWindowName() {
		return windowName;
	}
	// ------------------------------------------------------------------//
	/**
	 * Inits the login.
	 */
	public void initLogin() {
		LoginController controller = new LoginController();//LoginController
		pathfxml = "/Fxml/Login.fxml";
		windowName = "ICM-Login";
	}

	/**
	 * Inits the user home.
	 */
	public void initUserHome() {
		UserHomeController controller = new UserHomeController(); // UserHomeController
		pathfxml = "/Fxml/UserHome.fxml";
		windowName = "ICM-UserHome";
	}

	/**
	 * Inits the user show requests.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void initUserShowRequests() throws IOException {
		UserShowRequestsController controller = new UserShowRequestsController(); // UserShowRequestsController
		pathfxml = "/Fxml/UserShowRequests.fxml";
		windowName = "ICM-UserShowRequests";
	}

	/**
	 * Inits the user add request.
	 */
	public void initUserAddRequest() {
		UserAddRequestController controller = new UserAddRequestController();
		pathfxml = "/Fxml/UserAddRequest.fxml";
		windowName = "ICM-UserAddRequest";
	}

	/**
	 * Inits the user personal info.
	 */
	private void initUserPersonalInfo() {
		UserPersonalInfoController controller = new UserPersonalInfoController();
		pathfxml = "/Fxml/UserPersonalInfo.fxml";
		windowName = "ICM-UserPersonalInfo";

	}
	
	/**
	 * Inits the user request details.
	 */
	private void initUserRequestDetails() {
		UserRequestDetailsController controller = new UserRequestDetailsController(); // UserRequestDetailsController
		pathfxml = "/Fxml/UserRequestDetails.fxml";
		windowName = "ICM-UserRequestDetails";
	}

	/**
	 * Inits the user help.
	 */
	private void initUserHelp() {
		UserHelpController controller = new UserHelpController(); // UserAddRequestController
		pathfxml = "/Fxml/UserHelp.fxml";
		windowName = "ICM-UserHelp";

	}
	
	/**
	 * Inits the supervisor time request.
	 */
	private void initSupervisorTimeRequest()
	{
		SupervisorTimeRequestController controller = new SupervisorTimeRequestController(); // SupervisorHomeController
		pathfxml = "/Fxml/SuperviserTimeManage.fxml";
		windowName = "ICM-SupervisorRequestTime";
	}

	/**
	 * Inits the supervisor home.
	 */
	public void initSupervisorHome() {
		SupervisorHomeController controller = new SupervisorHomeController(); // SupervisorHomeController
		pathfxml = "/Fxml/SupervisorHome.fxml";
		windowName = "ICM-SupervisorHome";
	}

	/**
	 * Inits the supervisor show requests.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void initSupervisorShowRequests() throws IOException {
		SupervisorShowRequestsController controller = new SupervisorShowRequestsController(); // SupervisorShowRequestsController
		pathfxml = "/Fxml/SupervisorShowRequests.fxml";
		windowName = "ICM-SupervisorShowRequests";
	}

	/**
	 * Inits the supervisor show request detailes.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void initSupervisorShowRequestDetailes() throws IOException {
		SupervisorRequestDetailsController controller = new SupervisorRequestDetailsController(); // SupervisorShowRequestsController
		pathfxml = "/Fxml/SupervisorRequestDetails.fxml";
		windowName = "ICM-SupervisorRequestDetails";
	}
	
	/**
	 * Inits the supervisor help.
	 */
	public void initSupervisorHelp() {
		SupervisorHelpController controller = new SupervisorHelpController(); // SupervisorHelpController
		pathfxml = "/Fxml/SupervisorHelp.fxml";
		windowName = "ICM-SupervisorHelp";
	}
	
	/**
	 * Inits the supervisor update request.
	 */
	public void initSupervisorUpdateRequest() {
		SupervisorUpdateRequestController controller = new SupervisorUpdateRequestController(); // SupervisorUpdateRequestController
		pathfxml = "/Fxml/SupervisorUpdateRequest.fxml";
		windowName = "ICM-SupervisorUpdateRequest";
	}

	/**
	 * Inits the supervisor personal info.
	 */
	public void initSupervisorPersonalInfo() {
		SupervisorPersonalInfoController controller = new SupervisorPersonalInfoController(); // SupervisorPersonalInfoController
		pathfxml = "/Fxml/SupervisorPersonalInfo.fxml";
		windowName = "ICM-SupervisorPersonalInfo";
	}

	/**
	 * Inits the supervisor extention request.
	 */
	public void initSupervisorExtentionRequest() {
		SupervisorExtensionRequestController controller = new SupervisorExtensionRequestController(); // SupervisorExtentionRequestController
		pathfxml = "/Fxml/SupervisorExtentionRequest.fxml";
		windowName = "ICM-SupervisorExtentionRequest";
	}
	
	/**
	 * Inits the supervisor messages.
	 */
	private void initSupervisorMessages() {
		SupervisorMessagesController controller = new SupervisorMessagesController(); // SupervisorHelpController
		pathfxml = "/Fxml/SupervisorMessages.fxml";
		windowName = "ICM-SupervisorMessages";
		
	}

	/**
	 * Inits the IT home.
	 */
	public void initITHome() {
		ITHomeController controller = new ITHomeController();
		pathfxml = "/Fxml/ITHome.fxml";
		windowName = "ICM-ITHome";
	}

	/**
	 * Inits the IT personal info.
	 */
	public void initITPersonalInfo() {
		ITPersonalInfoController controller = new ITPersonalInfoController();
		pathfxml = "/Fxml/ITPersonalInfo.fxml";
		windowName = "ICM-ITPersonalInfo";
	}
	
	/**
	 * Inits the IT help.
	 */
	public void initITHelp() {
		ITHelpController controller = new ITHelpController();
		pathfxml = "/Fxml/ITHelp.fxml";
		windowName = "ICM-ITHelp";
	}
	
	/**
	 * Inits the I tshow requests.
	 */
	public void initITshowRequests()
	{
		ITshowRequestsController controller = new ITshowRequestsController();
		pathfxml = "/Fxml/ITshowRequest.fxml";
		windowName = "ICM-ITshowRequests";
	}
	
	/**
	 * Inits the IT test failur report.
	 */
	public void initITTestFailurReport()
	{
		ITTestFailurReportController controller = new ITTestFailurReportController();
		pathfxml = "/Fxml/ITTestFailurReport.fxml";
		windowName = "ICM-ITTestFailurReport";	
	}
	
	/**
	 * Inits the ITCCC evaluation report.
	 */
	public void initITCCCEvaluationReport()
	{
		ITCCCEvaluationReportController controller = new ITCCCEvaluationReportController();
		pathfxml = "/Fxml/ITCCCEvaluationReport.fxml";
		windowName = "ICM-ITCCCEvaluationReport";
	}
	
	/**
	 * Inits the ITCCC request more info.
	 */
	public void initITCCCRequestMoreInfo()
	{
		ITCCCRequestMoreInfoController controller = new ITCCCRequestMoreInfoController();
		pathfxml = "/Fxml/ITCCCRequestMoreInfo.fxml";
		windowName = "ICM-ITCCCRequestMoreInfo";		
	}
	
	/**
	 * Inits the IT extension request.
	 */
	public void initITExtensionRequest()
	{
		ITExtensionRequestController controller = new ITExtensionRequestController();
		pathfxml = "/Fxml/ITExtensionRequest.fxml";
		windowName = "ICM-ITExtensionRequest";		
	}
	
	/**
	 * Inits the IT meaning assessment evaluation report.
	 */
	public void initITMeaningAssessmentEvaluationReport()
	{
		ITMeaningAssessmentEvaluationReportController controller = new ITMeaningAssessmentEvaluationReportController();
		pathfxml = "/Fxml/ITMeaningAssessmentEvaluationReport.fxml";
		windowName = "ICM-ITMeaningAssessmentEvaluationReport";		
	}
	
	/**
	 * Inits the IT handle request.
	 */
	public void initITHandleRequest()
	{
		ITHandleRequestController controller = new ITHandleRequestController();
		pathfxml = "/Fxml/ITHandleRequest.fxml";
		windowName = "ICM-ITHandleRequest";	
	}
	
	/**
	 * Inits the IT request details.
	 */
	public void initITRequestDetails()
	{
		ITRequestDetailsController controller = new ITRequestDetailsController();
		pathfxml = "/Fxml/ITRequestDetails.fxml";
		windowName = "ICM-ITRequestDetails";			
	}
	
	/**
	 * Inits the IT manager messages.
	 */
	private void initITManagerMessages() {
		ITManagerMessagesController controller = new ITManagerMessagesController();
		pathfxml = "/Fxml/ITManagerMessages.fxml";
		windowName = "ICM-ITManagerMessages";
	}

	/**
	 * Inits the IT manager employees managment.
	 */
	private void initITManagerEmployeesManagment() {
		ITManagerEmployeesManagmentController controller = new ITManagerEmployeesManagmentController();
		pathfxml = "/Fxml/ITManagerEmployeesManagment.fxml";
		windowName = "ICM-ITManagerEmployeesManagement";
		
	}

	/**
	 * Inits the IT manager show requests.
	 */
	private void initITManagerShowRequests() {
		ITManagerShowRequestsController controller = new ITManagerShowRequestsController();
		pathfxml = "/Fxml/ITManagerShowRequests.fxml";
		windowName = "ICM-ITManagerShowRequests";	
	}

	/**
	 * Inits the IT manager personal info.
	 */
	private void initITManagerPersonalInfo() {
		ITManagerPersonalInfoController controller = new ITManagerPersonalInfoController();
		pathfxml = "/Fxml/ITManagerPersonalInfo.fxml";
		windowName = "ICM-ITManagerPersonalInfo";	
	}

	/**
	 * Inits the IT manager home.
	 */
	private void initITManagerHome() {
		ITManagerHomeController controller = new ITManagerHomeController();
		pathfxml = "/Fxml/ITManagerHome.fxml";
		windowName = "ICM-ITManagerHome";	
	}
	
	/**
	 * Inits the IT manager help.
	 */
	private void initITManagerHelp() {
		ITManagerHelpController controller = new ITManagerHelpController();
		pathfxml = "/Fxml/ITManagerHelp.fxml";
		windowName = "ICM-ITManagerHelp";		
	}
	
	/**
	 * Inits the IT manager reports.
	 */
	private void initITManagerReports() {
		ITManagerReportsController controller = new ITManagerReportsController();
		pathfxml = "/Fxml/ITManagerReports.fxml";
		windowName = "ICM-ITManagerReports";
	}
	
	/**
	 * Inits the IT manager request details.
	 */
	private void initITManagerRequestDetails() {
		ITManagerRequestDetailsController controller = new ITManagerRequestDetailsController();
		pathfxml = "/Fxml/ITManagerRequestDetails.fxml";
		windowName = "ICM-ITManagerRequestDetails";
		
	}
}
