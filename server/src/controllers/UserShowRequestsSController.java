package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.DBmessage;
import entity.Request;
import javafx.collections.ObservableList;

public class UserShowRequestsSController 
{
	private String user;
	private Connection connection;
	
	public UserShowRequestsSController(DBmessage msg,Connection connection)
	{
		this.user=(String)msg.getObjs().get(0);
		this.connection=connection;
	}
	
	public ArrayList<Request> showRequest()
	{
		Statement stmt;
		ArrayList<Request> toSend= new ArrayList<Request>();
		try 
		{
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM request WHERE userName='"+user+ "'");
			if(rs.next()==false)
  	 		{
  	 			return null;
  	 		}
			else
			{
				while(rs.next()!=false)
				{
					//toSend.add(new Request());
				}
				return toSend;
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
}
