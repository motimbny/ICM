package entity;

import controllers.CCEvaluationReportController;
import controllers.CCRequestMoreInfoController;
import controllers.CCShowRequestsController;
import controllers.LoginController;
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

	// ______________________________________________________________//
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
}
