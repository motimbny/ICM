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
	private ServerFile sf;
	public UserSAddRequestController(DBmessage dbm,Connection connection)
	{
		int addDoc=0;
		this.connection=connection;
		ArrayList<Object> arry=dbm.getObjs();
		if(arry.size()==8)
		{
		 sf=(ServerFile)arry.get(7);
		System.out.println("add"+sf.getFileName());
		addDoc=1;
		saveFileToServerFolder();
		}
		request=new Request((String)arry.get(0),"pending",StageName.meaningAssessment,(String)arry.get(1), (String)arry.get(2),
				             (String)arry.get(3), (String)arry.get(4), (String)arry.get(5), (String)arry.get(6) ,
				             java.time.LocalDate.now().toString(),addDoc);	
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
