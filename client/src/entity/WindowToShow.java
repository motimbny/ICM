package entity;

import controllers.CCEvaluationReportController;
import controllers.CCRequestMoreInfoController;
import controllers.CCShowRequestsController;
import controllers.LoginController;
import controllers.PerformanceLeaderHomeController;
import controllers.PerformanceLeaderShowRequestsController;
import controllers.SuperviserExtentionRequestController;
import controllers.SupervisorEmployeesListController;
import controllers.SupervisorHomeController;
import controllers.SupervisorShowRequestsController;
import controllers.TesterShowRequestsController;
import controllers.TesterSubmitReportController;
import controllers.UserAddRequestController;
import controllers.UserHomeController;
import controllers.UserShowRequestsController;

public class WindowToShow {
	public Object controller;
	private String pathfxml, windowName;

	public void setWindowToShow(String str) {
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
		case "SupervisorHome":
			initSupervisorHome();
			break;
		case "SupervisorEmployeesList":
			initSupervisorEmployeesList();
			break;
		case "SuperviserExtentionRequest":
			initSuperviserExtentionRequest();
			break;
		case "SupervisorShowRequests":
			initSupervisorShowRequests();
			break;
		}
	}
//
	public String getPathfxml() {
		return pathfxml;
	}

	public String getWindowName() {
		return windowName;
	}

	//------------------------------------------------------------------//
	public void initLogin() {
		LoginController controller = new LoginController();
		controller.loginController();
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
	
	public void initUserShowRequests() {
		UserShowRequestsController controller = new UserShowRequestsController(); // UserShowRequestsController
		pathfxml = "/Fxml/UserShowRequests.fxml";
		windowName = "ICM-UserShowRequests";
	}

	public void initUserAddRequest() {
		UserAddRequestController controller = new UserAddRequestController(); // UserAddRequestController
		pathfxml = "/Fxml/UserAddRequest.fxml";
		windowName = "ICM-UserAddRequest";
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
	
	public void initSupervisorEmployeesList() {
		SupervisorEmployeesListController controller = new SupervisorEmployeesListController(); // SupervisorEmployeesListController
		pathfxml = "/Fxml/SupervisorEmployeesList.fxml";
		windowName = "ICM-SupervisorEmployeesList";
	}
	
	public void initSupervisorShowRequests() {
		SupervisorShowRequestsController controller = new SupervisorShowRequestsController(); // SupervisorShowRequestsController
		pathfxml = "/Fxml/SupervisorShowRequests.fxml";
		windowName = "ICM-SupervisorShowRequests";
	}
	
	public void initSuperviserExtentionRequest() {
		SuperviserExtentionRequestController controller = new SuperviserExtentionRequestController(); // SuperviserExtentionRequestController
		pathfxml = "/Fxml/SuperviserExtentionRequest.fxml";
		windowName = "ICM-SuperviserExtentionRequest";
	}
}
