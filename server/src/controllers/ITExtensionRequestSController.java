package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Enums.MessageTypeS;
import entity.DBSmessage;
import entity.DBmessage;

public class ITExtensionRequestSController {
	private String user;
	private int reqId;
	private Connection connection;
	private String stage;
	private String reason;
	private int timeToAdd;
	private DBmessage db; 

	public ITExtensionRequestSController(DBmessage msg, Connection connection) {
		this.db=msg;
		this.connection = connection;
	}
		
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

	public DBSmessage submitRequest()
	{
		ArrayList<Object> arr = db.getObjs();
		System.out.println("7777");
		this.reqId=(int)arr.get(0);
		System.out.println("0000");
		this.stage=(String)arr.get(1);
		this.user=(String)arr.get(2);
		this.timeToAdd=(int)arr.get(3);
		this.reason=(String)arr.get(4);
	   PreparedStatement ps;
	   DBSmessage dbs;
   	   ArrayList<Object> arry=new ArrayList<Object>();
 		try 
 		  {
 			System.out.println("lalala");
 			ps = connection.prepareStatement("INSERT INTO extensionrequest VALUES(?,?,?,?,?,?)");
 			ps.setInt(1, this.reqId);
 			ps.setString(2, this.stage);
 			ps.setString(3, this.user);
 			ps.setString(4, this.reason);
 			ps.setInt(5, this.timeToAdd);
 			ps.setString(6, "null");
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
