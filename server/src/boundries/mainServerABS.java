package boundries;
import entity.ConnectToDB;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class mainServerABS extends AbstractServer
{
	    Object controller;
	    private ConnectToDB connection;
		public mainServerABS(int port,String password,String UserName,String BaseName)
		{
			super(port);
	    	connection=new ConnectToDB(password, UserName, BaseName);
			
		}
		@Override
		protected void handleMessageFromClient(Object msg, ConnectionToClient client)
		{
		    
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
}
