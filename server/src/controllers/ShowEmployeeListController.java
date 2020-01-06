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
	private DBmessage db;
	private ArrayList<Object> toSend;
	public ShowEmployeeListController(DBmessage db,Connection connection)
	{
		this.db=db;
		this.connection=connection;
		toSend= new ArrayList<Object>();
	}
	public DBSmessage showEmployee()
	{
		
		Statement stmt;
		DBSmessage dbs;
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
				System.out.println(toSend.get(0));
				return dbs;
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public DBSmessage SwitceEmployee()
	{
		Statement stmt;
		String nameIT=(String) db.getObjs().get(0);
		String pos=(String) db.getObjs().get(1);	
		try {
			stmt = connection.createStatement();
			stmt.executeUpdate("UPDATE user SET position='"+pos+"' WHERE userName='"+nameIT+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		toSend.add(1);
		System.out.println(toSend.size());
		return showEmployee();
	}
	
}
