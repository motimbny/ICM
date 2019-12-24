package controllers;

import java.sql.Connection;
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
	   Statement stmt;
 		try 
 		  {
 			stmt = connection.createStatement();
 			int rs = stmt.executeUpdate("INSERT INTO request VALUES(1,"+request.getId()+",2,"+request.getInfoSystem()+
 					",3,"+request.getCurrentStatus()+",4,"+request.getCurrentStatus()+",5,"+request.getWantedChange()
 					+",6,"+request.getReasonForRequest()+",7,"+request.getComments()+",8,"+request.getAddDocuments()
 					+",9,"+request.getUserSubFullName()+",10,"+request.getUserSubposition()+",11,"+request.getUserSubemail()+");");
 	 		if(rs==0)
 	 			return false;
 	 		else
 	 			return true;
 		  }
 		
 		 catch (SQLException e) 
 		     {
 		    	e.printStackTrace();
 		     }
		return false;
   }
}
