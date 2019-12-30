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
					case "student":
						topen="userHome";
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
			case SearchReqUser:
			{
				ArrayList<Object> send=(ArrayList<Object>) dbs.getObjs();
			     MainAllControllers.showUserSPReq(((ArrayList<Object>)send));
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
