package boundries;

import java.io.IOException;

import Enums.Position;
import controllers.MainAllControllers;
import entity.DBmessage;
import entity.User;
import javafx.event.ActionEvent;
import ocsf.client.AbstractClient;

public class mainClientABS extends AbstractClient
{
    private MainAllControllers MainAllControllers;
	public mainClientABS(String host, int port) throws IOException
	{
		super(host, port);
		MainAllControllers=MainAllControllers.getInstance();
		openConnection();
	}
	

	@Override
	protected void handleMessageFromServer(Object msg)
	{
		if(msg instanceof User)
		{
				try 
				{
					MainAllControllers.setWindowVar("userHome");
					MainAllControllers.changeWin();
					  MainAllControllers.setUser((User)msg);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
              }
			else
			{
				 MainAllControllers.badUser();		 
			}  			 	
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
