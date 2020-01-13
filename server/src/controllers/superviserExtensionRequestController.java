package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Enums.MessageTypeS;
import Enums.StageName;
import entity.DBSmessage;
import entity.DBmessage;
import entity.Request;
import entity.User;
import entity.extensionrequest;

public class superviserExtensionRequestController
{
    private Connection connection;
    private int numReport;
    private DBmessage db;
    private User manager;
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
		DBSmessage dbs = null;
		int id=(int) db.getObjs().get(0);
		String ans=(String) db.getObjs().get(1);
		String stage=(String) db.getObjs().get(2);
		int timeToAdd=Integer.parseInt((String) db.getObjs().get(3));
		String nameTime=null;
		ArrayList<Object> toSend= new ArrayList<Object>();
		try 
		{
			stmt = connection.createStatement();
			stmt.executeUpdate("UPDATE extensionrequest SET status='"+ans+"' WHERE id="+id+"");
			
 			stmt.executeUpdate("UPDATE requeststages SET currentStatus='Active' WHERE id="+id+"");
			stmt.executeUpdate("UPDATE request SET currentStatus='Active' WHERE id="+id+"");
			dbs=new DBSmessage(MessageTypeS.superviserExtensionRequestAnswer,toSend);
				
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		if(ans.equals("approve"))
		{
			try {
				this.sendMessageToManager(" "+id);
				
				switch(stage)
				{
				case "meaningAssessment":
					nameTime = "timeEvaluation";
					break;
				case "examinationAndDecision":
					nameTime = "timeExaminationDecision";
					break;
				case "execution":
					nameTime = "timePerform";
					break;
				case "testing":
					nameTime = "timeTest";
					break;
				}
				
				int toUp=this.getTime(nameTime, id)+timeToAdd;
				stmt = connection.createStatement();		
				stmt.executeUpdate("UPDATE requeststages SET "+nameTime+"="+ toUp+" WHERE id="+id+"");
				
				
			} catch (SQLException e) {	}
				
		}
		
		
		
		return dbs;
	}
	
	public void sendMessageToManager(String reqid) throws SQLException
	   {
		findmanager();
		   PreparedStatement mToAdd;
		   mToAdd = connection.prepareStatement("INSERT INTO messages VALUES(?,?,?,?,?,?)");
		   mToAdd.setString(1,manager.getName());
		   mToAdd.setString(2,"Supervisor");
		   mToAdd.setString(3,reqid);
		   mToAdd.setString(4,reqid+" extension request was approved");
		   mToAdd.setString(5,java.time.LocalDate.now().toString());
		   mToAdd.setInt(6,0);
		   mToAdd.executeUpdate();	
		   mToAdd.close();
		}
	
	 private void findmanager()
		{
			Statement stmt;
			User toAdd=null;
			try 
			{
				stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE position='IT-manager'");
					while(rs.next()!=false)
					{
					  manager=new User(rs.getString(1),Integer.toString(rs.getInt(2)),rs.getString(3));
					}
					rs.close();
			}
			catch(Exception e) {}	
		}
	 
	 public int getTime(String stage, int id)
	 {
		 Statement stmt;
		 int num=0;
		 try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT "+stage+" FROM requeststages WHERE id="+id+"");
				while(rs.next()!=false)
				{
				  num=rs.getInt(1);
				}
				rs.close();
			
			
		} catch (SQLException e) {	}
			return num;
	 }
}
