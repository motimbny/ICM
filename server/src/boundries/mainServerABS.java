package boundries;
import java.io.IOException;
import java.sql.Connection;

import controllers.loginSController;
import entity.DBmessage;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class mainServerABS extends AbstractServer
{
	    Object controller;
	    private Connection connection;
	    private boolean IsDBConnected=false;
		public mainServerABS(int port,Connection connection)
		{
			super(port);
	    	this.connection=connection;
	    	if(!connection.equals(null))
	    		IsDBConnected=true;
		}
		@Override
		protected void handleMessageFromClient(Object msg, ConnectionToClient client)
		{
		    DBmessage dbm=(DBmessage)msg;
		    switch(dbm.getType()) 
		    {
		       case Login:
		       {
		    	   loginSController loginSController=new loginSController(dbm,connection);
		    	   try 
		    	   {
					client.sendToClient(loginSController.CheckLogIn());
				    } 
		    	   catch (IOException e) {}
		    	   break;
		       }
			default:
				break;
		    }
		}
		 public void startServer()
		 {
			 try 
			    {
			      listen(); //Start listening for connections
			    } 
			    catch (Exception ex) 
			    {
			      System.out.println("ERROR - Could not listen for clients!");
			    }
		 }
	    protected void serverStarted()
	    {
	    	System.out.println("> Server listening for connections on port " + getPort());
	    }

	    protected void serverStopped()
	    {
	    	System.out.println("> Server has stopped listening for connections.");
	    }
	    public boolean getIsDBConnected()
	    {
	    	return IsDBConnected;
	    }
}
