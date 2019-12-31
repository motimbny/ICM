package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Enums.MessageTypeS;
import entity.DBSmessage;
import entity.DBmessage;
import entity.requestSuper;

public class superviserRequestShowController
{
	private Connection connection;
	private int numRequest;
	private DBmessage msg;
	public superviserRequestShowController(DBmessage msg,Connection connection)
	{
		this.connection=connection;
		this.msg=msg;
	}
	public DBSmessage getRequestToShow()
	{
		Statement stmt;
		DBSmessage dbs;
		ArrayList<Object> toSend= new ArrayList<Object>();
		try 
		{
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id,currentStatus,currentStage FROM request");
				while(rs.next()!=false)
				{
					requestSuper toAdd=new requestSuper(rs.getInt(1), rs.getString(2), rs.getString(3));
					toSend.add(toAdd);
				}
				dbs=new DBSmessage(MessageTypeS.superviserRequestShow,toSend);
				return dbs;
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public DBSmessage getSPRequestToShow()
	{
		int num=(int) msg.getObjs().get(0);
		Statement stmt;
		DBSmessage dbs;
		ArrayList<Object> toSend= new ArrayList<Object>();
		try 
		{
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id,currentStatus,currentStage FROM request WHERE id="+num+"");
				while(rs.next()!=false)
				{
					requestSuper toAdd=new requestSuper(rs.getInt(1), rs.getString(2), rs.getString(3));
					toSend.add(toAdd);
				}
				dbs=new DBSmessage(MessageTypeS.superviserRequestShow,toSend);
				return dbs;
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

}
