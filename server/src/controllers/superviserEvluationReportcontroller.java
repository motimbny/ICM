package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Enums.MessageTypeS;
import entity.DBSmessage;
import entity.DBmessage;
import entity.evluationReport;

public class superviserEvluationReportcontroller
{
    private Connection connection;
    private int numReport;
	public superviserEvluationReportcontroller(DBmessage dbm, Connection connection) 
	{
		this.connection=connection;
		this.numReport=(int) dbm.getObjs().get(0);
	}
	public DBSmessage getReport()
	{
		Statement stmt;
		DBSmessage dbs;
		evluationReport ev = null;
		ArrayList<Object> toSend= new ArrayList<Object>();
		try 
		{
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM evluationreport WHERE id='"+numReport+ "'");
			if(rs.next()!=false)
				ev=new evluationReport(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getInt(5));
			toSend.add(ev);
			dbs=new DBSmessage(MessageTypeS.superviserEvluationReport,toSend);
				return dbs;
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}	
		return null;
	}
}
