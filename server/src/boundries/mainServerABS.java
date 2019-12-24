package boundries;
import java.io.IOException;
import java.sql.Connection;

import controllers.UserShowRequestsSController;
import controllers.loginSController;
import controllers.serverController;
import entity.DBmessage;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class mainServerABS extends AbstractServer
{
	    Object controller;
	    private Connection connection;
	    private boolean IsDBConnected=false;
	    private serverController serverController;
		public mainServerABS(int port,serverController serverController)
		{
			super(port);
	    	this.serverController=serverController;
		}
		public void connectToDb(Connection connection)
		{
			this.connection=connection;
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
		       case ShowReqUser:
		       {
		    	   UserShowRequestsSController userShowRequestsSController=new UserShowRequestsSController(dbm,connection);
		    	   try {
					client.sendToClient(userShowRequestsSController.showRequest());
				} catch (IOException e) {}
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
			      this.listen(); //Start listening for connections
			    } 
			    catch (Exception ex) 
			    {
			    	serverController.showOnScreen("ERROR - Could not listen for clients!");
			    }
		 }
		 public void stopServer()
		 {
			 try 
			    {
			      this.stopListening(); //Start listening for connections
			    } 
			    catch (Exception ex) 
			    {
			    	serverController.showOnScreen("ERROR - Could not listen for clients!");
			    }
		 }
	    protected void serverStarted()
	    {
	    	serverController.showOnScreen("> Server listening for connections on port " + getPort());
	    }

	    protected void serverStopped()
	    {
	    	serverController.showOnScreen("> Server has stopped listening for connections.");
	    }
	    public boolean getIsDBConnected()
	    {
	    	return IsDBConnected;
	    }
}
