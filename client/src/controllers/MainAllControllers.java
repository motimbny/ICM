package controllers;

import java.io.IOException;
import java.util.ArrayList;

import Enums.MessageType;
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
	public String host;
	public int port;
	private boolean isConnected=false;
	private MainAllControllers() 
	{
		this.mac=mac;
	}
	public void setMainAbs() throws IOException
	{
		   mcABS=new mainClientABS(host,port);
		   isConnected=true;
	}
	public boolean getisConnect()
	{
		return isConnected;
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
	public void logOutUser() throws IOException
	{
		DBmessage dbm;
		ArrayList<Object> arry=new ArrayList<Object>();
		arry.add(user);
		dbm= new DBmessage(MessageType.logout,arry);
		this.sendToAbsServer(dbm);
		user=null;
	}
	public void badUser()
	{
		((LoginController) WindowToShow.controller).setVisibleLbl();
	}
	public void setHomeUserNum(int num)
	{
		Platform.runLater(new Runnable()
		{
		@Override
		public void run() 
		{
		((UserHomeController) WindowToShow.controller).setRequestNumber(num);
		}});
	}
	public void setHomeITNum(int num)
	{
		Platform.runLater(new Runnable()
		{
		@Override
		public void run() 
		{
		((ITHomeController) WindowToShow.controller).setRequestNumber(num);
		}});
	}
	public void goodRequeSend()
	{
		((UserAddRequestController) WindowToShow.controller).setOnSucsess();		
	}
	public void submitExtensionRequest(int num)
	{Platform.runLater(new Runnable()
	{
	@Override
	public void run() 
	{
		((ITExtensionRequestController) WindowToShow.controller).setOnSucsess(num);	
	}});
	}
	public void submitEstimatedTime(ArrayList<Object> list)
	{
		Platform.runLater(new Runnable()
		{
		@Override
		public void run() 
		{
		((ITHandleRequestController) WindowToShow.controller).setOnSucsess();		
		}});
	}
	public void showUserReq(ArrayList<Object> list)
	{
		Platform.runLater(new Runnable()
		{
		@Override
		public void run() 
		{
		((UserShowRequestsController) WindowToShow.controller).setTextInTable(list);
		}});
	}
	public void showITReq(ArrayList<Object> list)
	{
		Platform.runLater(new Runnable()
		{
		@Override
		public void run() 
		{
		((ITshowRequestsController) WindowToShow.controller).setTextInTable(list);
		}});
		}
	public void getITjobInReq(ArrayList<Object> list)
	{
		Platform.runLater(new Runnable()
		{
		@Override
		public void run() 
		{
		((ITHandleRequestController) WindowToShow.controller).setPane(list);
		}});
	}
	public void getITReqStage(ArrayList<Object> list)
	{
		Platform.runLater(new Runnable()
		{
		@Override
		public void run() 
		{
		((ITExtensionRequestController) WindowToShow.controller).setTextInFields(list);
	}});
	}
	public void submitFailurReport(ArrayList<Object> list)
	{
		((ITTestFailurReportController) WindowToShow.controller).setOnSucsess();
	}
	public void submitRequireMoreInfo(ArrayList<Object> list)
	{
		((ITCCCRequestMoreInfoController) WindowToShow.controller).setOnSucsess();
	}
	public void getITReqLocation(ArrayList<Object> list)
	{
		Platform.runLater(new Runnable()
		{
		@Override
		public void run() 
		{
		((ITMeaningAssessmentEvaluationReportController) WindowToShow.controller).setTextInFields(list);
		}});
	}
	public void submitEvaluationReport(ArrayList<Object> list)
	{
		((ITMeaningAssessmentEvaluationReportController) WindowToShow.controller).setOnSucsess();
	}
/*	public void submitEvaluationTime(ArrayList<Object> list)
	{
		((ITHandleRequestController) WindowToShow.controller).setOnSucsess();
	}*/
	public void showUserReqDetails(ArrayList<Object> list)
	{
		Platform.runLater(new Runnable()
		{
		@Override
		public void run() 
		{
		((UserRequestDetailsController) WindowToShow.controller).setTextInFields(list);
	}});
		}
	public void showITReqDetails(ArrayList<Object> list)
	{
		Platform.runLater(new Runnable()
		{
		@Override
		public void run() 
		{
		((ITRequestDetailsController) WindowToShow.controller).setTextInFields(list);
		}});
	}
	public void showUserSPReq(ArrayList<Object> list)
	{
		Platform.runLater(new Runnable()
		{
		@Override
		public void run() 
		{
		((UserShowRequestsController) WindowToShow.controller).clearTable();
		((UserShowRequestsController) WindowToShow.controller).setTextInTable(list);
		}});
	}
	public void showITSPReq(ArrayList<Object> list)
	{
		Platform.runLater(new Runnable()
		{
		@Override
		public void run() 
		{
		((ITshowRequestsController) WindowToShow.controller).clearTable();
		((ITshowRequestsController) WindowToShow.controller).setTextInTable(list);
		}});
	}
	public void setUser(User user)
	{
		this.user=user;
	}
	public void setHomeSupervisorNum(int num)
	{		
		Platform.runLater(new Runnable()
		{
		@Override
		public void run() 
		{
		((SupervisorHomeController) WindowToShow.controller).setRequestNumber(num);
		}});
	}
	public void showSupervisorSPReq(ArrayList<Object> list)
	{
		Platform.runLater(new Runnable()
		{
		@Override
		public void run() 
		{
		((SupervisorShowRequestsController) WindowToShow.controller).clearTable();
		((SupervisorShowRequestsController) WindowToShow.controller).setTextInTable(list);
		}});
	}
	public void showManagerEmployeeList(ArrayList<Object> list)
	{
		Platform.runLater(new Runnable()
		{
		@Override
		public void run() 
		{
        if(list.get(0) instanceof Integer)
        {
            ((ITManagerEmployeesManagmentController) WindowToShow.controller).setVisable();
             list.remove(0);
        }
        ((ITManagerEmployeesManagmentController) WindowToShow.controller).setTextInTable(list);
		}});

	}
	public void showSuperviserRequestList(ArrayList<Object> list)
	{
		Platform.runLater(new Runnable()
		{
		@Override
		public void run() 
		{
		((SupervisorShowRequestsController) WindowToShow.controller).setTextInTable(list);
		}});
	}
	public void showSuperviserExtensionRequest(ArrayList<Object> list)
	{
		Platform.runLater(new Runnable()
		{
		@Override
		public void run() 
		{
		((SupervisorExtensionRequestController) WindowToShow.controller).setToFields((extensionrequest) list.get(0));
		}});
	}
	public void SupervisorUpdateRequest(ArrayList<Object> list) {
		
		Platform.runLater(new Runnable()
		{
		@Override
		public void run() 
		{
		((SupervisorUpdateRequestController) WindowToShow.controller).setToFields(list);
		}});
	}
	public void getListOfIT(ArrayList<Object> arrayList) 
	{
		Platform.runLater(new Runnable()
		{
		@Override
		public void run() 
		{
		((SupervisorUpdateRequestController) WindowToShow.controller).setListOfIT(arrayList);
	}});
		}
		
	public void getListOfITCEO(ArrayList<Object> arrayList) 
	{
		Platform.runLater(new Runnable()
		{
		@Override
		public void run() 
		{
		((ITHandleRequestController) WindowToShow.controller).setListOfIT(arrayList);
		}});
	}
	public void saveTesterSuccssesful(ArrayList<Object> arrayList) 
	{
		Platform.runLater(new Runnable()
		{
		@Override
		public void run() 
		{
		((ITHandleRequestController) WindowToShow.controller).setOnSucsess();
		}});
	}
	public void showITEvaluationReport(ArrayList<Object> arrayList) 
	{
		Platform.runLater(new Runnable()
		{
		@Override
		public void run() 
		{
		((ITCCCEvaluationReportController) WindowToShow.controller).setTextInFields((Evluationreport) arrayList.get(0));
		}});
	}
	public void ReqTimeSupervisor(ArrayList<Object> list) throws IOException
	{
		
		((SupervisorShowRequestsController) WindowToShow.controller).setTimeWindow(list);		
	}
	public void SupervisorRequestDetailes(ArrayList<Object> list)
	{
		Platform.runLater(new Runnable()
		{
		@Override
		public void run() 
		{
		((SupervisorRequestDetailsController) WindowToShow.controller).setTextInFields(list);	
		}});
	}
	public void ITManagerRequestDetailes(ArrayList<Object> list)
	{
		Platform.runLater(new Runnable()
		{
		@Override
		public void run() 
		{
		((ITManagerRequestDetailsController) WindowToShow.controller).setTextInFields(list);	
		}});
	}
	public void SupervisorTimeRequest(ArrayList<Object> list)
	{
		Platform.runLater(new Runnable()
		{
		@Override
		public void run() 
		{
		((SupervisorTimeRequestController) WindowToShow.controller).setToFields(list);
		}});
	}
	public void SupervisorSaveTimeEv(ArrayList<Object> list)
	{
		Platform.runLater(new Runnable()
		{
		@Override
		public void run() 
		{
		((SupervisorTimeRequestController) WindowToShow.controller).setOnSucsess();
		}});
	}
	public void showSuperviserExtensionRequestAnswer(ArrayList<Object> send) 
	{
		Platform.runLater(new Runnable()
		{
		@Override
		public void run() 
		{
		((SupervisorExtensionRequestController) WindowToShow.controller).showAnswer();
		}});
	}
	public void setHomeMangerNum(int num) 
	{
		Platform.runLater(new Runnable()
		{
		@Override
		public void run() 
		{
		((ITManagerHomeController) WindowToShow.controller).setRequestNumber(num);
		}});
	}
	public void showMangerRequestList(ArrayList<Object> list)
	{
		Platform.runLater(new Runnable()
		{
		@Override
		public void run() 
		{
		((ITManagerShowRequestsController)WindowToShow.controller).setTextTable(list);
		}});
	}
	public void showManSPReq(ArrayList<Object> list) 
	{
		Platform.runLater(new Runnable()
		{
		@Override
		public void run() 
		{
		((ITManagerShowRequestsController) WindowToShow.controller).clearTable();
		((ITManagerShowRequestsController) WindowToShow.controller).setTextTable(list);
		}});
	}

	public void ManagerShowMessage(ArrayList<Object> list) 
		{
		Platform.runLater(new Runnable()
		{
		@Override
		public void run() 
		{
			((ITManagerMessagesController)WindowToShow.controller).setTextTable(list);
		}});
		}

/*	public void addTimeEstimated(Object object) {
		
		((ITHandleRequestController) WindowToShow.controller).setvisable();
	}*/
	public void setvisable() {

		((SupervisorShowRequestsController) WindowToShow.controller).setvisable();
	
	}
	public void SuperviserShowMessage(ArrayList<Object> list)
	{
		Platform.runLater(new Runnable()
		{
		@Override
		public void run() 
		{
		((SupervisorMessagesController)WindowToShow.controller).setTextTable(list);	
		}});
	}
	public void UserConnected() 
	{
		((LoginController) WindowToShow.controller).setVisibleLblCon();
	}
	public void makeActiveSuClo(ArrayList<Object> arrayList) 
	{Platform.runLater(new Runnable()
	{
	@Override
	public void run() 
	{
		
		((ITManagerReportsController) WindowToShow.controller).setActiveSuClo(arrayList);
	}});
		
	}
	public void makePerformenct(ArrayList<Object> send) {
		Platform.runLater(new Runnable()
		{
		@Override
		public void run() 
		{
		((ITManagerReportsController) WindowToShow.controller).setmakePerformenct(send);
		}});
	}
	public void makemakeDelays(ArrayList<Object> send) {
		Platform.runLater(new Runnable()
		{
		@Override
		public void run() 
		{
		((ITManagerReportsController) WindowToShow.controller).setmakeDelays(send);	
		}});
	}
	
}
