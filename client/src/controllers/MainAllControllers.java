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

/**
 * The Class MainAllControllers.
 */
public class MainAllControllers 
{
	
	/** The mc ABS. */
	public mainClientABS mcABS;       //singleton of one 
	
	/** The window. */
	public Stage window;
	
	/** The user. */
	public User user;
	
	/** The s. */
	Scene s;
	
	/** The pane. */
	Pane pane;
    
    /** The mac. */
    private static MainAllControllers mac=new MainAllControllers();
	
	/** The Window to show. */
	public WindowToShow WindowToShow;
	
	/** The request. */
	public int request;
	
	/** The it handlejob. */
	public String itHandlejob;
	
	/** The now win. */
	public String nowWin;
	
	/** The host. */
	public String host;
	
	/** The port. */
	public int port;
	
	/** The is connected. */
	private boolean isConnected=false;
	
	/**
	 * Instantiates a new main all controllers.
	 */
	private MainAllControllers() 
	{
		this.mac=mac;
	}
	
	/**
	 * Sets the main abs.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void setMainAbs() throws IOException
	{
		   mcABS=new mainClientABS(host,port);
		   isConnected=true;
	}
	
	/**
	 * Gets the checks if is connect.
	 *
	 * @return the checks if is connect
	 */
	public boolean getisConnect()
	{
		return isConnected;
	}
	
	/**
	 * Gets the single instance of MainAllControllers.
	 *
	 * @return single instance of MainAllControllers
	 */
	public static MainAllControllers getInstance()
	{
		return mac;
	}
    
    /**
     * Inits the main all controllers.
     *
     * @param window the window
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void initMainAllControllers(Stage window) throws IOException
    {
    	this.window=window;
    	WindowToShow=new WindowToShow();
    	WindowToShow.setWindowToShow("login");
    	setWindow();  	
    }
    
    /**
     * Change window.
     *
     * @param MainAllControllers the main all controllers
     */
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
    
    /**
     * Sets the window var.
     *
     * @param str the new window var
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void setWindowVar(String str) throws IOException
    {
    	this.WindowToShow.setWindowToShow(str);
    }
    
    /**
     * Change win.
     */
    public void changeWin()
    {
    	changeWindow(this);
    } 
    
	/**
	 * Sets the window.
	 */
	public void setWindow() 
	{
		loadScene();
		setScence(s);
	}
	
	/**
	 * Load scene.
	 */
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
	
	/**
	 * Sets the scence.
	 *
	 * @param s the new scence
	 */
	public void setScence(Scene s)
	{
		 window.setTitle(WindowToShow.getWindowName());
	     window.setScene(s);
	     window.show();
	}
	
	/**
	 * Send to abs server.
	 *
	 * @param msg the msg
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void sendToAbsServer(Object msg) throws IOException
	{
		mcABS.openConToServer();
		mcABS.sendToServer(msg);
	}
	
	/**
	 * Log out user.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void logOutUser() throws IOException
	{
		DBmessage dbm;
		ArrayList<Object> arry=new ArrayList<Object>();
		arry.add(user);
		dbm= new DBmessage(MessageType.logout,arry);
		this.sendToAbsServer(dbm);
		user=null;
	}
	
	/**
	 * Bad user.
	 */
	public void badUser()
	{
		((LoginController) WindowToShow.controller).setVisibleLbl();
	}
	
	/**
	 * Sets the home user num.
	 *
	 * @param num the new home user num
	 */
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
	
	/**
	 * Sets the home IT num.
	 *
	 * @param num the new home IT num
	 */
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
	
	/**
	 * Good reque send.
	 */
	public void goodRequeSend()
	{
		((UserAddRequestController) WindowToShow.controller).setOnSucsess();		
	}
	
	/**
	 * Submit extension request.
	 *
	 * @param num the num
	 */
	public void submitExtensionRequest(int num)
	{Platform.runLater(new Runnable()
	{
	@Override
	public void run() 
	{
		((ITExtensionRequestController) WindowToShow.controller).setOnSucsess(num);	
	}});
	}
	
	/**
	 * Submit estimated time.
	 *
	 * @param list the list
	 */
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
	
	public void showReqAgain(ArrayList<Object> list)
	{
		try {
			((ITHandleRequestController) WindowToShow.controller).showRequests();
		} catch (IOException e) {}
	}
	
	public void hideButtonExtension(ArrayList<Object> list)
	{
		boolean send=((int)list.get(0)>3 ||(int)list.get(0)==0) ? false : true;
		((ITHandleRequestController) WindowToShow.controller).visableExtensionBTN(send);
	}
	
	/**
	 * Show user req.
	 *
	 * @param list the list
	 */
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
	
	/**
	 * Show IT req.
	 *
	 * @param list the list
	 */
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
	
	/**
	 * Gets the i tjob in req.
	 *
	 * @param list the list
	 * @return the i tjob in req
	 */
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
	
	/**
	 * Gets the IT req stage.
	 *
	 * @param list the list
	 * @return the IT req stage
	 */
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
	
	/**
	 * Submit failur report.
	 *
	 * @param list the list
	 */
	public void submitFailurReport(ArrayList<Object> list)
	{
		((ITTestFailurReportController) WindowToShow.controller).setOnSucsess();
	}
	
	/**
	 * Submit require more info.
	 *
	 * @param list the list
	 */
	public void submitRequireMoreInfo(ArrayList<Object> list)
	{
		((ITCCCRequestMoreInfoController) WindowToShow.controller).setOnSucsess();
	}
	
	/**
	 * Gets the IT req location.
	 *
	 * @param list the list
	 * @return the IT req location
	 */
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
	
	/**
	 * Submit evaluation report.
	 *
	 * @param list the list
	 */
	public void submitEvaluationReport(ArrayList<Object> list)
	{
		try {
			((ITMeaningAssessmentEvaluationReportController) WindowToShow.controller).showReqAgain();
		} catch (IOException e) {
		}
	}

/**
 * Show user req details.
 *
 * @param list the list
 */
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
	
	/**
	 * Show IT req details.
	 *
	 * @param list the list
	 */
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
	
	/**
	 * Show user SP req.
	 *
	 * @param list the list
	 */
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
	
	/**
	 * Show ITSP req.
	 *
	 * @param list the list
	 */
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
	
	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(User user)
	{
		this.user=user;
	}
	
	/**
	 * Sets the home supervisor num.
	 *
	 * @param num the new home supervisor num
	 */
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
	
	/**
	 * Show supervisor SP req.
	 *
	 * @param list the list
	 */
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
	
	/**
	 * Show manager employee list.
	 *
	 * @param list the list
	 */
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
	
	/**
	 * Show superviser request list.
	 *
	 * @param list the list
	 */
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
	
	/**
	 * Show superviser extension request.
	 *
	 * @param list the list
	 */
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
	
	/**
	 * Supervisor update request.
	 *
	 * @param list the list
	 */
	public void SupervisorUpdateRequest(ArrayList<Object> list) {
		
		Platform.runLater(new Runnable()
		{
		@Override
		public void run() 
		{
		((SupervisorUpdateRequestController) WindowToShow.controller).setToFields(list);
		}});
	}
	
	/**
	 * Gets the list of IT.
	 *
	 * @param arrayList the array list
	 * @return the list of IT
	 */
	public void getListOfIT(ArrayList<Object> arrayList) 
	{
		((SupervisorUpdateRequestController) WindowToShow.controller).setListOfIT(arrayList);
		}
		
	/**
	 * Gets the list of ITCEO.
	 *
	 * @param arrayList the array list
	 * @return the list of ITCEO
	 */
	public void getListOfITCEO(ArrayList<Object> arrayList) 
	{
		((ITHandleRequestController) WindowToShow.controller).setListOfIT(arrayList);
	}
	
	/**
	 * Save tester succssesful.
	 *
	 * @param arrayList the array list
	 */
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
	
	/**
	 * Show IT evaluation report.
	 *
	 * @param arrayList the array list
	 */
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
	
	/**
	 * Req time supervisor.
	 *
	 * @param list the list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void ReqTimeSupervisor(ArrayList<Object> list) throws IOException
	{
		
		((SupervisorShowRequestsController) WindowToShow.controller).setTimeWindow(list);		
	}
	
	/**
	 * Supervisor request detailes.
	 *
	 * @param list the list
	 */
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
	
	/**
	 * IT manager request detailes.
	 *
	 * @param list the list
	 */
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
	
	/**
	 * Supervisor time request.
	 *
	 * @param list the list
	 */
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
	
	/**
	 * Supervisor save time ev.
	 *
	 * @param list the list
	 */
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
	
	/**
	 * Show superviser extension request answer.
	 *
	 * @param send the send
	 */
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
	
	/**
	 * Sets the home manger num.
	 *
	 * @param num the new home manger num
	 */
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
	
	/**
	 * Show manger request list.
	 *
	 * @param list the list
	 */
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
	
	/**
	 * Show man SP req.
	 *
	 * @param list the list
	 */
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

	/**
	 * Manager show message.
	 *
	 * @param list the list
	 */
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

/**
 * Setvisable.
 */
/*	public void addTimeEstimated(Object object) {
		
		((ITHandleRequestController) WindowToShow.controller).setvisable();
	}*/
	public void setvisable() {

		((SupervisorShowRequestsController) WindowToShow.controller).setvisable();
	
	}
	
	/**
	 * Superviser show message.
	 *
	 * @param list the list
	 */
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
	
	/**
	 * User connected.
	 */
	public void UserConnected() 
	{
		((LoginController) WindowToShow.controller).setVisibleLblCon();
	}
	
	/**
	 * Make active su clo.
	 *
	 * @param arrayList the array list
	 */
	public void makeActiveSuClo(ArrayList<Object> arrayList) 
	{Platform.runLater(new Runnable()
	{
	@Override
	public void run() 
	{
		
		((ITManagerReportsController) WindowToShow.controller).setActiveSuClo(arrayList);
	}});
		
	}
	
	/**
	 * Make performenct.
	 *
	 * @param send the send
	 */
	public void makePerformenct(ArrayList<Object> send) {
		Platform.runLater(new Runnable()
		{
		@Override
		public void run() 
		{
		((ITManagerReportsController) WindowToShow.controller).setmakePerformenct(send);
		}});
	}
	
	/**
	 * Makemake delays.
	 *
	 * @param send the send
	 */
	public void makemakeDelays(ArrayList<Object> send) {
		Platform.runLater(new Runnable()
		{
		@Override
		public void run() 
		{
		((ITManagerReportsController) WindowToShow.controller).setmakeDelays(send);	
		}});
	}
	
	/**
	 * Sets the home manger num messages.
	 *
	 * @param num the new home manger num messages
	 */
	public void setHomeMangerNumMessages(int num)
	{
		Platform.runLater(new Runnable()
		{
		@Override
		public void run() 
		{
		((ITManagerHomeController) WindowToShow.controller).setMessagesNumber(num);
		}});
		
	}
	
	/**
	 * Sets the home supervisor mess num.
	 *
	 * @param num the new home supervisor mess num
	 */
	public void setHomeSupervisorMessNum(int num) 
	{
		Platform.runLater(new Runnable()
		{
		@Override
		public void run() 
		{
		((SupervisorHomeController) WindowToShow.controller).setMessagesNumber(num);
		}});
		
	}

	public void SupervisorRequestDetailesFile(ArrayList<Object> send) 
	{
		Platform.runLater(new Runnable()
		{
		@Override
		public void run() 
		{
		((SupervisorRequestDetailsController) WindowToShow.controller).openRequest(send);	
		}});
		
	}

	public void ITManagerRequestDetailesFile(ArrayList<Object> send)
	{
		Platform.runLater(new Runnable()
		{
		@Override
		public void run() 
		{
		((ITManagerRequestDetailsController) WindowToShow.controller).openRequest(send);	
		}});
		
	}
	public void ITRequestDetailesFile(ArrayList<Object> send)
	{
		Platform.runLater(new Runnable()
		{
		@Override
		public void run() 
		{
		((ITRequestDetailsController) WindowToShow.controller).openRequest(send);	
		}});	
	}

	public void viewrecentreport(ArrayList<Object> send) {
		Platform.runLater(new Runnable()
		{
		@Override
		public void run() 
		{
			System.out.println("main");
		((ITManagerReportsController) WindowToShow.controller).setTextInTable(send);
		}});
		
	}
	
}
