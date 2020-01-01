package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Enums.MessageTypeS;
import Enums.StageName;
import entity.DBSmessage;
import entity.DBmessage;
import entity.RequestUser;

public class ITShowRequestsSController 
{
	private String user;
	private int userIdReq;
	private Connection connection;
	
	public ITShowRequestsSController(DBmessage msg,Connection connection)
	{
		ArrayList<Object> arry=msg.getObjs();
		this.user=(String)arry.get(0);
		if(arry.size()==2)
			userIdReq=(int)arry.get(1);
		this.connection=connection;
	}
	
	public DBSmessage showRequest()
	{
		Statement stmt;
		DBSmessage dbs;
		ArrayList<Object> toSend= new ArrayList<Object>();
		try 
		{
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, currentStatus, currentStage FROM requeststages WHERE itAppraiser='"+user+ "' OR itCCC1='"+user+"' OR itCCC2='"+user+"' OR itCCC3='"+user+"' OR itPerformanceLeader='"+user+"' OR itTester='"+user+"'"); 
				while(rs.next()!=false)
				{
					StageName name=null;
					switch(rs.getString(3))
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
					RequestUser toAdd=new RequestUser(rs.getInt(1),rs.getString(2),name);
					toSend.add(toAdd);
				}
				dbs=new DBSmessage(MessageTypeS.ShowReqIT,toSend);
				return dbs;
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}	
		return null;
	}
	
	
	public DBSmessage showSPRequest()
	{
		Statement stmt;
		DBSmessage dbs;
		ArrayList<Object> toSend= new ArrayList<Object>();
		try 
		{
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, currentStatus, currentStage FROM requeststages WHERE ( itAppraiser='"+user+ "' OR itCCC1='"+user+"' OR itCCC2='"+user+"' OR itCCC3='"+user+"' OR itPerformanceLeader='"+user+"' OR itTester='"+user+"' ) AND id="+userIdReq+"");
				while(rs.next()!=false)
				{
					StageName name=null;
					switch(rs.getString(3))
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
					RequestUser toAdd=new RequestUser(rs.getInt(1),rs.getString(2),name);
					toSend.add(toAdd);
				}
				dbs=new DBSmessage(MessageTypeS.SearchReqIT,toSend);
				return dbs;
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}	
		return null;
	}
	
	public DBSmessage numOfRequest()
	{
		Statement stmt;
		DBSmessage dbs;
		ArrayList<Object> toSend= new ArrayList<Object>();
		try 
		{
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM requeststages WHERE itAppraiser='"+user+"' OR itCCC1='"+user+"' OR itCCC2='"+user+"' OR itCCC3='"+user+"' OR itPerformanceLeader='"+user+"' OR itTester='"+user+"'");
			if(!rs.next())
				toSend.add(0);
			else
			    toSend.add(rs.getInt(1));
			dbs=new DBSmessage(MessageTypeS.IThomeRequestNum,toSend);
				return dbs;
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}	
		return null;
	}
}
