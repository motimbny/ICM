package boundries;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

import Enums.Position;
import controllers.MainAllControllers;
import entity.DBSmessage;
import entity.DBmessage;
import entity.Evluationreport;
import entity.RequestUser;
import entity.User;
import javafx.event.ActionEvent;
import ocsf.client.AbstractClient;

public class mainClientABS extends AbstractClient
{
    private MainAllControllers MainAllControllers;
	public mainClientABS(String host, int port) throws IOException
	{
		super(host, port);
		MainAllControllers=controllers.MainAllControllers.getInstance();
	}
	
	@Override
	protected void handleMessageFromServer(Object msg)
	{
			DBSmessage dbs=(DBSmessage)msg;
			switch(dbs.getType()) 
			{
			case Login:
				{
					String topen="";
					User set=(User)dbs.getObjs().get(0);
					switch (set.getstrPosition())
					{
					case "supervisor":
						topen="SupervisorHome";
						break;
					case "IT-manager":
						topen="ITManagerHome";
						break;
					case "IT":
						topen="ITHome";
						break;
					case "student":
						topen="userHome";
						break;
					case "lecturer":
						topen="userHome";
						break;
					case "college-emp":
						topen="userHome";
						break;
					}
					try 
						{
							MainAllControllers.setWindowVar(topen);
							MainAllControllers.changeWin();
							MainAllControllers.setUser(set);
						} catch (IOException e) 
						{
							e.printStackTrace();
						} 
					}
				   break;
			case LoginFail:
				{
					MainAllControllers.badUser();
				}
				break;
			case homeRequestNum:
			{
				ArrayList<Object> send=(ArrayList<Object>) dbs.getObjs();
				MainAllControllers.setHomeUserNum((int)send.get(0));
			}
			break;
			case MhomeRequestNum:
			{
				ArrayList<Object> send=(ArrayList<Object>) dbs.getObjs();
				MainAllControllers.setHomeMangerNum((int)send.get(0));
			}
			break;
			case IThomeRequestNum:
			{
				ArrayList<Object> send=(ArrayList<Object>) dbs.getObjs();
				MainAllControllers.setHomeITNum((int)send.get(0));
			}
			break;
			case supervisorHomeRequestNum:
			{
				ArrayList<Object> send=(ArrayList<Object>) dbs.getObjs();
				MainAllControllers.setHomeSupervisorNum((int)send.get(0));
			}
			break;
			case AddRequest:
				{
					 MainAllControllers.goodRequeSend();
				}
				break;
			case AddExtensionRequest:
			{
				ArrayList<Object> send=(ArrayList<Object>) dbs.getObjs();
				MainAllControllers.submitExtensionRequest((int)send.get(0));
			}
				break;
			case ShowReqUser:
				{
					ArrayList<Object> send=(ArrayList<Object>) dbs.getObjs();
				   MainAllControllers.showUserReq(((ArrayList<Object>)send));
				}
				break;
			case ShowReqIT:
			{
				ArrayList<Object> send=(ArrayList<Object>) dbs.getObjs();
			   MainAllControllers.showITReq(((ArrayList<Object>)send));
			}
			break;
			case ITjobInReq:
			{
				ArrayList<Object> send=(ArrayList<Object>) dbs.getObjs();
			   MainAllControllers.getITjobInReq(((ArrayList<Object>)send));
			}
			
			break;
			case ITgetReqStage:
			{
				ArrayList<Object> send=(ArrayList<Object>) dbs.getObjs();
			   MainAllControllers.getITReqStage(send);
			}
			break;
			case ITgetLocation:
			{
				ArrayList<Object> send=(ArrayList<Object>) dbs.getObjs();
			   MainAllControllers.getITReqLocation(send);
			}
			break;
			case ITshowEvaluationReport:
			{
				ArrayList<Object> send=(ArrayList<Object>) dbs.getObjs();
				MainAllControllers.showITEvaluationReport(send);
			}
			break;
			case ITFailurReport:
			{
				ArrayList<Object> send=(ArrayList<Object>) dbs.getObjs();
				MainAllControllers.submitFailurReport(send);
			}
			break;
			case ITsubmitRequireMoreInfo:
			{
				ArrayList<Object> send=(ArrayList<Object>) dbs.getObjs();
				MainAllControllers.submitRequireMoreInfo(send);
			}
			break;
			case getListOfIT:
			{
				System.out.println("im here getlist"+dbs.getObjs().size());
				ArrayList<Object> send=(ArrayList<Object>) dbs.getObjs();
			   MainAllControllers.getListOfIT(((ArrayList<Object>)send));
			}
			break;
			case showRequestDetailsUser:
			{
				ArrayList<Object> send=(ArrayList<Object>) dbs.getObjs();
			   MainAllControllers.showUserReqDetails(send);
			}
			break;
			case showRequestDetailsIT:
			{
				ArrayList<Object> send=(ArrayList<Object>) dbs.getObjs();
			   MainAllControllers.showITReqDetails(send);
			}
			break;
			case SearchReqUser:
			{
				ArrayList<Object> send=(ArrayList<Object>) dbs.getObjs();
			     MainAllControllers.showUserSPReq(((ArrayList<Object>)send));
			}
			break;
			case SearchReqIT:
			{
				ArrayList<Object> send=(ArrayList<Object>) dbs.getObjs();
			     MainAllControllers.showITSPReq(((ArrayList<Object>)send));
			}
			break;
			case SearchReqSupervisor:
			{
				ArrayList<Object> send=(ArrayList<Object>) dbs.getObjs();
			     MainAllControllers.showUserSPReq(((ArrayList<Object>)send));
			}
			break;
			case SearchReqManager:
			{
				ArrayList<Object> send=(ArrayList<Object>) dbs.getObjs();
			     MainAllControllers.showManSPReq(send);
			}
			break;
			case ITSubmitEvaluationReport:
			{
				ArrayList<Object> send=(ArrayList<Object>) dbs.getObjs();
			  MainAllControllers.submitEvaluationReport(send);
			}
			break;
			case addTimeEstimated:
			{
				ArrayList<Object> send=(ArrayList<Object>) dbs.getObjs();
				MainAllControllers.submitEvaluationTime(send);
			}
			break;
			case addTimeEstimatedPerformance:
			{
				ArrayList<Object> send=(ArrayList<Object>) dbs.getObjs();
				MainAllControllers.submitEvaluationTime(send);
			}
			break;
			case ShowEmployeeList:
			{
				ArrayList<Object> send=(ArrayList<Object>) dbs.getObjs();
				MainAllControllers.showManagerEmployeeList(send);
			}
			break;
			case superviserRequestShow:
			{
				ArrayList<Object> send=(ArrayList<Object>) dbs.getObjs();
				MainAllControllers.showSuperviserRequestList(send);
			}
			break;
			case MangerRequestShow:
			{
				ArrayList<Object> send=(ArrayList<Object>) dbs.getObjs();
				MainAllControllers.showMangerRequestList(send);
			}
			break;
			case SupervisorUpdateRequest:
			{
				ArrayList<Object> send=(ArrayList<Object>) dbs.getObjs();
				MainAllControllers.SupervisorUpdateRequest(send);
			}
			break;
			case showRequestDetailsSuperviser:
			{
				ArrayList<Object> send=(ArrayList<Object>) dbs.getObjs();
				MainAllControllers.SupervisorRequestDetailes(send);
			}
			break;
			case SupervisorTimeRequest:
			{
				ArrayList<Object> send=(ArrayList<Object>) dbs.getObjs();
				MainAllControllers.SupervisorTimeRequest(send);
			}
			break;
			case superviserExtensionRequestAnswer:
			{
				ArrayList<Object> send=(ArrayList<Object>) dbs.getObjs();
				MainAllControllers.showSuperviserExtensionRequestAnswer(send);
			}
			break;
			case superviserExtensionRequest:
			{
				ArrayList<Object> send=(ArrayList<Object>) dbs.getObjs();
				MainAllControllers.showSuperviserExtensionRequest(send);
			}
			break;
			case MangerShowMessages:
				{
					ArrayList<Object> send=(ArrayList<Object>) dbs.getObjs();
					MainAllControllers.ManagerShowMessage(send);
				}
			break;
			default:
				break;
			}
			
			
		 this.connectionClosed();		 
	}
	public void openConToServer()
	{
		try 
		{
			openConnection();
		} 
		catch (IOException e){}
	}
	public void handleMessageFromClientUI(DBmessage msg)  
	  {
	    try
	    {
	    	sendToServer(msg);
	    }
	    catch(IOException e)
	    {
	    }
	  
	 }
    

}
