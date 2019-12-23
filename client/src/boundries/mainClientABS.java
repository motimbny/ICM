package boundries;

import java.io.IOException;

import entity.DBmessage;
import ocsf.client.AbstractClient;

public class mainClientABS extends AbstractClient
{
	public mainClientABS(String host, int port) throws IOException
	{
		super(host, port);
		openConnection();
	}
//
	@Override
	protected void handleMessageFromServer(Object msg)
	{
		
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
