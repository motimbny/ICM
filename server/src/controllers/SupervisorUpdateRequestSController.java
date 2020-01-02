package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Enums.MessageTypeS;
import entity.DBSmessage;
import entity.DBmessage;
import entity.ITemployee;
import entity.evluationReport;
import entity.updateRequest;

public class SupervisorUpdateRequestSController {

	   private Connection connection;
	   // private int numReport;
	   private ArrayList<Object> list;
	    private DBmessage msg;
		public SupervisorUpdateRequestSController(DBmessage dbm, Connection connection) 
		{
			this.list=dbm.getObjs();
			this.msg=dbm;
			this.connection=connection;
			System.out.println("lalala");

			//System.out.println("num:"+numReport);
		}
		public DBSmessage getReport()
		{
			int numReport=(int)list.get(0);
			System.out.println(numReport);
			Statement stmt;
			DBSmessage dbs;
			updateRequest up = null;
			ArrayList<Object> toSend= new ArrayList<Object>();
			try 
			{
				//System.out.println(numReport);
				stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM requeststages WHERE id='"+numReport+ "'");
				if(rs.next()!=false)
					up=new updateRequest(rs.getInt(1),rs.getString(4), rs.getString(8),rs.getString(9),rs.getInt(10),rs.getInt(11), rs.getInt(12),rs.getInt(13));
				toSend.add(up);
				System.out.println(up);
				dbs=new DBSmessage(MessageTypeS.SupervisorUpdateRequest,toSend);
					return dbs;
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}	
			return null;
		}
		public DBSmessage updatechangeExecuter() {
			
			int num=(int) msg.getObjs().get(0);
			String change=(String)msg.getObjs().get(1);
			String position=(String)msg.getObjs().get(2);
			Statement stmt;
			try 
			{
				stmt = connection.createStatement();
				int rs = stmt.executeUpdate("UPDATE requeststages SET "+position+"='"+change+"' WHERE id="+num+"");
				System.out.println(rs);
					if(rs==1)
					{
						System.out.println("updatechangeExecuter");
					}
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			return getReport();
		}
		public DBSmessage getListOfIT() {
			

			Statement stmt;
			DBSmessage dbs;
			updateRequest up = null;
			ArrayList<Object> listOfIT= new ArrayList<Object>();
			try 
			{
				
				stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM itemployees WHERE employeePos='regular'");
				while(rs.next()!=false)
				{
					System.out.println(rs.getString(2));
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
}
