package entity;

import controllers.CCEvaluationReportController;
import controllers.CCRequestMoreInfoController;
import controllers.CCShowRequestsController;
import controllers.LoginController;

public class WindowToShow 
{
	public Object controller;
	private String pathfxml,windowName;
    public void setWindowToShow(String str)
    {
    	switch(str)
		{
		  case "login":
			 initLogin();
			 break;
		}
    }
	public String getPathfxml() {
		return pathfxml;
	}
	public String getWindowName() {
		return windowName;
	}

	//______________________________________________________________//
	public void initLogin()
	{
		LoginController controller=new LoginController();
		pathfxml="/Fxml/Login.fxml";
		windowName="ICM-Login";
	}
	public void initCCEvaluationReport()
	{
		CCEvaluationReportController controller=new CCEvaluationReportController(); //CCEvaluationReport
		pathfxml="/Fxml/ControlCommeteeEvaluationReport.fxml";
		windowName="ICM-EvaluationReport";
	}
	public void initCCRequestMoreInfo()
	{
		CCRequestMoreInfoController controller=new CCRequestMoreInfoController(); //CCRequestMoreInfo
		pathfxml="/Fxml/ControlCommeteeRequestMoreInfo.fxml";
		windowName="ICM-RequestMoreInformation";
	}
	public void initCCShowRequests()
	{
		CCShowRequestsController controller=new CCShowRequestsController(); //CCShowRequests
		pathfxml="/Fxml/ControlCommeteeShowRequests.fxml";
		windowName="ICM-ShowRequests";
	}
}
