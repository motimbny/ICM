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
	private ITemployee superviser,ceo,cc,cc2,person;
	public ShowEmployeeListController(DBmessage db,Connection connection)
	{
		this.db=db;
		this.connection=connection;
		toSend= new ArrayList<Object>();
		superviser=findsuperviser();
		ceo=findceo();
		cc=findcc();
		cc2=findcc2();
	}
	
	private ITemployee findcc2() 
	{
		Statement stmt;
		ITemployee toAdd=null;
		try 
		{
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM itemployees WHERE employeePos='CC' AND employeeName != '"+cc.getEmployeeName()+"'");
				while(rs.next()!=false)
				{
				  toAdd=new ITemployee(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4), rs.getInt(5),rs.getString(6));
				}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return toAdd;
	}
	private ITemployee findcc()
	{
		Statement stmt;
		ITemployee toAdd=null;
		try 
		{
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM itemployees WHERE employeePos='CC'");
				while(rs.next()!=false)
				{
				  toAdd=new ITemployee(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4), rs.getInt(5),rs.getString(6));
				}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return toAdd;
	}
	private ITemployee findceo() 
	{
		Statement stmt;
		ITemployee toAdd=null;
		try 
		{
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM itemployees WHERE employeePos='CEO'");
				while(rs.next()!=false)
				{
				  toAdd=new ITemployee(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4), rs.getInt(5),rs.getString(6));
				}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return toAdd;
	}
	private ITemployee findsuperviser()
	{
		Statement stmt;
		ITemployee toAdd=null;
		try 
		{
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM itemployees WHERE employeePos='superviser'");
				while(rs.next()!=false)
				{
				  toAdd=new ITemployee(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4), rs.getInt(5),rs.getString(6));
				}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return toAdd;
	}
	public DBSmessage showEmployee()
	{	
		Statement stmt;
		DBSmessage dbs;
		try 
		{
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM itemployees WHERE employeePos='IT' OR  employeePos='superviser' OR employeePos='CEO' OR employeePos='CC' OR employeePos='IT-operator'");
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
	public DBSmessage SwitceEmployee()
	{
		Statement stmt;
		String nameIT=(String) db.getObjs().get(0);
		String pos=(String) db.getObjs().get(1);
		person=getperson(nameIT);
		String posit="IT";
		try {
			stmt = connection.createStatement();
		     if(nameIT.equals(cc2.getEmployeeName())||nameIT.equals(cc.getEmployeeName())||nameIT.equals(superviser.getEmployeeName())||nameIT.equals(ceo.getEmployeeName()))
		    	 posit=person.getEmployeePos();    
			stmt.executeUpdate("UPDATE user SET position='"+pos+"' WHERE userName='"+nameIT+"'");
			stmt.executeUpdate("UPDATE itemployees SET employeePos='"+pos+"' WHERE employeeName='"+nameIT+"'");
			if(pos.equals("CEO"))
			{
				stmt.executeUpdate("UPDATE user SET position='"+posit+"' WHERE userName='"+ceo.getEmployeeName()+"'");
				stmt.executeUpdate("UPDATE itemployees SET employeePos='"+posit+"' WHERE employeeName='"+ceo.getEmployeeName()+"'");
			}
			if(pos.equals("superviser"))
			{
				stmt.executeUpdate("UPDATE user SET position='"+posit+"'  WHERE userName='"+superviser.getEmployeeName()+"'");
				stmt.executeUpdate("UPDATE itemployees SET employeePos='"+posit+"' WHERE employeeName='"+superviser.getEmployeeName()+"'");
			}
			if(pos.equals("CC"))
			{
				stmt.executeUpdate("UPDATE user SET position='"+posit+"'  WHERE userName='"+cc.getEmployeeName()+"'");
				stmt.executeUpdate("UPDATE itemployees SET employeePos='"+posit+"' WHERE employeeName='"+cc.getEmployeeName()+"'");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		toSend.add(1);
		return showEmployee();
	}
	private ITemployee getperson(String nameIT) 
	{
		Statement stmt;
		ITemployee toAdd=null;
		try 
		{
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM itemployees WHERE employeeName='"+nameIT+"'");
				while(rs.next()!=false)
				{
				  toAdd=new ITemployee(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4), rs.getInt(5),rs.getString(6));
				}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return toAdd;
	}
	
}
