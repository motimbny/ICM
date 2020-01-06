package controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.DBSmessage;
import entity.DBmessage;
import entity.updateRequest;

public class ITCCCRequestMoreInfoSController {
	private int idReq;
	private Connection connection;
	
	public ITCCCRequestMoreInfoSController(DBmessage msg,Connection connection)
	{
		ArrayList<Object> arry=msg.getObjs();
		this.idReq=(int)arry.get(0);
		this.connection=connection;
	}

	public Object approveEvaluationReport() {
		int numReport=idReq;
		Statement stmt;
		try 
		{
			stmt = connection.createStatement();
			
			stmt.executeUpdate("UPDATE request SET currentStage='meaningAssessment' WHERE id="+numReport+"");
			stmt.executeUpdate("UPDATE requeststages SET currentStage='meaningAssessment' WHERE id="+numReport+"");

		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}	
		return null;

	}

}
