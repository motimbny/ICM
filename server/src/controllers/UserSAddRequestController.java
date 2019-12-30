package controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

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
	public UserSAddRequestController(DBmessage dbm,Connection connection)
	{
		this.connection=connection;
		ArrayList<Object> arry=dbm.getObjs();
		//ServerFile sf=(ServerFile)arry.get(8);
		//System.out.println("add"+sf.getFileName());
		//int addDoc;
		request=new Request((String)arry.get(0),"pending",StageName.meaningAssessment,(String)arry.get(1), (String)arry.get(2),
				             (String)arry.get(3), (String)arry.get(4), (String)arry.get(5), (String)arry.get(6) ,
				             java.time.LocalDate.now().toString(),0);	
	}
	public DBSmessage submitRequest()
	{
	   PreparedStatement ps;
	   DBSmessage dbs;
	   boolean flag=false;
   	   ArrayList<Object> arry=new ArrayList<Object>();
 		try 
 		  {
 			ps = connection.prepareStatement("INSERT INTO request VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
 			ps.setInt(1, request.getId());
 			ps.setString(2, request.getInfoSystem());
 			ps.setString(3, request.getCurrentStatus());
 			ps.setString(4, request.getCurrentStage().toString());
 			ps.setString(5, request.getDesExtSit());
 			ps.setString(6, request.getWantedChange());
 			ps.setString(7, request.getReasonForRequest());
 			ps.setString(8, request.getComments());
 			ps.setInt(9, request.getAddDocuments());
 			ps.setString(10, request.getUserSubFullName());
 			ps.setString(11, request.getUserSubposition());                          
 			ps.setString(12, request.getUserSubemail());
 			ps.setString(13, request.getReqDate());
 			ps.executeUpdate();	
 			ps.close();
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
   public void saveFileToServerFolder(Object msg)
   {
	   int fileSize =((ServerFile)msg).getSize(); 
		  ServerFile newMsg= (ServerFile)msg; 	
		  String path="server/";
		  try{
			  File newFile = new File(path+""+newMsg.getFileName());
			  byte [] mybytearray  =newMsg.getMybytearray();
			  FileOutputStream fos = new FileOutputStream(newFile);
			  BufferedOutputStream bos = new BufferedOutputStream(fos);
			  bos.write (mybytearray,0,newMsg.getSize());
			  bos.flush();
			  fos.flush();
			 }
		  
			catch (Exception e)
		     {
				System.out.println("Error send (Files)msg) to Server");
			 }
   }

}
