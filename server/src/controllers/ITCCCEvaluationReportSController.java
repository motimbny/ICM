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
			ResultSet rs = stmt.executeQuery("SELECT id, Location, timeEstimated, descriptionOfChangeRequired,"
					+ " resultOfChange, constraintsAndRisks FROM evluationreport WHERE id='"+idReq+ "'");
				while(rs.next()!=false)
				{
					Evluationreport evtoSend=new Evluationreport(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6));
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

	public DBSmessage approveEvaluationReport() {
			
		int numReport=idReq;
		Statement stmt;
		DBSmessage dbs;
		updateRequest up = null;
		ArrayList<Object> toSend= new ArrayList<Object>();
		try 
		{
			stmt = connection.createStatement();
			
			stmt.executeUpdate("UPDATE request SET currentStage='execution' WHERE id="+numReport+"");
			stmt.executeUpdate("UPDATE requeststages SET currentStage='execution' WHERE id="+numReport+"");

		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}	
		return null;
		
		}

	public Object denyEvaluationReport() {
		int numReport=idReq;
		Statement stmt;
		DBSmessage dbs;
		updateRequest up = null;
		ArrayList<Object> toSend= new ArrayList<Object>();
		try 
		{
			stmt = connection.createStatement();	
			stmt.executeUpdate("UPDATE request SET currentStage='closing' WHERE id="+numReport+"");
			stmt.executeUpdate("UPDATE requeststages SET currentStage='closing' WHERE id="+numReport+"");
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}	
		return null;
	}
	
}
