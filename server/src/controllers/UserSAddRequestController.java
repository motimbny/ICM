package controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import Enums.MessageTypeS;
import Enums.StageName;
import entity.DBSmessage;
import entity.DBmessage;
import entity.Request;
import entity.ServerFile;
import entity.User;

public class UserSAddRequestController
{
	private Request request;
	private Connection connection;
	private ServerFile sf;
	private String CEO;
	private String CCC2;
	private String CCC3;
	private ArrayList<Object> listOfIT;
	private ArrayList<Object> arry;
	public UserSAddRequestController(DBmessage dbm,Connection connection)
	{
		int addDoc=0;
		this.connection=connection;
		arry=dbm.getObjs();
		if(arry.size()==8)
		{
		 sf=(ServerFile)arry.get(7);
		System.out.println("add"+sf.getFileName());
		addDoc=1;
		saveFileToServerFolder();
		}
		request=new Request((String)arry.get(0),"pending",StageName.supervisorApprovel,(String)arry.get(1), (String)arry.get(2),
				             (String)arry.get(3), (String)arry.get(4), (String)arry.get(5), (String)arry.get(6) ,
				             java.time.LocalDate.now().toString(),addDoc);	
		SupervisorUpdateRequestSController getListOfIT=new SupervisorUpdateRequestSController(dbm,connection);
		listOfIT=getListOfIT.getListOfIT().getObjs();
		this.CEO=(String)getListOfIT.getCC().getObjs().get(0);
		this.CCC2=(String)getListOfIT.getCC().getObjs().get(1);
		this.CCC3=(String)getListOfIT.getCC().getObjs().get(2);

	}
	private void setCC() 
	{
		Statement stmt;
		DBSmessage dbs;
		   int id=(int)arry.get(0);
		   try {
			   stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM itemployees WHERE employeePos='CEO'");
				if(rs.next()!=false)
				{
					this.CEO=rs.getString(2);
				}
				   stmt = connection.createStatement();
				   rs = stmt.executeQuery("SELECT * FROM itemployees WHERE employeePos='CC'");
				   if(rs.next()!=false)
					   this.CCC2=rs.getString(2);
				   if(rs.next()!=false)
					   this.CCC3=rs.getString(2);
		   }
		   catch (SQLException e) 
		     {
		    	e.printStackTrace();
		    	
		     }	
	}
	public DBSmessage submitRequest()
	{
		int c1,c2,c3;
	   PreparedStatement req;
	   PreparedStatement requeststages;
	   PreparedStatement reqTime;
	   DBSmessage dbs;
	   Random rand = new Random();    
	   boolean flag=false;
   	   ArrayList<Object> arry=new ArrayList<Object>();
 		try 
 		  {
 			req = connection.prepareStatement("INSERT INTO request VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
 			req.setInt(1, request.getId());
 			req.setString(2, request.getInfoSystem());
 			req.setString(3, request.getCurrentStatus());
 			req.setString(4, request.getCurrentStage().toString());
 			req.setString(5, request.getDesExtSit());
 			req.setString(6, request.getWantedChange());
 			req.setString(7, request.getReasonForRequest());
 			req.setString(8, request.getComments());
 			req.setInt(9, request.getAddDocuments());
 			req.setString(10, request.getUserSubFullName());
 			req.setString(11, request.getUserSubposition());                          
 			req.setString(12, request.getUserSubemail());
 			req.setString(13, request.getReqDate());
 			req.executeUpdate();	
 			req.close();
 			requeststages = connection.prepareStatement("INSERT INTO requeststages VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
 			requeststages.setInt(1, request.getId());
 			requeststages.setString(2, request.getCurrentStatus());
 			requeststages.setString(3,request.getCurrentStage().toString());
 			c1=rand.nextInt(listOfIT.size());
 			requeststages.setString(4,listOfIT.get(c1).toString());
 			requeststages.setString(5,this.CEO);
 			requeststages.setString(6, this.CCC2);
 			requeststages.setString(7, this.CCC3);
 			c2=rand.nextInt(listOfIT.size());
 			while(c1==c2)
 	 			c2=rand.nextInt(listOfIT.size());	
 			requeststages.setString(8,listOfIT.get(c2).toString());
 			c3=rand.nextInt(listOfIT.size());
 			while(c1==c3||c2==c3)
 	 			c3=rand.nextInt(listOfIT.size());
 			requeststages.setString(9, listOfIT.get(c3).toString());
 			requeststages.setInt(10, 0);
 			requeststages.setInt(11,0);                          
 			requeststages.setInt(12, 0);
 			requeststages.setInt(13, 0);
 			requeststages.executeUpdate();	
 			requeststages.close();
 			reqTime = connection.prepareStatement("INSERT INTO requesttime VALUES(?,?,?,?,?,?,?,?,?,?,?)");
 			reqTime.setInt(1, request.getId());
 			reqTime.setDate(2, null);
 			reqTime.setDate(3, null);
 			reqTime.setDate(4, null);
 			reqTime.setDate(5, null);
 			reqTime.setDate(6, null);
 			reqTime.setDate(7, null);
 			reqTime.setDate(8, null);
 			reqTime.setDate(9, null);
 			reqTime.setDate(10, null);
 			reqTime.setDate(11, null);
 			reqTime.executeUpdate();	
 			reqTime.close();
 		  }
 		 catch (SQLException e) 
 		     {
 		    	e.printStackTrace();
 		    	flag=false;
 		     }
 		flag=true;
 		arry.add(flag);
    	dbs=new DBSmessage(MessageTypeS.AddRequest, arry);
		return dbs;
   }
   public void saveFileToServerFolder()
   {
	   int fileSize =sf.getSize(); 
		  System.out.println("Message received: " + sf);
		  System.out.println("length "+ fileSize); 
		  String LocalfilePath="serverfile/";	
		  try{
			      File newFile = new File (LocalfilePath+sf.getFileName());     		      
			      byte [] mybytearray  = sf.getMybytearray();		  
			      FileOutputStream fos = new FileOutputStream(newFile);
				  BufferedOutputStream bos = new BufferedOutputStream(fos);
				  bos.write(mybytearray,0,sf.getSize());
			      bos.flush();
			      fos.flush();
			    }
			catch (Exception e) {
				System.out.println("Error send ((Files)msg) to Server");
			}
   }

}
