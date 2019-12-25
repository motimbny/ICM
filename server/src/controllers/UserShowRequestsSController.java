package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Enums.StageName;
import entity.DBmessage;
import entity.Request;
import entity.RequestUser;
import javafx.collections.ObservableList;

public class UserShowRequestsSController 
{
	private String user;
	private Connection connection;
	
	public UserShowRequestsSController(DBmessage msg,Connection connection)
	{
		ArrayList<Object> arry=msg.getObjs();
		this.user=(String)arry.get(0);
		this.connection=connection;
	}
	
	public ArrayList<Object> showRequest()
	{
		Statement stmt;
		ArrayList<Object> toSend= new ArrayList<Object>();
		toSend.add("UserShowRequests");
		try 
		{
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, currentStatus, currentStage FROM request WHERE userSubFullName='"+user+ "'");
			if(rs.next()==false)
  	 		{
  	 			return toSend;
  	 		}
			else
			{
				int i=0;
				while(rs.next()!=false)
				{
					StageName name=null;
					switch(rs.getString(i+2))
					{
					case "meaningAssessment":
						name=StageName.meaningAssessment;
						break;
					case "examinationAndDecision":
						name=StageName.examinationAndDecision;
						break;
					case "execution":
						name=StageName.execution;
						break;	
					case "testing":
						name=StageName.testing;
						break;		
					case "closing":
						name=StageName.closing;
						break;		
					}
					RequestUser toAdd=new RequestUser(rs.getInt(i),rs.getString(i+1),name);
					toSend.add(toAdd);
					i+=3;
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
