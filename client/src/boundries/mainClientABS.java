package boundries;

import java.io.IOException;

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
			MainAllControllers.setWindowVar("userHome");
			MainAllControllers.changeWin();
		}
		else
		   System.out.println("bad");
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
