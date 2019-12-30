package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Enums.MessageTypeS;
import entity.DBSmessage;
import entity.DBmessage;

public class SupervisorShowRequestsSController 
{
	private Connection connection;
	private String user;
	
	public SupervisorShowRequestsSController(DBmessage msg,Connection connection)
	{
		ArrayList<Object> arry=msg.getObjs();
		this.user=(String)arry.get(0);
		this.connection=connection;
	}
	
	public DBSmessage numOfRequest()
	{
		Statement stmt;
		DBSmessage dbs;
		ArrayList<Object> toSend= new ArrayList<Object>();
		try 
		{
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM request");
			if(!rs.next())
				toSend.add(0);
			else
			    toSend.add(rs.getInt(1));
			dbs=new DBSmessage(MessageTypeS.supervisorHomeRequestNum,toSend);
			return dbs;
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}	
		return null;
	}
}
