package entity;

import java.io.IOException;

import controllers.CCEvaluationReportController;
import controllers.CCRequestMoreInfoController;
import controllers.CCShowRequestsController;
import controllers.LoginController;
import controllers.PerformanceLeaderHomeController;
import controllers.PerformanceLeaderShowRequestsController;
import controllers.SupervisorEmployeesListController;
import controllers.SupervisorEmployeesManagmentController;
import controllers.SupervisorExtentionRequestController;
import controllers.SupervisorHelpController;
import controllers.SupervisorHomeController;
import controllers.SupervisorPersonalInfoController;
import controllers.SupervisorShowRequestsController;
import controllers.TesterShowRequestsController;
import controllers.TesterSubmitReportController;
import controllers.UserAddRequestController;
import controllers.UserHelpController;
import controllers.UserHomeController;
import controllers.UserPersonalInfoController;
import controllers.UserShowRequestsController;

public class WindowToShow {
	public Object controller;
	private String pathfxml, windowName;

	public void setWindowToShow(String str) throws IOException {
		switch (str) 
		{
		case "login":
			initLogin();
			break;
		case "userHome":
			initUserHome();
			break;
		case "UserShowRequests":
			initUserShowRequests();
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
		case "SupervisorEmployeesList":
			initSupervisorEmployeesList();
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
		case "TesterSubmitReport":
			initTesterSubmitReport();
			break;
		case "TesterShowRequests":
			initTesterShowRequests();
			break;
		case "PerformanceLeaderShowRequests":
			initPerformanceLeaderShowRequests();
			break;
		case "PerformanceLeaderHome":
			initPerformanceLeaderHome();
			break;
		case "CCEvaluationReport":
			initCCEvaluationReport();
			break;
		case "CCRequestMoreInfo":
			initCCRequestMoreInfo();
			break;
		case "CCShowRequests":
			initCCShowRequests();
			break;
		}
	}


	
	public String getPathfxml() {
		return pathfxml;
	}

	public String getWindowName() {
		return windowName;
	}
	//------------------------------------------------------------------//
	public void initLogin() {
		LoginController controller = new LoginController();
		pathfxml = "/Fxml/Login.fxml";
		windowName = "ICM-Login";
	}

	public void initCCEvaluationReport() {
		CCEvaluationReportController controller = new CCEvaluationReportController(); // CCEvaluationReport
		pathfxml = "/Fxml/ControlCommeteeEvaluationReport.fxml";
		windowName = "ICM-EvaluationReport";
	}

	public void initCCRequestMoreInfo() {
		CCRequestMoreInfoController controller = new CCRequestMoreInfoController(); // CCRequestMoreInfo
		pathfxml = "/Fxml/ControlCommeteeRequestMoreInfo.fxml";
		windowName = "ICM-RequestMoreInformation";
	}

	public void initCCShowRequests() {
		CCShowRequestsController controller = new CCShowRequestsController(); // CCShowRequests
		pathfxml = "/Fxml/ControlCommeteeShowRequests.fxml";
		windowName = "ICM-ShowRequests";
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

	private void initUserHelp() {
		UserHelpController controller = new UserHelpController(); // UserAddRequestController
		pathfxml = "/Fxml/UserHelp.fxml";
		windowName = "ICM-UserHelp";
		
	}
	
	public void initTesterSubmitReport() {
		TesterSubmitReportController controller = new TesterSubmitReportController(); // TesterSubmitReportController
		pathfxml = "/Fxml/TesterSubmitReport.fxml";
		windowName = "ICM-TesterSubmitReport";
	}
	
	public void initTesterShowRequests() {
		TesterShowRequestsController controller = new TesterShowRequestsController(); // TesterShowRequestsController
		pathfxml = "/Fxml/TesterShowRequests.fxml";
		windowName = "ICM-TesterShowRequests";
	}
	
	public void initPerformanceLeaderShowRequests() {
		PerformanceLeaderShowRequestsController controller = new PerformanceLeaderShowRequestsController(); // PerformanceLeaderShowRequestsController
		pathfxml = "/Fxml/PerformanceLeaderShowRequests.fxml";
		windowName = "ICM-PerformanceLeaderShowRequests";
	}
	
	public void initPerformanceLeaderHome() {
		PerformanceLeaderHomeController controller = new PerformanceLeaderHomeController(); // PerformanceLeaderHomeController
		pathfxml = "/Fxml/PerformanceLeaderHome.fxml";
		windowName = "ICM-PerformanceLeaderHome";
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
	
	public void initSupervisorEmployeesList() {
		SupervisorEmployeesListController controller = new SupervisorEmployeesListController(); // SupervisorEmployeesListController
		pathfxml = "/Fxml/SupervisorEmployeesList.fxml";
		windowName = "ICM-SupervisorEmployeesList";
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

}
