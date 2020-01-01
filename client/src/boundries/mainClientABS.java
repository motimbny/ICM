package boundries;

import java.io.IOException;
import java.util.ArrayList;

import Enums.Position;
import controllers.MainAllControllers;
import entity.DBSmessage;
import entity.DBmessage;
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
						topen="ITmanHome";
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
			case IThomeRequestNum:
			{
				ArrayList<Object> send=(ArrayList<Object>) dbs.getObjs();
				MainAllControllers.setHomeITNum((int)send.get(0));
			}
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
			   MainAllControllers.getITReqStage(((ArrayList<Object>)send));
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
			case ShowEmployeeList:
			{
				ArrayList<Object> send=(ArrayList<Object>) dbs.getObjs();
				MainAllControllers.showSuperviserEmployeeList(send);
			}
			break;
			case superviserRequestShow:
			{
				ArrayList<Object> send=(ArrayList<Object>) dbs.getObjs();
				MainAllControllers.showSuperviserRequestList(send);
			}
			break;
			case superviserEvluationReport:
			{
				ArrayList<Object> send=(ArrayList<Object>) dbs.getObjs();
				MainAllControllers.showSuperviserReportEV(send);
			}
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
