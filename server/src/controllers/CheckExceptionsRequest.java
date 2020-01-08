package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;

import Enums.MessageTypeS;
import Enums.StageName;
import entity.ConnectToDB;
import entity.DBSmessage;
import entity.ITemployee;
import entity.Request;
import entity.RequestUser;
import entity.User;

public class CheckExceptionsRequest 
{
   private int toCheck; 
   private Request req;
   private Connection connection;
   private User superviser,manager;
   public CheckExceptionsRequest(int toCheck,Connection connection)
   {
	   this.toCheck=toCheck;
	   this.connection=connection;
	   getRequest();
	   findsuperviser();
	   findmanager();
   }
   private void findsuperviser()
	{
		Statement stmt;
		User toAdd=null;
		try 
		{
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE position='superviser'");
				while(rs.next()!=false)
				{
				  superviser=new User(rs.getString(1),Integer.toString(rs.getInt(2)),rs.getString(3));
				}
				rs.close();
		}
		catch(Exception e) {}	
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
   public void getRequest()
   {
		Statement stmt;
		try 
		{
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, userSubFullName, infoSystem, currentStatus,currentStage, desExtSit, wantedChange FROM request WHERE id='"+toCheck+ "'");
				while(rs.next()!=false)
				{
					StageName name=null;
					switch(rs.getString(5))
					{
					case "supervisorApprovel":
						name=StageName.supervisorApprovel;
						break;
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
					req=new Request(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),name,rs.getString(6),rs.getString(7));
				}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}	
   }
   public void checkException() throws SQLException
   {
	   PreparedStatement reqToAdd;
	   String stag="";
	   String topick ="",topick1="",topick2="";
	   Date d1=null;
	   Date d2 = null;
	   int dif=0;
	   Statement stmt;
	   ResultSet rs,ds;
	   switch(req.getCurrentStage())
	   {
	       case meaningAssessment:
	       {
	    	   stag="meaningAssessment";
	    	   topick="meaningAssessmentStart";
	    	   topick2="meaningAssessmentEND";
	    	   topick1="timeEvaluation";
	       }
	       break;
	       case examinationAndDecision:
	       {
	    	   stag="examinationAndDecision";
	    	   topick="examinationAndDecisionStart";
	    	   topick2="examinationAndDecisiondEND"; 
	    	   topick1="timeExaminationDecision";
	       }
	       break;
	       case execution:
	       {
	    	   stag="execution";
	    	   topick="executiondStart";
	    	   topick2="executionEND";
	    	   topick1="timePerform";

	       }
	    	   break;
	       case testing:
	       {
	    	   stag="testing";
	    	   topick="testingStart";
	    	   topick2="testingExepted";
	    	   topick1="timeTest";

	       }
	    	   break;
	       case closing: 
	       {
	    	   stag="closing";
	    	   topick="";
	    	   topick2="closingEND";
	       }
	    	   break;
	       default:
		break;	   
	   }
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT "+topick+","+topick2+" FROM requesttime WHERE id="+toCheck+"");
			while(rs.next()!=false)
			{
				d1=rs.getDate(1);
				d2=rs.getDate(2);
			}
			if(topick1.equals(""))
			{
				ds = stmt.executeQuery("SELECT "+topick1+" FROM requeststages WHERE id="+toCheck+"");
				while(ds.next()!=false)
				{
					dif=rs.getInt(1);
				}
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		if(!topick1.equals(""))
		{
		int diffrence=(d2.getDate()-d1.getDate())-dif;
		if(diffrence>0)
			{
			    diffrence-=dif;
				reqToAdd = connection.prepareStatement("INSERT INTO exceptionrequest VALUES(?,?,?)");
				reqToAdd.setInt(1, req.getId());
				reqToAdd.setString(2,stag);
				reqToAdd.setInt(3,diffrence);
				reqToAdd.executeUpdate();	
				reqToAdd.close();
			}
		}
		sendMessageToManager(stag);
		sendMessageToSuperviser(stag);
     }
   public void sendMessageToManager(String stag) throws SQLException
   {
	   PreparedStatement mToAdd;
	   mToAdd = connection.prepareStatement("INSERT INTO messages VALUES(?,?,?,?,?,?)");
	   mToAdd.setString(1,manager.getName());
	   mToAdd.setString(2,"System ICM");
	   mToAdd.setString(3,stag);
	   mToAdd.setString(4,stag+"was late");
	   mToAdd.setString(5,java.time.LocalDate.now().toString());
	   mToAdd.setInt(6,0);
	   mToAdd.executeUpdate();	
	   mToAdd.close();
	}
   public void sendMessageToSuperviser(String stag) throws SQLException
   {
	   PreparedStatement sToAdd;
	   sToAdd = connection.prepareStatement("INSERT INTO messages VALUES(?,?,?,?,?,?)");
	   sToAdd.setString(1,superviser.getName());
	   sToAdd.setString(2,"System ICM");
	   sToAdd.setString(3,stag);
	   sToAdd.setString(4,stag+"was late");
	   sToAdd.setString(5,java.time.LocalDate.now().toString());
	   sToAdd.setInt(6,0);
	   sToAdd.executeUpdate();	
	   sToAdd.close();
   }
}
