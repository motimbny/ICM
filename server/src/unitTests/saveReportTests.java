package unitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import controllers.ITManagerReportsSController;
import entity.ConnectToDB;
import entity.DBmessage;

class saveReportTests {
 
	public ITManagerReportsSController manager;
	public String start,end,type;
	public Connection con;
	public  saveReportTests() {
		
		ConnectToDB coctoDB=new ConnectToDB("Aa123456", "root", "project");
		 con=coctoDB.Connect();
	}
	@Test
	public void regularinsert()
	{
		start="2020-01-01";
		end="2020-01-12";
		type="Performence";
		DBmessage msg=new DBmessage(null, null);
		ArrayList<Object> send=new ArrayList<Object>();
		send.add(start);
		send.add(end);
		msg.setObjs(send);
		manager=new ITManagerReportsSController(msg, con);
		
		Statement stmt;
		try {
			stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT count(*) FROM reports where stage='"+type+"' and start='"
					+ start + "' and end='" + end + "'");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
		
	}


}
