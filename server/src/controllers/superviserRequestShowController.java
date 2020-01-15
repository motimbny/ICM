package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

/**
 * The Class superviserRequestShowController.
 */
public class superviserRequestShowController
{
	
	/** The connection. */
	private Connection connection;
	
	/** The num request. */
	private int numRequest;
	
	/** The msg. */
	private DBmessage msg;
	
	/**
	 * Instantiates a new superviser request show controller.
	 *
	 * @param msg the msg
	 * @param connection the connection
	 */
	public superviserRequestShowController(DBmessage msg,Connection connection)
	{
		this.connection=connection;
		this.msg=msg;
	}
	
	/**
	 * Gets the request to show.
	 *
	 * @return the request to show
	 */
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
	
	/**
	 * Gets the SP request to show.
	 *
	 * @return the SP request to show
	 */
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
	
	/**
	 * Update suspend request.
	 *
	 * @return the DB smessage
	 */
	public DBSmessage updateSuspendRequest() 
	{
		int num=(int) msg.getObjs().get(0);
		Statement stmt;
		PreparedStatement ps;
		String stage;
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
			ps=connection.prepareStatement("INSERT INTO suspendrequest VALUES(?,?,?)");
			ps.setInt(1, num);
			ps.setString(2, this.getStage(num));
			ps.setString(3,  java.time.LocalDate.now().toString());
			ps.executeUpdate();	
 			ps.close();	
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return getRequestToShow();
	}
	
	public String getStage(int id)
	{
		Statement stmt;
		String stage="";
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT currentStage FROM request WHERE id="+id+ "");
			while(rs.next()!=false)
			{
				stage=rs.getString(1);
			}
		} catch (SQLException e) {}
		return stage;
		
	}
	

	
	/**
	 * Updateclose request.
	 *
	 * @return the DB smessage
	 */
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
	
	/**
	 * Mget request to show.
	 *
	 * @return the DB smessage
	 */
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
	
	/**
	 * Mget SP request to show.
	 *
	 * @return the object
	 */
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
	
	/**
	 * Updaterenew request.
	 *
	 * @return the object
	 */
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
