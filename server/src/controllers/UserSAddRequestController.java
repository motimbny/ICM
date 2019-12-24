package controllers;

import java.sql.Connection;
import java.util.ArrayList;

import Enums.StageName;
import entity.DBmessage;
import entity.Request;

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
				             java.time.LocalDate.now().toString(), 0);
	}
   public boolean submitRequest()
   {
	return false; 
   }
}
