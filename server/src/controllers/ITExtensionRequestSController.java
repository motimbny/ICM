package controllers;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import Enums.MessageTypeS;
import entity.DBSmessage;
import entity.DBmessage;

/**
 * The Class ITExtensionRequestSController.
 */
public class ITExtensionRequestSController {
	
	/** The user. */
	private String user;
	
	/** The req id. */
	private int reqId;
	
	/** The connection. */
	private Connection connection;
	
	/** The stage. */
	private String stage;
	
	/** The reason. */
	private String reason;
	
	/** The time to add. */
	private int timeToAdd;
	
	/** The db. */
	private DBmessage db; 

	/**
	 * Instantiates a new IT extension request S controller.
	 *
	 * @param msg the msg
	 * @param connection the connection
	 */
	public ITExtensionRequestSController(DBmessage msg, Connection connection) {
		this.db=msg;
		this.connection = connection;
	}
		
	/**
	 * Gets the i treq stage.
	 *
	 * @return the i treq stage
	 */
	public DBSmessage getITreqStage() {
		ArrayList<Object> arr = db.getObjs();
		this.user=(String)arr.get(0);
		this.reqId= (int) arr.get(1);
		Statement stmt;
		DBSmessage dbs;
		ArrayList<Object> toSend = new ArrayList<Object>();
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT currentStage FROM requeststages WHERE ( itAppraiser='"
							+ user + "' OR itCCC1='" + user + "' OR itCCC2='" + user + "' OR itCCC3='" + user
							+ "' OR itPerformanceLeader='" + user + "' OR itTester='" + user + "' ) AND id=" + reqId+ "");
			while (rs.next() != false) 
			{
				toSend.add(rs.getString(1));
			}
			dbs = new DBSmessage(MessageTypeS.ITgetReqStage, toSend);
			return dbs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Submit request.
	 *
	 * @return the DB smessage
	 */
	public DBSmessage submitRequest()
	{
		ArrayList<Object> arr = db.getObjs();
		this.reqId=(int)arr.get(0);
		this.stage=(String)arr.get(1);
		this.user=(String)arr.get(2);
		this.timeToAdd=(int)arr.get(3);
		this.reason=(String)arr.get(4);
	   PreparedStatement ps;
	   DBSmessage dbs;
   	   ArrayList<Object> arry=new ArrayList<Object>();
 		try 
 		  {

 			ps = connection.prepareStatement("INSERT INTO extensionrequest VALUES(?,?,?,?,?,?,?)");
 			ps.setInt(1, this.reqId);
 			ps.setString(2, this.stage);
 			ps.setString(3, this.user);
 			ps.setString(4, this.reason);
 			ps.setInt(5, this.timeToAdd);
 			ps.setString(6, "null");
 			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
 			ps.setString(7, formatter.format(date));
 			ps.executeUpdate();	
 			ps.close();
 			arry.add(1);
 		  }
 		 catch (SQLException e) 
 		     {
 		    	e.printStackTrace();
 		    	arry.add(0);
 		     }
    	dbs=new DBSmessage(MessageTypeS.AddExtensionRequest, arry);
		return dbs;
   }
}
