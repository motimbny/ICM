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
public class MainAllControllers {

	/** The mc ABS. */
	public mainClientABS mcABS; // singleton of one

	/** The window. */
	public Stage window;

	/** The user. */
	public User user;

	/** The s. */
	Scene s;

	/** The pane. */
	Pane pane;

	/** The mac. */
	private static MainAllControllers mac = new MainAllControllers();

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
	private boolean isConnected = false;

	/**
	 * Instantiates a new main all controllers.
	 */
	private MainAllControllers() {
		this.mac = mac;
	}

	/**
	 * This method sets the main abs.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void setMainAbs() throws IOException {
		mcABS = new mainClientABS(host, port);
		isConnected = true;
	}

	/**
	 * This method checks if server is connect.
	 *
	 * @return the checks if is connect
	 */
	public boolean getisConnect() {
		return isConnected;
	}

	/**
	 * This method gets the single instance of MainAllControllers.
	 *
	 * @return single instance of MainAllControllers
	 */
	public static MainAllControllers getInstance() {
		return mac;
	}

	/**
	 * Initializes the main all controllers.
	 *
	 * @param window the window
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void initMainAllControllers(Stage window) throws IOException {
		this.window = window;
		WindowToShow = new WindowToShow();
		WindowToShow.setWindowToShow("login");
		setWindow();
	}

	/**
	 * This method is to change window.
	 *
	 * @param MainAllControllers the main all controllers
	 */
	protected static void changeWindow(MainAllControllers MainAllControllers) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				MainAllControllers.setWindow();
			}
		});
	}

	/**
	 * This method sets the window variable.
	 *
	 * @param str the new window var
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void setWindowVar(String str) throws IOException {
		this.WindowToShow.setWindowToShow(str);
	}

	/**
	 * This method is change window.
	 */
	public void changeWin() {
		changeWindow(this);
	}

	/**
	 * This method sets the window.
	 */
	public void setWindow() {
		loadScene();
		setScence(s);
	}

	/**
	 * This method Load scene.
	 */
	public void loadScene() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(WindowToShow.getPathfxml()));
			pane = loader.load();
			WindowToShow.controller = loader.getController();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		s = new Scene(pane);
	}

	/**
	 * This method Sets the scence.
	 *
	 * @param s the new scence
	 */
	public void setScence(Scene s) {
		window.setTitle(WindowToShow.getWindowName());
		window.setScene(s);
		window.show();
	}

	/**
	 * This method Send to abs server.
	 *
	 * @param msg the msg
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void sendToAbsServer(Object msg) throws IOException {
		mcABS.openConToServer();
		mcABS.sendToServer(msg);
	}

	/**
	 * This method Log out user.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void logOutUser() throws IOException {
		DBmessage dbm;
		ArrayList<Object> arry = new ArrayList<Object>();
		arry.add(user);
		dbm = new DBmessage(MessageType.logout, arry);
		this.sendToAbsServer(dbm);
		user = null;
	}

	/**
	 * Bad user.
	 */
	public void badUser() {
		((LoginController) WindowToShow.controller).setVisibleLbl();
	}

	/**
	 * This method Sets the home user num.
	 *
	 * @param num the new home user num
	 */
	public void setHomeUserNum(int num) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				((UserHomeController) WindowToShow.controller).setRequestNumber(num);
			}
		});
	}

	/**
	 * This method Sets the home IT num.
	 *
	 * @param num the new home IT num
	 */
	public void setHomeITNum(int num) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				((ITHomeController) WindowToShow.controller).setRequestNumber(num);
			}
		});
	}

	/**
	 * Good reque send.
	 */
	public void goodRequeSend() {
		((UserAddRequestController) WindowToShow.controller).setOnSucsess();
	}

	/**
	 * This method Submit extension request.
	 *
	 * @param num the num
	 */
	public void submitExtensionRequest(int num) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				((ITExtensionRequestController) WindowToShow.controller).setOnSucsess(num);
			}
		});
	}

	/**
	 * This method Submit estimated time.
	 *
	 * @param list the list
	 */
	public void submitEstimatedTime(ArrayList<Object> list) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				((ITHandleRequestController) WindowToShow.controller).setOnSucsess();
			}
		});
	}

	/**
	 * This method shows the request after being update
	 * 
	 * @param list
	 */
	public void showReqAgain(ArrayList<Object> list) {
		try {
			((ITHandleRequestController) WindowToShow.controller).showRequests();
		} catch (IOException e) {
		}
	}

	public void hideButtonExtension(ArrayList<Object> list) {
		boolean send = ((int) list.get(0) > 3 || (int) list.get(0) == 0) ? false : true;
		((ITHandleRequestController) WindowToShow.controller).visableExtensionBTN(send);
	}

	/**
	 * This method Show user request.
	 *
	 * @param list the list
	 */
	public void showUserReq(ArrayList<Object> list) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				((UserShowRequestsController) WindowToShow.controller).setTextInTable(list);
			}
		});
	}

	/**
	 * This method Show IT request.
	 *
	 * @param list the list
	 */
	public void showITReq(ArrayList<Object> list) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				((ITshowRequestsController) WindowToShow.controller).setTextInTable(list);
			}
		});
	}

	/**
	 * This method Gets the it job in request.
	 *
	 * @param list the list
	 * @return the it job in request
	 */
	public void getITjobInReq(ArrayList<Object> list) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				((ITHandleRequestController) WindowToShow.controller).setPane(list);
			}
		});
	}

	/**
	 * This method Gets the IT request stage.
	 *
	 * @param list the list
	 * @return the IT request stage
	 */
	public void getITReqStage(ArrayList<Object> list) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				((ITExtensionRequestController) WindowToShow.controller).setTextInFields(list);
			}
		});
	}

	/**
	 * This method Submit failure report.
	 *
	 * @param list the list
	 */
	public void submitFailurReport(ArrayList<Object> list) {
		((ITTestFailurReportController) WindowToShow.controller).setOnSucsess();
	}

	/**
	 * This method Submit require more info.
	 *
	 * @param list the list
	 */
	public void submitRequireMoreInfo(ArrayList<Object> list) {
		((ITCCCRequestMoreInfoController) WindowToShow.controller).setOnSucsess();
	}

	/**
	 * This method Gets the IT request location.
	 *
	 * @param list the list
	 * @return the IT request location
	 */
	public void getITReqLocation(ArrayList<Object> list) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				((ITMeaningAssessmentEvaluationReportController) WindowToShow.controller).setTextInFields(list);
			}
		});
	}

	/**
	 * This method Submit evaluation report.
	 *
	 * @param list the list
	 */
	public void submitEvaluationReport(ArrayList<Object> list) {
		try {
			((ITMeaningAssessmentEvaluationReportController) WindowToShow.controller).showReqAgain();
		} catch (IOException e) {
		}
	}

	/**
	 * This method Show user request details.
	 *
	 * @param list the list
	 */

	public void showUserReqDetails(ArrayList<Object> list) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				((UserRequestDetailsController) WindowToShow.controller).setTextInFields(list);
			}
		});
	}

	/**
	 * This method Show IT request details.
	 *
	 * @param list the list
	 */
	public void showITReqDetails(ArrayList<Object> list) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				((ITRequestDetailsController) WindowToShow.controller).setTextInFields(list);
			}
		});
	}

	/**
	 * This method Show user specific request.
	 *
	 * @param list the list
	 */
	public void showUserSPReq(ArrayList<Object> list) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				((UserShowRequestsController) WindowToShow.controller).clearTable();
				((UserShowRequestsController) WindowToShow.controller).setTextInTable(list);
			}
		});
	}

	/**
	 * This method Show IT specific request.
	 *
	 * @param list the list
	 */
	public void showITSPReq(ArrayList<Object> list) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				((ITshowRequestsController) WindowToShow.controller).clearTable();
				((ITshowRequestsController) WindowToShow.controller).setTextInTable(list);
			}
		});
	}

	/**
	 * This method Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * This method Sets the home supervisor num.
	 *
	 * @param num the new home supervisor num
	 */
	public void setHomeSupervisorNum(int num) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				((SupervisorHomeController) WindowToShow.controller).setRequestNumber(num);
			}
		});
	}

	/**
	 * This method Show supervisor specific request.
	 *
	 * @param list the list
	 */
	public void showSupervisorSPReq(ArrayList<Object> list) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				((SupervisorShowRequestsController) WindowToShow.controller).clearTable();
				((SupervisorShowRequestsController) WindowToShow.controller).setTextInTable(list);
			}
		});
	}

	/**
	 * This method Show manager employee list.
	 *
	 * @param list the list
	 */
	public void showManagerEmployeeList(ArrayList<Object> list) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				if (list.get(0) instanceof Integer) {
					((ITManagerEmployeesManagmentController) WindowToShow.controller).setVisable();
					list.remove(0);
				}
				((ITManagerEmployeesManagmentController) WindowToShow.controller).setTextInTable(list);
			}
		});

	}

	/**
	 * This method Show supervisor request list.
	 *
	 * @param list the list
	 */
	public void showSuperviserRequestList(ArrayList<Object> list) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				((SupervisorShowRequestsController) WindowToShow.controller).setTextInTable(list);
			}
		});
	}

	/**
	 * This method Show supervisor extension request.
	 *
	 * @param list the list
	 */
	public void showSuperviserExtensionRequest(ArrayList<Object> list) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				((SupervisorExtensionRequestController) WindowToShow.controller)
						.setToFields((extensionrequest) list.get(0));
			}
		});
	}

	/**
	 * In this method Supervisor update request.
	 *
	 * @param list the list
	 */
	public void SupervisorUpdateRequest(ArrayList<Object> list) {

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				((SupervisorUpdateRequestController) WindowToShow.controller).setToFields(list);
			}
		});
	}

	/**
	 * Gets the list of IT.
	 *
	 * @param arrayList the array list
	 * @return the list of IT
	 */
	public void getListOfIT(ArrayList<Object> arrayList) {
		((SupervisorUpdateRequestController) WindowToShow.controller).setListOfIT(arrayList);
	}

	/**
	 * This method Gets the list of ITCEO.
	 *
	 * @param arrayList the array list
	 * @return the list of ITCEO
	 */
	public void getListOfITCEO(ArrayList<Object> arrayList) {
		((ITHandleRequestController) WindowToShow.controller).setListOfIT(arrayList);
	}

	/**
	 * This method Save tester succssesful.
	 *
	 * @param arrayList the array list
	 */
	public void saveTesterSuccssesful(ArrayList<Object> arrayList) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				((ITHandleRequestController) WindowToShow.controller).setOnSucsess();
			}
		});
	}

	/**
	 * This method Show IT evaluation report.
	 *
	 * @param arrayList the array list
	 */
	public void showITEvaluationReport(ArrayList<Object> arrayList) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				((ITCCCEvaluationReportController) WindowToShow.controller)
						.setTextInFields((Evluationreport) arrayList.get(0));
			}
		});
	}

	/**
	 * This method Request time supervisor.
	 *
	 * @param list the list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void ReqTimeSupervisor(ArrayList<Object> list) throws IOException {

		((SupervisorShowRequestsController) WindowToShow.controller).setTimeWindow(list);
	}

	/**
	 * This method show Supervisor request detailes.
	 *
	 * @param list the list
	 */
	public void SupervisorRequestDetailes(ArrayList<Object> list) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				((SupervisorRequestDetailsController) WindowToShow.controller).setTextInFields(list);
			}
		});
	}

	/**
	 * This method show IT manager request detailes.
	 *
	 * @param list the list
	 */
	public void ITManagerRequestDetailes(ArrayList<Object> list) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				((ITManagerRequestDetailsController) WindowToShow.controller).setTextInFields(list);
			}
		});
	}

	/**
	 * This method show Supervisor time request.
	 *
	 * @param list the list
	 */
	public void SupervisorTimeRequest(ArrayList<Object> list) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				((SupervisorTimeRequestController) WindowToShow.controller).setToFields(list);
			}
		});
	}

	/**
	 * This method show Supervisor save time ev.
	 *
	 * @param list the list
	 */
	public void SupervisorSaveTimeEv(ArrayList<Object> list) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				((SupervisorTimeRequestController) WindowToShow.controller).setOnSucsess();
			}
		});
	}

	/**
	 * This method Show superviser extension request answer.
	 *
	 * @param send the send
	 */
	public void showSuperviserExtensionRequestAnswer(ArrayList<Object> send) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				((SupervisorExtensionRequestController) WindowToShow.controller).showAnswer();
			}
		});
	}

	/**
	 * This method Sets the home manger num.
	 *
	 * @param num the new home manger num
	 */
	public void setHomeMangerNum(int num) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				((ITManagerHomeController) WindowToShow.controller).setRequestNumber(num);
			}
		});
	}

	/**
	 * This method Show manger request list.
	 *
	 * @param list the list
	 */
	public void showMangerRequestList(ArrayList<Object> list) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				((ITManagerShowRequestsController) WindowToShow.controller).setTextTable(list);
			}
		});
	}

	/**
	 * This method Show man SP req.
	 *
	 * @param list the list
	 */
	public void showManSPReq(ArrayList<Object> list) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				((ITManagerShowRequestsController) WindowToShow.controller).clearTable();
				((ITManagerShowRequestsController) WindowToShow.controller).setTextTable(list);
			}
		});
	}

	/**
	 * This method show Manager show message.
	 *
	 * @param list the list
	 */
	public void ManagerShowMessage(ArrayList<Object> list) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				((ITManagerMessagesController) WindowToShow.controller).setTextTable(list);
			}
		});
	}

	/**
	 * This method Setvisable.
	 */

	public void setvisable() {

		((SupervisorShowRequestsController) WindowToShow.controller).setvisable();

	}

	/**
	 * This method Superviser show message.
	 *
	 * @param list the list
	 */
	public void SuperviserShowMessage(ArrayList<Object> list) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				((SupervisorMessagesController) WindowToShow.controller).setTextTable(list);
			}
		});
	}

	/**
	 * This method show User connected.
	 */
	public void UserConnected() {
		((LoginController) WindowToShow.controller).setVisibleLblCon();
	}

	/**
	 * This method Make activity report.
	 *
	 * @param arrayList the array list
	 */
	public void makeActiveSuClo(ArrayList<Object> arrayList) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {

				((ITManagerReportsController) WindowToShow.controller).setActiveSuClo(arrayList);
			}
		});

	}

	/**
	 * This method Make performance report.
	 *
	 * @param send the send
	 */
	public void makePerformenct(ArrayList<Object> send) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				((ITManagerReportsController) WindowToShow.controller).setmakePerformenct(send);
			}
		});
	}

	/**
	 * This method make delays report.
	 *
	 * @param send the send
	 */
	public void makemakeDelays(ArrayList<Object> send) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				((ITManagerReportsController) WindowToShow.controller).setmakeDelays(send);
			}
		});
	}

	/**
	 * This method Sets the home manger num messages.
	 *
	 * @param num the new home manger num messages
	 */
	public void setHomeMangerNumMessages(int num) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				((ITManagerHomeController) WindowToShow.controller).setMessagesNumber(num);
			}
		});

	}

	/**
	 * This method Sets the home supervisor mess num.
	 *
	 * @param num the new home supervisor mess num
	 */
	public void setHomeSupervisorMessNum(int num) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				((SupervisorHomeController) WindowToShow.controller).setMessagesNumber(num);
			}
		});

	}

	/**
	 * This method make SupervisorRequestDetailesFile
	 *
	 * @param send the send
	 */
	public void SupervisorRequestDetailesFile(ArrayList<Object> send) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				((SupervisorRequestDetailsController) WindowToShow.controller).openRequest(send);
			}
		});

	}

	/**
	 * This method make ITManagerRequestDetailesFile
	 *
	 * @param send the send
	 */
	public void ITManagerRequestDetailesFile(ArrayList<Object> send) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				((ITManagerRequestDetailsController) WindowToShow.controller).openRequest(send);
			}
		});

	}

	/**
	 * This method make ITRequestDetailesFile
	 *
	 * @param send the send
	 */
	public void ITRequestDetailesFile(ArrayList<Object> send) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				((ITRequestDetailsController) WindowToShow.controller).openRequest(send);
			}
		});
	}

	/**
	 * This method make viewrecentreport
	 *
	 * @param send the send
	 */
	public void viewrecentreport(ArrayList<Object> send) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				((ITManagerReportsController) WindowToShow.controller).setTextInTable(send);
			}
		});

	}

}
