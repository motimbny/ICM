package boundries;

import java.io.IOException;
import java.sql.Connection;

import controllers.ITCCCEvaluationReportSController;
import controllers.ITExtensionRequestSController;
import controllers.ITHandleRequestSController;
import controllers.ITMeaningAssessmentEvaluationReportSController;
import controllers.ITRequestDetailsSController;
import controllers.ITShowRequestsSController;
import controllers.ITTestFailurReportSController;
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

import entity.DBmessage;

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class mainServerABS extends AbstractServer {
	Object controller;
	private Connection connection;
	private boolean IsDBConnected = false;
	private serverController serverController;

	public mainServerABS(int port, serverController serverController) {
		super(port);
		this.serverController = serverController;
	}

	public void connectToDb(Connection connection) {
		this.connection = connection;
		IsDBConnected = true;
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {

		DBmessage dbm = (DBmessage) msg;
		switch (dbm.getType()) 
		{
			case Login: {
				loginSController loginSController = new loginSController(dbm, connection);
				try {
					client.sendToClient(loginSController.CheckLogIn());
				} catch (IOException e) {
				}
				break;
			}
			case homeRequestNum: {
				UserShowRequestsSController userShowRequestsSController = new UserShowRequestsSController(dbm, connection);
				try {
					client.sendToClient(userShowRequestsSController.numOfRequest());
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
			case ITjobInReq: {
				ITHandleRequestSController itHandleRequestSController = new ITHandleRequestSController(dbm, connection);
				try {
					client.sendToClient(itHandleRequestSController.getITjob());
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
			case ITFailurReport:
			{
				ITTestFailurReportSController itTestFailurReportSController=new ITTestFailurReportSController(dbm, connection);
				try {
					client.sendToClient(itTestFailurReportSController.submitFailurReport());
				} catch (IOException e) {}
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
			case superviserRequestShow: {
				superviserRequestShowController superviserRequestShowController = new superviserRequestShowController(dbm,
						connection);
				try {
					client.sendToClient(superviserRequestShowController.getRequestToShow());
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
			case changeExecuter: {
	
				SupervisorUpdateRequestSController supervisorUpdateRequestController = new SupervisorUpdateRequestSController(
						dbm, connection);
				try {
					client.sendToClient(supervisorUpdateRequestController.updatechangeExecuter());
				} catch (IOException e) {
				}
	
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
		case superviserExtensionRequestAnswer:
		{
			superviserExtensionRequestController superviserExtensionRequestController = new superviserExtensionRequestController(
					dbm, connection);
			try {
				client.sendToClient(superviserExtensionRequestController.changeAnswer());
			} catch (IOException e) {
			}
			break;
		}
		case SupervisorListIt:
		{
			SupervisorUpdateRequestSController supervisorUpdateRequest = new SupervisorUpdateRequestSController(dbm,
					connection);
			try {
				client.sendToClient(supervisorUpdateRequest.getListOfIT());

			} catch (IOException e) {
			}
			break;
		}
		case SupervisorUpdateRequest:
		{
			SupervisorUpdateRequestSController supervisorUpdateRequest = new SupervisorUpdateRequestSController(dbm,
					connection);
			try {
				client.sendToClient(supervisorUpdateRequest.getReport());

			} catch (IOException e) {
			}
			break;
		}
		case SupervisorTimeRequest: 
		{
			SupervisorUpdateRequestSController SupervisorUpdateTimeRequest = new SupervisorUpdateRequestSController(dbm,
					connection);
			try {
				client.sendToClient(SupervisorUpdateTimeRequest.getTimeReport());

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
		case showRequestDetailsSuperviser:
		{
			superviserExtensionRequestController superviserExtensionRequestController = new superviserExtensionRequestController(
					dbm, connection);
			try {
				client.sendToClient(superviserExtensionRequestController.getRequest());
			} catch (IOException e) {
			}
			break;
		}
		default:
			break;
		}
	}

	public void startServer() {
		try {
			this.listen(); // Start listening for connections
		} catch (Exception ex) {
			serverController.showOnScreen("ERROR - Could not listen for clients!");
		}
	}

	public void stopServer() {
		try {
			this.stopListening(); // Start listening for connections
		} catch (Exception ex) {
			serverController.showOnScreen("ERROR - Could not listen for clients!");
		}
	}

	protected void serverStarted() {
		serverController.showOnScreen("> Server listening for connections on port " + getPort());
	}

	protected void serverStopped() {
		serverController.showOnScreen("> Server has stopped listening for connections.");
	}

	public boolean getIsDBConnected() {
		return IsDBConnected;
	}
}
