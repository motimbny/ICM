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

public class SupervisorShowRequestsSController 
{
	private Connection connection;
	private String user;
	private int userIdReq;
	private DBmessage msg;
	
	public SupervisorShowRequestsSController(DBmessage msg,Connection connection)
	{
		this.msg=msg;
		
		this.connection=connection;
	}

	public DBSmessage showSPRequest()
	{
		ArrayList<Object> arry=msg.getObjs();
		this.user=(String)arry.get(0);
		Statement stmt;
		DBSmessage dbs;
		ArrayList<Object> toSend= new ArrayList<Object>();
		try 
		{
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, currentStatus, currentStage FROM request WHERE id="+userIdReq+"");
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
				dbs=new DBSmessage(MessageTypeS.SearchReqUser,toSend);
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


	public DBSmessage viewExtensionReport() {
		Statement stmt;
		DBSmessage dbs;
		int timeEvaluation;
		ArrayList<Object> toSend= new ArrayList<Object>();
		try 
		{
			stmt = connection.createStatement();
			int id=(int)msg.getObjs().get(0);
			ResultSet rs = stmt.executeQuery("SELECT * FROM requeststages WHERE id="+id+"");
				while(rs.next()!=false)
				{
					
					timeEvaluation=rs.getInt(10);
					if(timeEvaluation==0)
					{
						dbs=new DBSmessage(MessageTypeS.viewTime,null);
						return dbs;
					}
				}
				toSend.add(userIdReq);
				dbs=new DBSmessage(MessageTypeS.viewTime,toSend);
				return dbs;
		}
		catch (SQLException e)
		{

			e.printStackTrace();
		}
		return null;	
	}
	
	public DBSmessage approvevTime()
	{
		Statement stmt;
		DBSmessage dbs;
		int reqId=(int)msg.getObjs().get(0);
		ArrayList<Object> toSend= new ArrayList<Object>();
		try {
			toSend.add(reqId);
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT currentStage FROM requeststages WHERE id="+reqId+"");
			while(rs.next()!=false)
			{
				if(rs.getString(1).equals("waitingSupervisorApproveEvaluationTime"))
				{
					toSend.add(1);
				}
				else
				{
					toSend.add(0);
				}
				dbs=new DBSmessage(MessageTypeS.approveTime,toSend);
				return dbs;
			}
		} catch (SQLException e) {}
		return null;
	}
}
