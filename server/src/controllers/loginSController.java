package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.ConnectToDB;
import entity.User;

public class loginSController 
{
	private String user;
	private String password;
	private ConnectToDB connection;
	public loginSController(String user,String password,ConnectToDB connection)
	{
		this.user=user;
		this.password=password;
		this.connection=connection;
	}
	public User CheckLogIn()
	{
    	Statement stmt;
    	User toSend;
    	Connection con;
    	con=connection.Connect();
  		try 
  		  {
  			stmt = con.createStatement();
  			ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE userName='"+user+ "'");
  	 		if(rs.next()==false)
  	 		{
  	 			return null;                              //user not in database
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
		return null;
	}

}
