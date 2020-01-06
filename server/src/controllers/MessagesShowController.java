package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Enums.MessageTypeS;
import entity.DBSmessage;
import entity.DBmessage;
import entity.Messages;
import entity.requestSuper;

public class MessagesShowController
{
	private Connection connection;
	private DBmessage db;
	public MessagesShowController(DBmessage db,Connection connection)
	{
		this.db=db;
		this.connection=connection;
	}
   public DBSmessage MgetMessagesToShow() 
   {
	    Statement stmt;
		DBSmessage dbs;
		String towho=(String) db.getObjs().get(0);
		ArrayList<Object> toSend= new ArrayList<Object>();
		try 
		{
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM messages WHERE toWho='"+towho+"'");
				while(rs.next()!=false)
				{
					Messages toAdd=new Messages(rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7));
					toSend.add(toAdd);
				}
				dbs=new DBSmessage(MessageTypeS.MangerShowMessages,toSend);
				return dbs;
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
