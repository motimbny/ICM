package boundries;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import Enums.MessageTypeS;
import controllers.ITCCCEvaluationReportSController;

import controllers.ITCCCRequestMoreInfoSController;

import controllers.ITExtensionRequestSController;
import controllers.ITHandleRequestSController;
import controllers.ITManagerReportsSController;
import controllers.ITManagerRequestDetailsSController;
import controllers.ITMeaningAssessmentEvaluationReportSController;
import controllers.ITRequestDetailsSController;
import controllers.ITShowRequestsSController;
import controllers.ITTestFailurReportSController;
import controllers.MessagesShowController;
import controllers.ShowEmployeeListController;
import controllers.SupervisorShowRequestsSController;
import controllers.SupervisorUpdateRequestSController;
import controllers.UserRequestDetailsSController;
import controllers.UserSAddRequestController;
import controllers.UserShowRequestsSController;
import controllers.loginSController;
import controllers.serverController;
import controllers.superviserExtensionRequestController;
import controllers.superviserRequestShowController;
import entity.DBSmessage;
import entity.DBmessage;
import entity.User;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

/**
 * The handler of the sending and receiving data to/from Client.
 *
 * @author SHIRA
 */
public class mainServerABS extends AbstractServer {

	/** The controller. */
	Object controller;

	/** The connection. */
	private Connection connection;

	/** The Is DB connected. */
	private boolean IsDBConnected = false;

	/** The server controller. */
	private serverController serverController;

	/** The logged. */
	private ArrayList<User> logged;

	/**
	 * Instantiates a new main server ABS.
	 *
	 * @param port             the port
	 * @param serverController the server controller
	 */
	public mainServerABS(int port, serverController serverController) {
		super(port);
		this.serverController = serverController;
		logged = new ArrayList<User>();
	}

	/**
	 * This method check if user connected.
	 *
	 * @param toadd the user
	 * @return true, if successful
	 */
	public boolean checkUserConnected(User toadd) {
		if (logged.size() == 0) {
			logged.add(toadd);
			return true;
		}
		for (User i : logged) {
			if (toadd.getName().equals(i.getName()))
				return false;
		}
		logged.add(toadd);
		return true;
	}

	/**
	 * This method connect to DB.
	 *
	 * @param connection the connection
	 */
	public void connectToDb(Connection connection) {
		this.connection = connection;
		IsDBConnected = true;
	}

	/**
	 * This method Removes the connection.
	 *
	 * @param toadd the user
	 */
	public void removeConnected(User toadd) {
		for (User i : logged)
			if (toadd.getName().equals(i.getName()))
				logged.remove(i);
	}

	/**
	 * Handles a message sent from the Client to this Server.
	 *
	 * @param msg    - the message sent.
	 * @param client the client
	 */
	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		DBmessage dbm = (DBmessage) msg;
		switch (dbm.getType()) {
		case Login: {
			loginSController loginSController = new loginSController(dbm, connection);
			try {
				DBSmessage dbs = loginSController.CheckLogIn();
				if (dbs.getType().equals(MessageTypeS.LoginFail))
					client.sendToClient(dbs);
				User toadd = (User) dbs.getObjs().get(0);
				if (checkUserConnected(toadd) == false) {
					dbs.setType(MessageTypeS.LoginFailConnected);
				}
				client.sendToClient(dbs);
			} catch (IOException e) {
			}
			break;
		}
		case logout: {
			User toremove = (User) dbm.getObjs().get(0);
			this.removeConnected(toremove);
		}
		case homeRequestNum: {
			UserShowRequestsSController userShowRequestsSController = new UserShowRequestsSController(dbm, connection);
			try {
				client.sendToClient(userShowRequestsSController.numOfRequest());
			} catch (IOException e) {
			}
			break;
		}
		case MhomeRequestNum: {
			UserShowRequestsSController userShowRequestsSController = new UserShowRequestsSController(dbm, connection);
			try {
				client.sendToClient(userShowRequestsSController.MnumOfRequest());
			} catch (IOException e) {
			}
			break;
		}
		case IThomeRequestNum: {
			ITShowRequestsSController itShowRequestsSController = new ITShowRequestsSController(dbm, connection);
			try {
				client.sendToClient(itShowRequestsSController.numOfRequest());
			} catch (IOException e) {
			}
			break;
		}
		case ITrequestDaysLeft: {
			ITHandleRequestSController itHandleRequestSController = new ITHandleRequestSController(dbm, connection);
			try {
				client.sendToClient(itHandleRequestSController.numOfDays());
			} catch (IOException e) {
			}
			break;
		}
		case ShowReqUser: {
			UserShowRequestsSController userShowRequestsSController = new UserShowRequestsSController(dbm, connection);
			try {
				client.sendToClient(userShowRequestsSController.showRequest());
			} catch (IOException e) {
			}
			break;
		}
		case showRequestDetailsUser: {
			UserRequestDetailsSController userRequestDetailsSController = new UserRequestDetailsSController(dbm,
					connection);
			try {
				client.sendToClient(userRequestDetailsSController.showRequestDetails());
			} catch (IOException e) {
			}
			break;
		}
		case showRequestDetailsIT: {
			ITRequestDetailsSController itRequestDetailsSController = new ITRequestDetailsSController(dbm, connection);
			try {
				client.sendToClient(itRequestDetailsSController.showRequestDetails());
			} catch (IOException e) {
			}
			break;
		}
		case showattachfileIT: {
			ITRequestDetailsSController itRequestDetailsSController = new ITRequestDetailsSController(dbm, connection);
			try {
				client.sendToClient(itRequestDetailsSController.showRequestDetailsFile());
			} catch (IOException e) {
			}
			break;
		}
		case approveEvaluationReport: {
			ITCCCEvaluationReportSController ITCCCEvaluationReportSController = new ITCCCEvaluationReportSController(
					dbm, connection);
			try {
				client.sendToClient(ITCCCEvaluationReportSController.approveEvaluationReport());
			} catch (IOException e) {
			}
			break;
		}

		case denyEvaluationReport: {
			ITCCCEvaluationReportSController ITCCCEvaluationReportSController = new ITCCCEvaluationReportSController(
					dbm, connection);
			try {
				client.sendToClient(ITCCCEvaluationReportSController.denyEvaluationReport());
			} catch (IOException e) {
			}
			break;
		}
		case ITchangeCompleted: {
			ITHandleRequestSController itHandleRequestSController = new ITHandleRequestSController(dbm, connection);
			try {
				client.sendToClient(itHandleRequestSController.saveChangeCompleted());
			} catch (IOException e) {
			}
			break;
		}
		case ITTestApproval: {
			ITHandleRequestSController itHandleRequestSController = new ITHandleRequestSController(dbm, connection);
			try {
				client.sendToClient(itHandleRequestSController.saveTestApproval());
			} catch (IOException e) {
			}
			break;
		}

		case ITsubmitRequireMoreInfo: {
			ITCCCRequestMoreInfoSController ITCCCRequestMoreInfoSController = new ITCCCRequestMoreInfoSController(dbm,
					connection);
			try {
				client.sendToClient(ITCCCRequestMoreInfoSController.submitRequireMoreInfo());
			} catch (IOException e) {
			}
		}
			break;

		case viewTime: {
			SupervisorShowRequestsSController SupervisorShowRequestsSController = new SupervisorShowRequestsSController(
					dbm, connection);

			try {

				client.sendToClient(SupervisorShowRequestsSController.viewExtensionReport());

			} catch (IOException e) {
			}
			break;
		}
		case approveTime: {
			SupervisorShowRequestsSController SupervisorShowRequestsSController = new SupervisorShowRequestsSController(
					dbm, connection);
			try {
				client.sendToClient(SupervisorShowRequestsSController.approvevTime());
			} catch (IOException e) {
			}
			break;
		}
		case ITjobInReq: {
			ITHandleRequestSController itHandleRequestSController = new ITHandleRequestSController(dbm, connection);
			try {
				client.sendToClient(itHandleRequestSController.getITjob());
			} catch (IOException e) {
			}
			break;
		}
		case makeActiveSuClo: {
			ITManagerReportsSController ITManagerReportsSController = new ITManagerReportsSController(dbm, connection);
			try {
				client.sendToClient(ITManagerReportsSController.makeActiveSuClo());
			} catch (IOException e) {
			}
			break;
		}
		case makeDelays: {
			ITManagerReportsSController ITManagerReportsSController = new ITManagerReportsSController(dbm, connection);
			try {
				client.sendToClient(ITManagerReportsSController.makeDelays());
			} catch (IOException e) {
			}
			break;
		}

		case makePerformenct: {
			ITManagerReportsSController ITManagerReportsSController = new ITManagerReportsSController(dbm, connection);
			try {
				client.sendToClient(ITManagerReportsSController.makePerformenct());
			} catch (IOException e) {
			}
			break;
		}

		case ITgetReqStage: {
			ITExtensionRequestSController itExtensionRequestSController = new ITExtensionRequestSController(dbm,
					connection);
			try {
				client.sendToClient(itExtensionRequestSController.getITreqStage());
			} catch (IOException e) {
			}
			break;
		}
		case ITgetLocation: {
			ITMeaningAssessmentEvaluationReportSController itMeaningAssessmentEvaluationReportController = new ITMeaningAssessmentEvaluationReportSController(
					dbm, connection);
			try {
				client.sendToClient(itMeaningAssessmentEvaluationReportController.getITreqLocation());
			} catch (IOException e) {
			}
			break;
		}
		case ITshowEvaluationReport: {
			ITCCCEvaluationReportSController itCCCEvaluationReportSController = new ITCCCEvaluationReportSController(
					dbm, connection);
			try {
				client.sendToClient(itCCCEvaluationReportSController.showEvaluationReport());
			} catch (IOException e) {
			}

			break;
		}
		case ITFailurReport: {
			ITTestFailurReportSController itTestFailurReportSController = new ITTestFailurReportSController(dbm,
					connection);
			try {
				client.sendToClient(itTestFailurReportSController.submitFailurReport());
			} catch (IOException e) {
			}
			break;
		}
		case SearchReqUser: {
			UserShowRequestsSController userShowRequestsSController = new UserShowRequestsSController(dbm, connection);
			try {
				client.sendToClient(userShowRequestsSController.showSPRequest());
			} catch (IOException e) {
			}

			break;
		}
		case SearchReqIT: {
			ITShowRequestsSController itshowRequestsSController = new ITShowRequestsSController(dbm, connection);
			try {
				client.sendToClient(itshowRequestsSController.showSPRequest());
			} catch (IOException e) {
			}

			break;
		}
		case ShowEmployeeList: {
			ShowEmployeeListController ShowEmployeeListController = new ShowEmployeeListController(dbm, connection);
			try {
				client.sendToClient(ShowEmployeeListController.showEmployee());
			} catch (IOException e) {
			}

			break;
		}
		case ITShowEmployeeList: {
			ITHandleRequestSController itHandleRequestSController = new ITHandleRequestSController(dbm, connection);
			try {
				client.sendToClient(itHandleRequestSController.getListOfIT());
			} catch (IOException e) {
			}

			break;
		}
		case AddRequest: {
			UserSAddRequestController UserSAddRequestController = new UserSAddRequestController(dbm, connection);
			try {
				client.sendToClient(UserSAddRequestController.submitRequest());
			} catch (IOException e) {
			}
			break;
		}
		case supervisorHomeRequestNum: {
			SupervisorShowRequestsSController supervisorShowRequestsSController = new SupervisorShowRequestsSController(
					dbm, connection);
			try {
				client.sendToClient(supervisorShowRequestsSController.numOfRequest());
			} catch (IOException e) {
			}
			break;
		}
		case SearchReqSupervisor: {
			superviserRequestShowController superviserRequestShowController = new superviserRequestShowController(dbm,
					connection);
			try {
				client.sendToClient(superviserRequestShowController.getSPRequestToShow());
			} catch (IOException e) {
			}

			break;
		}
		case SearchReqManager: {
			superviserRequestShowController ManagerRequestShowController = new superviserRequestShowController(dbm,
					connection);
			try {
				client.sendToClient(ManagerRequestShowController.MgetSPRequestToShow());
			} catch (IOException e) {
			}

			break;
		}
		case ITSubmitEvaluationReport: {
			ITMeaningAssessmentEvaluationReportSController ITMeaningAssessmentEvaluationReportSController = new ITMeaningAssessmentEvaluationReportSController(
					dbm, connection);
			try {
				client.sendToClient(ITMeaningAssessmentEvaluationReportSController.submitEvaluationReport());
			} catch (IOException e) {
			}
			break;
		}
		case ITSaveTester: {
			ITHandleRequestSController itHandleRequestSController = new ITHandleRequestSController(dbm, connection);
			try {
				client.sendToClient(itHandleRequestSController.saveTester());
			} catch (IOException e) {
			}
			break;
		}
		case superviserRequestShow: {
			superviserRequestShowController superviserRequestShowController = new superviserRequestShowController(dbm,
					connection);
			try {
				client.sendToClient(superviserRequestShowController.getRequestToShow());
			} catch (IOException e) {
			}
			break;
		}
		case viewrecentreport: {

			ITManagerReportsSController ITManagerReportsSController = new ITManagerReportsSController(dbm, connection);
			try {

				client.sendToClient(ITManagerReportsSController.viewrecentreport());
			} catch (IOException e) {
			}
			break;
		}
		case MangerRequestShow: {
			superviserRequestShowController ManagerRequestShowController = new superviserRequestShowController(dbm,
					connection);
			try {
				client.sendToClient(ManagerRequestShowController.MgetRequestToShow());
			} catch (IOException e) {
			}
			break;
		}
		case suspendRequest: {
			superviserRequestShowController superviserRequestShowController = new superviserRequestShowController(dbm,
					connection);
			try {
				client.sendToClient(superviserRequestShowController.updateSuspendRequest());
			} catch (IOException e) {
			}

			break;
		}
		case MangerShowMessages: {
			MessagesShowController MessagesShowController = new MessagesShowController(dbm, connection);
			try {
				client.sendToClient(MessagesShowController.MgetMessagesToShow());
			} catch (IOException e) {
			}
			break;
		}
		case SuperviserShowMessages: {
			MessagesShowController SuperviserShowMessages = new MessagesShowController(dbm, connection);
			try {
				client.sendToClient(SuperviserShowMessages.SgetMessagesToShow());
			} catch (IOException e) {
			}
			break;
		}
		case changeExecuter: {

			SupervisorUpdateRequestSController supervisorUpdateRequestController = new SupervisorUpdateRequestSController(
					dbm, connection);
			try {
				client.sendToClient(supervisorUpdateRequestController.updatechangeExecuter());
			} catch (IOException e) {
			}

			break;
		}
		case changeExecuter2: {

			SupervisorUpdateRequestSController supervisorUpdateRequestController = new SupervisorUpdateRequestSController(
					dbm, connection);
			supervisorUpdateRequestController.updatechangeSaveInTable();
			break;
		}
		case closeRequest: {
			superviserRequestShowController superviserRequestShowController = new superviserRequestShowController(dbm,
					connection);
			try {
				client.sendToClient(superviserRequestShowController.updatecloseRequest());
			} catch (IOException e) {
			}

			break;
		}
		case ShowReqIT: {
			ITShowRequestsSController itShowRequestsSController = new ITShowRequestsSController(dbm, connection);
			try {
				client.sendToClient(itShowRequestsSController.showRequest());
			} catch (IOException e) {
			}
			break;
		}
		case superviserExtensionRequest: {
			superviserExtensionRequestController superviserExtensionRequestController = new superviserExtensionRequestController(
					dbm, connection);
			try {
				client.sendToClient(superviserExtensionRequestController.getReport());
			} catch (IOException e) {
			}
			break;
		}
		case superviserExtensionRequestAnswer: {
			superviserExtensionRequestController superviserExtensionRequestController = new superviserExtensionRequestController(
					dbm, connection);
			try {
				client.sendToClient(superviserExtensionRequestController.changeAnswer());
			} catch (IOException e) {
			}
			break;
		}
		case SupervisorListIt: {
			SupervisorUpdateRequestSController supervisorUpdateRequest = new SupervisorUpdateRequestSController(dbm,
					connection);
			try {
				client.sendToClient(supervisorUpdateRequest.getListOfIT());

			} catch (IOException e) {
			}
			break;
		}
		case SupervisorUpdateRequest: {
			SupervisorUpdateRequestSController supervisorUpdateRequest = new SupervisorUpdateRequestSController(dbm,
					connection);
			try {
				client.sendToClient(supervisorUpdateRequest.getReport());

			} catch (IOException e) {
			}
			break;
		}
		case SupervisorTimeRequest: {
			SupervisorUpdateRequestSController SupervisorUpdateTimeRequest = new SupervisorUpdateRequestSController(dbm,
					connection);
			try {
				client.sendToClient(SupervisorUpdateTimeRequest.getTimeReport());

			} catch (IOException e) {
			}
			break;
		}
		case SupervisorApproveEvluationTime: {
			SupervisorUpdateRequestSController SupervisorUpdateTimeRequest = new SupervisorUpdateRequestSController(dbm,
					connection);
			try {
				client.sendToClient(SupervisorUpdateTimeRequest.saveApproveEv());

			} catch (IOException e) {
			}
			break;
		}
		case SupervisorApproveExecutionTime: {
			SupervisorUpdateRequestSController SupervisorUpdateTimeRequest = new SupervisorUpdateRequestSController(dbm,
					connection);
			try {
				client.sendToClient(SupervisorUpdateTimeRequest.saveApproveEx());

			} catch (IOException e) {
			}
			break;
		}
		case SupervisorDenyEvluationTime: {
			SupervisorUpdateRequestSController SupervisorUpdateTimeRequest = new SupervisorUpdateRequestSController(dbm,
					connection);
			try {
				client.sendToClient(SupervisorUpdateTimeRequest.saveDenyEv());

			} catch (IOException e) {
			}
			break;
		}
		case SupervisorDenyExecutionTime: {
			SupervisorUpdateRequestSController SupervisorUpdateTimeRequest = new SupervisorUpdateRequestSController(dbm,
					connection);
			try {
				client.sendToClient(SupervisorUpdateTimeRequest.saveDenyEx());

			} catch (IOException e) {
			}
			break;
		}
		case AddExtensionRequest: {
			ITExtensionRequestSController itExtensionRequestSController = new ITExtensionRequestSController(dbm,
					connection);
			try {
				client.sendToClient(itExtensionRequestSController.submitRequest());
			} catch (IOException e) {
			}
			break;
		}

		case ITaddTimeEstimated: {
			ITHandleRequestSController ITHandleRequestController = new ITHandleRequestSController(dbm, connection);
			try {

				client.sendToClient(ITHandleRequestController.addTimeEstimated());
			} catch (IOException e) {
			}
			break;
		}
		case addTimeEstimatedPerformance: {
			ITHandleRequestSController ITHandleRequestController = new ITHandleRequestSController(dbm, connection);
			try {

				client.sendToClient(ITHandleRequestController.addTimeEstimatedPerformance());
			} catch (IOException e) {
			}
			break;
		}
		case showRequestDetailsITManager: {
			ITManagerRequestDetailsSController iTManagerRequestDetailsSController = new ITManagerRequestDetailsSController(
					dbm, connection);
			try {

				client.sendToClient(iTManagerRequestDetailsSController.showRequestDetails());
			} catch (IOException e) {
			}
			break;
		}
		case showattachfile: {
			superviserExtensionRequestController superviserExtensionRequestController = new superviserExtensionRequestController(
					dbm, connection);
			try {
				client.sendToClient(superviserExtensionRequestController.getAttachRequest());
			} catch (IOException e) {
			}
			break;
		}
		case showattachfileM: {
			ITManagerRequestDetailsSController iTManagerRequestDetailsSController = new ITManagerRequestDetailsSController(
					dbm, connection);
			try {

				client.sendToClient(iTManagerRequestDetailsSController.getAttachRequest());
			} catch (IOException e) {
			}
			break;
		}

		case showRequestDetailsSuperviser: {
			superviserExtensionRequestController superviserExtensionRequestController = new superviserExtensionRequestController(
					dbm, connection);
			try {
				client.sendToClient(superviserExtensionRequestController.getRequest());
			} catch (IOException e) {
			}
			break;
		}
		case MangerUpdateMessages: {
			MessagesShowController MessagesShowController = new MessagesShowController(dbm, connection);
			try {
				client.sendToClient(MessagesShowController.MUpdateMessagesToShow());
			} catch (IOException e) {
			}
			break;
		}
		case SuperviserUpdateMessages: {
			MessagesShowController MessagesShowController = new MessagesShowController(dbm, connection);
			try {
				client.sendToClient(MessagesShowController.SUpdateMessagesToShow());
			} catch (IOException e) {
			}
			break;
		}
		case renewRequest: {
			superviserRequestShowController ManagerRequestShowController = new superviserRequestShowController(dbm,
					connection);
			try {
				client.sendToClient(ManagerRequestShowController.updaterenewRequest());
			} catch (IOException e) {
			}

			break;
		}
		case SwitchPositions: {
			ShowEmployeeListController ShowEmployeeListController = new ShowEmployeeListController(dbm, connection);
			try {
				client.sendToClient(ShowEmployeeListController.SwitceEmployee());
			} catch (IOException e) {
			}
		}
		default:
			break;
		}
	}

	/**
	 * This method Start listening for connections.
	 */
	public void startServer() {
		try {
			this.listen();
		} catch (Exception ex) {
			serverController.showOnScreen("ERROR - Could not listen for clients!");
		}
	}

	/**
	 * This method Stop listening for connections.
	 */
	public void stopServer() {
		try {
			this.stopListening();
		} catch (Exception ex) {
			serverController.showOnScreen("ERROR - Could not listen for clients!");
		}
	}

	/**
	 * This method show in the screen that Server started to listening.
	 */
	protected void serverStarted() {
		serverController.showOnScreen("> Server listening for connections on port " + getPort());
	}

	/**
	 * This method show in the screen that Server stop to listening.
	 */
	protected void serverStopped() {
		serverController.showOnScreen("> Server has stopped listening for connections.");
	}

	/**
	 * This method checks if is DB connected.
	 *
	 * @return the checks if is DB connected
	 */
	public boolean getIsDBConnected() {
		return IsDBConnected;
	}
}
