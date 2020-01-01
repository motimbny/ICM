package controllers;

import java.io.IOException;
import java.util.ArrayList;

import boundries.mainClientABS;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import entity.RequestUser;
import entity.User;
import entity.WindowToShow;
import entity.evluationReport;
public class MainAllControllers 
{
	public mainClientABS mcABS;       //singleton of one 
	public Stage window;
	public User user;
	Scene s;
	Pane pane;
    private static MainAllControllers mac=new MainAllControllers();
	public WindowToShow WindowToShow;
	public int request;
	public String itHandlejob;
	
	private MainAllControllers() 
	{
		this.mac=mac;
	}
	public void setMainAbs() throws IOException
	{
		mcABS=new mainClientABS("",5555);
	}
	public static MainAllControllers getInstance()
	{
		return mac;
	}
    public void initMainAllControllers(Stage window) throws IOException
    {
    	this.window=window;
    	WindowToShow=new WindowToShow();
    	WindowToShow.setWindowToShow("login");
    	setWindow();  	
    }
    protected static void changeWindow(MainAllControllers MainAllControllers) 
    {
    	Platform.runLater(new Runnable() {

			@Override
			public void run() 
			{
				MainAllControllers.setWindow();
			}
		});
    }
    public void setWindowVar(String str) throws IOException
    {
    	this.WindowToShow.setWindowToShow(str);
    }
    public void changeWin()
    {
    	changeWindow(this);
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
	            loader.setLocation(getClass().getResource(WindowToShow.getPathfxml()));
	            pane =  loader.load();
	            WindowToShow.controller = loader.getController();
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
		 window.setTitle(WindowToShow.getWindowName());
	     window.setScene(s);
	     window.show();
	}
	public void sendToAbsServer(Object msg) throws IOException
	{
		mcABS.openConToServer();
		mcABS.sendToServer(msg);
	}
	public void badUser()
	{
		((LoginController) WindowToShow.controller).setVisibleLbl();
	}
	public void setHomeUserNum(int num)
	{
		((UserHomeController) WindowToShow.controller).setRequestNumber(num);
	}
	public void setHomeITNum(int num)
	{
		((ITHomeController) WindowToShow.controller).setRequestNumber(num);
	}
	public void goodRequeSend()
	{
		((UserAddRequestController) WindowToShow.controller).setOnSucsess();		
	}
	public void showUserReq(ArrayList<Object> list)
	{
		((UserShowRequestsController) WindowToShow.controller).setTextInTable(list);
	}
	public void showITReq(ArrayList<Object> list)
	{
		((ITshowRequestsController) WindowToShow.controller).setTextInTable(list);
	}
	public void getITjobInReq(ArrayList<Object> list)
	{
		((ITHandleRequestController) WindowToShow.controller).setPane(list);
	}
	public void getITReqStage(ArrayList<Object> list)
	{
		((ITExtensionRequestController) WindowToShow.controller).setTextInFields(list);
	}
	public void showUserReqDetails(ArrayList<Object> list)
	{
		((UserRequestDetailsController) WindowToShow.controller).setTextInFields(list);
	}
	public void showITReqDetails(ArrayList<Object> list)
	{
		((ITRequestDetailsController) WindowToShow.controller).setTextInFields(list);
	}
	public void showUserSPReq(ArrayList<Object> list)
	{
		((UserShowRequestsController) WindowToShow.controller).clearTable();
		((UserShowRequestsController) WindowToShow.controller).setTextInTable(list);
	}
	public void showITSPReq(ArrayList<Object> list)
	{
		((ITshowRequestsController) WindowToShow.controller).clearTable();
		((ITshowRequestsController) WindowToShow.controller).setTextInTable(list);
	}
	public void setUser(User user)
	{
		this.user=user;
	}
	public void setHomeSupervisorNum(int num)
	{
		((SupervisorHomeController) WindowToShow.controller).setRequestNumber(num);
	}
	public void showSupervisorSPReq(ArrayList<Object> list)
	{
		((SupervisorShowRequestsController) WindowToShow.controller).clearTable();
		((SupervisorShowRequestsController) WindowToShow.controller).setTextInTable(list);
	}
	public void showSuperviserEmployeeList(ArrayList<Object> list)
	{
		((SupervisorEmployeesManagmentController) WindowToShow.controller).setTextInTable(list);
		
	}
	public void showSuperviserRequestList(ArrayList<Object> list)
	{
		((SupervisorShowRequestsController) WindowToShow.controller).setTextInTable(list);
	}
	public void showSuperviserReportEV(ArrayList<Object> list)
	{
		((SupervisorExtentionRequestController) WindowToShow.controller).setToFields((evluationReport) list.get(0));
		
	}
}
