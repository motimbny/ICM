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
import entity.Request;
import entity.RequestUser;

public class CheckExceptionsRequest 
{
   private int toCheck; 
   private Request req;
   private Connection connection;
   public CheckExceptionsRequest(int toCheck,Connection connection)
   {
	   this.toCheck=toCheck;
	   this.connection=connection;
	   getRequest();
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
			System.out.println("im here");
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT "+topick+","+topick2+" FROM requesttime WHERE id="+toCheck+"");
			while(rs.next()!=false)
			{
				System.out.println("im here 2");
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
		int diffrence=(d2.getDay()-d1.getDay())-dif;
		if(diffrence>0)
			{
				reqToAdd = connection.prepareStatement("INSERT INTO exceptionrequest VALUES(?,?,?)");
				reqToAdd.setInt(1, req.getId());
				reqToAdd.setString(2,stag);
				reqToAdd.setInt(3,diffrence);
			}
		}
     }
   public static void main(String[]args) throws SQLException
   {
	   ConnectToDB connection=new ConnectToDB("Aa123456","root","project");
	   CheckExceptionsRequest ch=new CheckExceptionsRequest(17, connection.Connect());
	   ch.checkException();
	   
   }
}
