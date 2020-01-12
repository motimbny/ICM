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
import entity.requestSuper;

public class superviserRequestShowController
{
	private Connection connection;
	private int numRequest;
	private DBmessage msg;
	public superviserRequestShowController(DBmessage msg,Connection connection)
	{
		this.connection=connection;
		this.msg=msg;
	}
	public DBSmessage getRequestToShow()
	{
		Statement stmt;
		DBSmessage dbs;
		ArrayList<Object> toSend= new ArrayList<Object>();
		try 
		{
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id,currentStatus,currentStage FROM request");
				while(rs.next()!=false)
				{
					requestSuper toAdd=new requestSuper(rs.getInt(1), rs.getString(2), rs.getString(3));
					toSend.add(toAdd);
				}
				dbs=new DBSmessage(MessageTypeS.superviserRequestShow,toSend);
				return dbs;
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public DBSmessage getSPRequestToShow()
	{
		int num=(int) msg.getObjs().get(0);
		Statement stmt;
		DBSmessage dbs;
		ArrayList<Object> toSend= new ArrayList<Object>();
		try 
		{
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id,currentStatus,currentStage FROM request WHERE id="+num+"");
				while(rs.next()!=false)
				{
					requestSuper toAdd=new requestSuper(rs.getInt(1), rs.getString(2), rs.getString(3));
					toSend.add(toAdd);
				}
				dbs=new DBSmessage(MessageTypeS.superviserRequestShow,toSend);
				return dbs;
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public DBSmessage updateSuspendRequest() 
	{
		int num=(int) msg.getObjs().get(0);
		System.out.println(num);
		Statement stmt;
		try 
		{
			stmt = connection.createStatement();
			int rs = stmt.executeUpdate("UPDATE request SET currentStatus='Suspend' WHERE id="+num+"");
				if(rs==1)
				{
					System.out.println("updateSuspendRequest");
				}
			int ds = stmt.executeUpdate("UPDATE requeststages SET currentStatus='Suspend' WHERE id="+num+"");
				if(rs==1)
				{
					System.out.println("updateSuspendRequeststages");
				}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return getRequestToShow();
	}
	

	
	public DBSmessage updatecloseRequest() 
	{
		int num=(int) msg.getObjs().get(0);
		Statement stmt;
		String name="";
		int x=1;
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try 
		{
			stmt = connection.createStatement();
			int rs = stmt.executeUpdate("UPDATE request SET currentStatus='Closed' WHERE id="+num+"");
			stmt.executeUpdate("UPDATE request SET currentStage='Closed' WHERE id="+num+"");
			stmt.executeUpdate("UPDATE requeststages SET currentStatus='Closed' WHERE id="+num+"");
			stmt.executeUpdate("UPDATE requeststages SET currentStage='Closed' WHERE id="+num+"");
			stmt.executeUpdate("UPDATE requesttime SET closingEND='"+formatter.format(date)+"' WHERE id="+num+"");

		
			ResultSet rss = stmt.executeQuery("SELECT userSubFullName FROM request WHERE id="+num+ "");
			while(rss.next()!=false)
			{
				name=rss.getString(1);
			}
			rss = stmt.executeQuery("SELECT timeTest FROM requeststages WHERE id="+num+ "");
			while(rss.next()!=false)
			{
				x=rss.getInt(1);
				if(x!=0)
					x=1;
			}
			SendMail s=new SendMail(name,x,num);
			s.sendEMail();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return getRequestToShow();
	}
	public DBSmessage MgetRequestToShow() 
	{
		Statement stmt;
		DBSmessage dbs;
		ArrayList<Object> toSend= new ArrayList<Object>();
		try 
		{
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id,currentStatus,currentStage FROM request");
				while(rs.next()!=false)
				{
					requestSuper toAdd=new requestSuper(rs.getInt(1), rs.getString(2), rs.getString(3));
					toSend.add(toAdd);
				}
				dbs=new DBSmessage(MessageTypeS.MangerRequestShow,toSend);
				return dbs;
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public Object MgetSPRequestToShow()
	{
		int num=(int) msg.getObjs().get(0);
		Statement stmt;
		DBSmessage dbs;
		ArrayList<Object> toSend= new ArrayList<Object>();
		try 
		{
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id,currentStatus,currentStage FROM request WHERE id="+num+"");
				while(rs.next()!=false)
				{
					requestSuper toAdd=new requestSuper(rs.getInt(1), rs.getString(2), rs.getString(3));
					toSend.add(toAdd);
				}
				dbs=new DBSmessage(MessageTypeS.SearchReqManager,toSend);
				return dbs;
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public Object updaterenewRequest() 
	{
		int num=(int) msg.getObjs().get(0);
		Statement stmt;
		try 
		{
			stmt = connection.createStatement();
			int rs = stmt.executeUpdate("UPDATE request SET currentStatus='Active' WHERE id="+num+"");
				if(rs==1)
				{
					System.out.println("updateSuspendRequest");
				}
				int ds = stmt.executeUpdate("UPDATE requeststages SET currentStatus='Active' WHERE id="+num+"");
				if(rs==1)
				{
					System.out.println("updateSuspendRequeststages");
				}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return MgetRequestToShow();
	}
}
