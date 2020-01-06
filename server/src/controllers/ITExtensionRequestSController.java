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

	public ITExtensionRequestSController(DBmessage msg, Connection connection) {
		ArrayList<Object> arry = msg.getObjs();
		this.user = (String) arry.get(0);
		this.reqId = (int) arry.get(1);
		this.connection = connection;
		if(arry.size()==5)
		{
			this.stage=(String) arry.get(2);
			this.timeToAdd=(int) arry.get(3);
			this.reason=(String) arry.get(4);
		}
	}
	
	
	public DBSmessage getITreqStage() {
		Statement stmt;
		DBSmessage dbs;
		ArrayList<Object> toSend = new ArrayList<Object>();
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT id, currentStage FROM requeststages WHERE ( itAppraiser='"
							+ user + "' OR itCCC1='" + user + "' OR itCCC2='" + user + "' OR itCCC3='" + user
							+ "' OR itPerformanceLeader='" + user + "' OR itTester='" + user + "' ) AND id=" + reqId+ "");
			while (rs.next() != false) 
			{
				toSend.add(rs.getString(2));
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
	
	   PreparedStatement ps;
	   DBSmessage dbs;
   	   ArrayList<Object> arry=new ArrayList<Object>();
 		try 
 		  {
 			ps = connection.prepareStatement("INSERT INTO extensionrequest VALUES(?,?,?,?,?)");
 			ps.setInt(1, this.reqId);
 			ps.setString(2, this.stage);
 			ps.setString(3, this.user);
 			ps.setString(4, this.reason);
 			ps.setInt(5, this.timeToAdd);
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
