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
import entity.Request;
import entity.extensionrequest;

public class superviserExtensionRequestController
{
    private Connection connection;
    private int numReport;
    private DBmessage db;
	public superviserExtensionRequestController(DBmessage dbm, Connection connection) 
	{
		this.db=dbm;
		this.connection=connection;
		this.numReport=(int) dbm.getObjs().get(0);
	}
	public DBSmessage getReport()
	{
		Statement stmt;
		DBSmessage dbs;
		extensionrequest ev = null;
		ArrayList<Object> toSend= new ArrayList<Object>();
		try 
		{
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM extensionrequest WHERE id='"+numReport+ "'");
			if(rs.next()!=false)
				ev=new extensionrequest(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6));
			toSend.add(ev);
			dbs=new DBSmessage(MessageTypeS.superviserExtensionRequest,toSend);
				return dbs;
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}	
		return null;
	}
	public DBSmessage getRequest()
	{
		Statement stmt;
		DBSmessage dbs;
		ArrayList<Object> toSendA= new ArrayList<Object>();
		Request tosend;
		try 
		{
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, userSubFullName, infoSystem, currentStatus,currentStage, desExtSit, wantedChange FROM request WHERE id='"+numReport+ "'");
				while(rs.next()!=false)
				{
					StageName name=null;
					switch(rs.getString(5))
					{
					case "supervisorApprovel":
						name=StageName.supervisorApprovel;
						break;
					case "waitingEvaluationTime":
						name=StageName.waitingEvaluationTime;
						break;
					case "waitingSupervisorApproveEvaluationTime":
						name=StageName.waitingSupervisorApproveEvaluationTime;
						break;
					case "meaningAssessment":
						name=StageName.meaningAssessment;
						break;
					case "waitingExecutionTime":
						name=StageName.waitingExecutionTime;
					break;
					case "waitingSupervisorApproveExecutionTime":
						name=StageName.waitingSupervisorApproveExecutionTime;
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
					case "Closed":
						name=StageName.Closed;
						break;
					}
					tosend=new Request(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),name,rs.getString(6),rs.getString(7));
					toSendA.add(tosend);
				}
				dbs=new DBSmessage(MessageTypeS.showRequestDetailsSuperviser,toSendA);
				return dbs;
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}	
		return null;
	}
	public DBSmessage changeAnswer()
	{
		Statement stmt;
		DBSmessage dbs;
		String ans=(String) db.getObjs().get(1);
		ArrayList<Object> toSend= new ArrayList<Object>();
		try 
		{
			stmt = connection.createStatement();
			int rs = stmt.executeUpdate("UPDATE extensionrequest SET status='"+ans+"' WHERE id="+numReport+"");
			dbs=new DBSmessage(MessageTypeS.superviserExtensionRequestAnswer,toSend);
				return dbs;
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}	
		return null;
	}
}
