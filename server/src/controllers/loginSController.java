package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.ConnectToDB;
import entity.DBmessage;
import entity.User;

public class loginSController 
{
	private String user;
	private String password;
	private Connection connection;
	public loginSController(DBmessage msg,Connection connection)
	{
		this.user=((String)msg.getObjs().get(0));
		this.password=((String)msg.getObjs().get(1));
		this.connection=connection;
	}
	public User CheckLogIn()
	{
    	Statement stmt;
    	User toSend=null;
  		try 
  		  {
  			stmt = connection.createStatement();
  			ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE userName='"+user+ "'");
  	 		if(rs.next()==false)
  	 		{
  	 			return toSend;                              //user not in database
  	 		}
  	 		else
  	 		{
  	 			if(password.equals(rs.getString(2)))           //change to number of col
  	 			{
  	 				toSend=new User(rs.getString(1),rs.getString(2),rs.getString(3));
  	 				return toSend;
  	 			}
  	 		}
  			 rs.close();
  		  } 
  		    catch (SQLException e) 
  		     {
  		    	e.printStackTrace();
  		     }
		return toSend;
	}

}
