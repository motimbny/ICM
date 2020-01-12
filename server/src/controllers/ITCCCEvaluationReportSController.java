package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Enums.MessageTypeS;
import Enums.StageName;
import entity.DBSmessage;
import entity.DBmessage;
import entity.Evluationreport;
import entity.Request;
import entity.updateRequest;

public class ITCCCEvaluationReportSController {
	private int idReq;
	private Connection connection;
	
	public ITCCCEvaluationReportSController(DBmessage msg,Connection connection)
	{
		ArrayList<Object> arry=msg.getObjs();
		this.idReq=(int)arry.get(0);
		this.connection=connection;
	}

	public DBSmessage showEvaluationReport() 
	{
		Statement stmt;
		DBSmessage dbs=null;
		ArrayList<Object> toSend= new ArrayList<Object>();
		try 
		{
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, location, timeEstimated, description,"
					+ " result, constraints FROM evluationreport WHERE id='"+idReq+ "'");
				while(rs.next()!=false)
				{
					Evluationreport evtoSend=new Evluationreport(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
					toSend.add(evtoSend);
				}
				dbs=new DBSmessage(MessageTypeS.ITshowEvaluationReport,toSend);
				return dbs;
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}	
		return dbs;
	}

	public DBSmessage approveEvaluationReport() 
	{
		Statement stmt;
		DBSmessage dbs;
		updateRequest up = null;
		ArrayList<Object> toSend= new ArrayList<Object>();
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try 
		{
			stmt = connection.createStatement();
			stmt.executeUpdate("UPDATE requesttime SET examinationAndDecisiondEND='"+formatter.format(date)+"' WHERE id="+idReq+"");
			CheckExceptionsRequest checkExp = new CheckExceptionsRequest(idReq,connection);
			checkExp.checkException();
			stmt.executeUpdate("UPDATE request SET currentStage='waitingExecutionTime' WHERE id="+idReq+"");
			stmt.executeUpdate("UPDATE requeststages SET currentStage='waitingExecutionTime' WHERE id="+idReq+"");
			
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}	
		return null;
		
		}

	public Object denyEvaluationReport() {
		Statement stmt;
		DBSmessage dbs;
		String name="";
		updateRequest up = null;
		ArrayList<Object> toSend= new ArrayList<Object>();
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try 
		{
			stmt = connection.createStatement();	
			stmt.executeUpdate("UPDATE requesttime SET examinationAndDecisiondEND='"+formatter.format(date)+"' WHERE id="+idReq+"");
			CheckExceptionsRequest checkExp = new CheckExceptionsRequest(idReq,connection);
			checkExp.checkException();
			stmt.executeUpdate("UPDATE request SET currentStage='closing' WHERE id="+idReq+"");
			stmt.executeUpdate("UPDATE requeststages SET currentStage='closing' WHERE id="+idReq+"");
	
			
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}	
		return null;
	}
	
}
