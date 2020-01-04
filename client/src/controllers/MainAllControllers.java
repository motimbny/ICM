package controllers;

import java.io.IOException;
import java.util.ArrayList;

import boundries.mainClientABS;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import entity.DBmessage;
import entity.Evluationreport;
import entity.RequestUser;
import entity.User;
import entity.WindowToShow;
import entity.extensionrequest;
import entity.updateRequest;
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
	public String nowWin;
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
	public void submitExtensionRequest(int num)
	{
		((ITExtensionRequestController) WindowToShow.controller).setOnSucsess(num);		
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
	public void submitFailurReport(ArrayList<Object> list)
	{
		((ITTestFailurReportController) WindowToShow.controller).setOnSucsess();
	}
	public void getITReqLocation(ArrayList<Object> list)
	{
		((ITMeaningAssessmentEvaluationReportController) WindowToShow.controller).setTextInFields(list);
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
	public void showManagerEmployeeList(ArrayList<Object> list)
	{
		((ITManagerEmployeesManagmentController) WindowToShow.controller).setTextInTable(list);
		
	}
	public void showSuperviserRequestList(ArrayList<Object> list)
	{
		((SupervisorShowRequestsController) WindowToShow.controller).setTextInTable(list);
	}
	public void showSuperviserExtensionRequest(ArrayList<Object> list)
	{
		((SupervisorExtensionRequestController) WindowToShow.controller).setToFields((extensionrequest) list.get(0));
		
	}
	public void SupervisorUpdateRequest(ArrayList<Object> list) {
		
		((SupervisorUpdateRequestController) WindowToShow.controller).setToFields((updateRequest) list.get(0));
		
	}
	public void getListOfIT(ArrayList<Object> arrayList) 
	{
		((SupervisorUpdateRequestController) WindowToShow.controller).setListOfIT(arrayList);
	}
	public void showITEvaluationReport(ArrayList<Object> arrayList) {
		System.out.println();
		((ITCCCEvaluationReportController) WindowToShow.controller).setTextInFields((Evluationreport) arrayList.get(0));
	}
	public void SupervisorRequestDetailes(ArrayList<Object> list)
	{
		((SupervisorRequestDetailsController) WindowToShow.controller).setTextInFields(list);		
	}
	public void SupervisorTimeRequest(ArrayList<Object> list)
	{
		((SupervisorTimeRequestController) WindowToShow.controller).setToFields((updateRequest) list.get(0));
	}
	public void showSuperviserExtensionRequestAnswer(ArrayList<Object> send) 
	{
		((SupervisorExtensionRequestController) WindowToShow.controller).showAnswer();
	}
	public void setHomeMangerNum(int num) 
	{
		((ITManagerHomeController) WindowToShow.controller).setRequestNumber(num);
	}
	public void showMangerRequestList(ArrayList<Object> list)
	{
		((ITManagerShowRequestsController)WindowToShow.controller).setTextTable(list);
	}
	public void showManSPReq(ArrayList<Object> list) 
	{
		((ITManagerShowRequestsController) WindowToShow.controller).clearTable();
		((ITManagerShowRequestsController) WindowToShow.controller).setTextTable(list);
	}
	
}
