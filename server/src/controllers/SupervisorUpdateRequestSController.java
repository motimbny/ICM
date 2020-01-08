package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Enums.MessageTypeS;
import entity.DBSmessage;
import entity.DBmessage;
import entity.ITemployee;
import entity.extensionrequest;
import entity.updateRequest;
import javafx.scene.input.MouseEvent;

public class SupervisorUpdateRequestSController 
{
		private int flag=0;
	   private Connection connection;
	   private ArrayList<Object> list;
	    private DBmessage msg;
		public SupervisorUpdateRequestSController(DBmessage dbm, Connection connection) 
		{
			this.list=dbm.getObjs();
			this.msg=dbm;
			this.connection=connection;
		}
		public DBSmessage getReport()
		{
			int numReport=(int)list.get(0);
			Statement stmt;
			DBSmessage dbs;
			updateRequest up = null;
			ArrayList<Object> toSend= new ArrayList<Object>();
			try 
			{
				stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM requeststages WHERE id='"+numReport+ "'");
				while(rs.next()!=false)
				{
					up=new updateRequest(rs.getInt(1),rs.getString(4), rs.getString(8),rs.getString(9),rs.getInt(10),rs.getInt(11), rs.getInt(12),rs.getInt(13));
					if(rs.getString(3).equals("supervisorApprovel")&&flag==1)
					{
						stmt.executeUpdate("UPDATE request SET currentStage='waitingEvaluationTime' WHERE id="+numReport+"");
						stmt.executeUpdate("UPDATE request SET currentStatus='Active' WHERE id="+numReport+"");
						stmt.executeUpdate("UPDATE requeststages SET currentStage='waitingEvaluationTime' WHERE id="+numReport+"");
						stmt.executeUpdate("UPDATE requeststages SET currentStatus='Active' WHERE id="+numReport+"");			
					}
					toSend.add(numReport);
					toSend.add(rs.getString(4));
					toSend.add(rs.getString(8));
				}
				//toSend.add(up);
				dbs=new DBSmessage(MessageTypeS.SupervisorUpdateRequest,toSend);
				return dbs;
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}	
			return null;
		}
		public DBSmessage updatechangeExecuter() 
		{
			int num=(int) msg.getObjs().get(0);
			String changeexecuter=(String)msg.getObjs().get(1);
			String changeapprieser=(String)msg.getObjs().get(2);
			Statement stmt;
			try 
			{
				stmt = connection.createStatement();
				stmt.executeUpdate("UPDATE requeststages SET itPerformanceLeader='"+changeexecuter+"' WHERE id="+num+"");
				stmt.executeUpdate("UPDATE requeststages SET itAppraiser='"+changeapprieser+"' WHERE id="+num+"");
				flag=1;	
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			return getReport();
		}
		public DBSmessage getListOfIT() 
		{
			Statement stmt;
			DBSmessage dbs;
			updateRequest up = null;
			ArrayList<Object> listOfIT= new ArrayList<Object>();
			try 
			{		
				stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM itemployees WHERE employeePos='IT' OR employeePos='IT-operator'");
				while(rs.next()!=false)
				{
					listOfIT.add(rs.getString(2).toString());
				}
				dbs=new DBSmessage(MessageTypeS.getListOfIT,listOfIT);
				return dbs;
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}	
			return null;
		}
		public DBSmessage getCC() 
		{
			Statement stmt;
			DBSmessage dbs;
			updateRequest up = null;
			ArrayList<Object> listOfIT= new ArrayList<Object>();
			try 
			{		
				stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM itemployees WHERE employeePos='CEO'");
				while(rs.next()!=false)
				{
					listOfIT.add(rs.getString(2).toString());
				}
				stmt = connection.createStatement();
				rs = stmt.executeQuery("SELECT * FROM itemployees WHERE employeePos='CC'");
				while(rs.next()!=false)
				{
					listOfIT.add(rs.getString(2).toString());
				}
				dbs=new DBSmessage(MessageTypeS.getListOfIT,listOfIT);
				return dbs;
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}	
			return null;
		}
		public DBSmessage getTimeReport() 
		{
			int id=(int)list.get(0);
			Statement stmt;
			DBSmessage dbs;
		//	updateRequest up = null;
			ArrayList<Object> toSend= new ArrayList<Object>();
			try 
			{
				stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM requeststages WHERE id='"+id+ "'");
				while(rs.next()!=false)
				{
					toSend.add(rs.getInt(1));
					toSend.add(rs.getInt(10));
					toSend.add(rs.getInt(12));
					toSend.add(rs.getString(3));
				}
			/*	if(rs.next()!=false)
					up=new updateRequest(rs.getInt(1),rs.getString(4), rs.getString(8),rs.getString(9),rs.getInt(10),rs.getInt(11), rs.getInt(12),rs.getInt(13));
				toSend.add(up);*/
				dbs=new DBSmessage(MessageTypeS.SupervisorTimeRequest,toSend);
				return dbs;
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}	
			return null;
		}
		
		public DBSmessage saveApproveEv()
		{
			int id=(int)list.get(0);
			Statement stmt;
			DBSmessage dbs;
			ArrayList<Object> toSend= new ArrayList<Object>();
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			try {
				stmt = connection.createStatement();
				stmt.executeUpdate("UPDATE requeststages SET currentStage='meaningAssessment' WHERE id="+id+"");
				stmt.executeUpdate("UPDATE request SET currentStage='meaningAssessment' WHERE id="+id+"");
				stmt.executeUpdate("UPDATE requesttime SET meaningAssessmentStart='"+formatter.format(date)+"' WHERE id="+id+"");
				toSend.add(1);
				dbs=new DBSmessage(MessageTypeS.SupervisorApproveEvluationTime,toSend);
				return dbs;
			} catch (SQLException e) {}
			
			return null;
		}
		public DBSmessage saveDenyEv() {
			int id=(int)list.get(0);
			Statement stmt;
			DBSmessage dbs;
			ArrayList<Object> toSend= new ArrayList<Object>();
			try {
				stmt = connection.createStatement();
				stmt.executeUpdate("UPDATE requeststages SET currentStage='waitingEvaluationTime' WHERE id="+id+"");
				stmt.executeUpdate("UPDATE request SET currentStage='waitingEvaluationTime' WHERE id="+id+"");
				toSend.add(1);
				dbs=new DBSmessage(MessageTypeS.SupervisorDenyEvluationTime,toSend);
				return dbs;
			} catch (SQLException e) {}
			return null;
		}
}
