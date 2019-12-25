package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Enums.StageName;
import entity.DBmessage;
import entity.Request;
import entity.User;

public class UserSAddRequestController
{
	private Request request;
	private Connection connection;
	public UserSAddRequestController(DBmessage dbm,Connection connection)
	{
		this.connection=connection;
		ArrayList<Object> arry=dbm.getObjs();
		request=new Request((String)arry.get(0),"pending",StageName.meaningAssessment,(String)arry.get(1),
				             (String)arry.get(3), (String)arry.get(4), (String)arry.get(5), (String)arry.get(6) ,
				             java.time.LocalDate.now().toString(),0);
	}
   public boolean submitRequest()
   {
	   PreparedStatement ps;
 		try 
 		  {
 			ps = connection.prepareStatement("INSERT INTO request VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
 			ps.setInt(1, request.getId());
 			ps.setString(2, request.getInfoSystem());
 			ps.setString(3, request.getCurrentStatus());
 			ps.setString(4, request.getCurrentStage().toString());
 			ps.setString(5, request.getWantedChange());
 			ps.setString(6, request.getReasonForRequest());
 			ps.setString(7, request.getComments());
 			ps.setInt(8, request.getAddDocuments());
 			ps.setString(9, request.getUserSubFullName());
 			ps.setString(10, request.getUserSubposition());
 			ps.setString(11, request.getUserSubemail());
 			ps.setString(12, request.getReqDate());
 			ps.executeUpdate();	
 		  }
 		 catch (SQLException e) 
 		     {
 		    	e.printStackTrace();
 		    	return false;
 		     }
		return true;
   }

}
