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
import entity.ITemployee;
import entity.RequestUser;

public class ShowEmployeeListController 
{
	private Connection connection;
	public ShowEmployeeListController(DBmessage msg,Connection connection)
	{
		this.connection=connection;
	}
	public DBSmessage showEmployee()
	{
		Statement stmt;
		DBSmessage dbs;
		ArrayList<Object> toSend= new ArrayList<Object>();
		try 
		{
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM itemployees WHERE employeePos='regular'");
				while(rs.next()!=false)
				{
					ITemployee toAdd=new ITemployee(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4), rs.getInt(5),rs.getString(6));
					toSend.add(toAdd);
				}
				dbs=new DBSmessage(MessageTypeS.ShowEmployeeList,toSend);
				return dbs;
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
}
