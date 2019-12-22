package controllers;

import java.io.IOException;

import boundries.mainClientABS;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainAllControllers {

	mainClientABS mcABS;
	Object controller;
	String pathfxml,windowName;
	Stage window;
	Scene s;
    Pane pane;
	
    public MainAllControllers(Stage window)
    {
    	this.window=window;
    	initLogin();
    }
	public void setWindow() 
	{
		loadScene();
		setScence(s);
	}
	public void loadScene()
	{
	        try 
	        {
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(getClass().getResource(pathfxml));
	            pane =  loader.load();
	            controller = loader.getController();
	        } 
	        catch (IOException e) 
	        {
	            e.printStackTrace();
	            return;
	        }
	        s= new Scene(pane);	
	}
	public void setScence(Scene s)
	{
		 window.setTitle(windowName);
	     window.setScene(s);
	     window.show(); 
	}
	
	
	//------------------------------------//
	
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
		windowName="ICM-Login";
	}
	
}
