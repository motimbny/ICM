package boundries;

import entity.ConnectToDB;

public class mainServer
{
   public static void main(String[]args)
   {
	   ConnectToDB connect=new ConnectToDB("Aa123456","root", "project");
	   mainServerABS mainServerABS=new mainServerABS(5555,connect.Connect()); 
	   mainServerABS.startServer();
   }
   
}
