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
		manager.makePerformenct();
		
		Statement stmt;
		try {
			stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM reports where stage='"+type+"' and start='"
					+ start + "' and end='" + end + "'");
			if(rs.next())
			{
				int num=rs.getInt(1);
				System.out.println(num);
				assertEquals(1,num );
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void withoutinsert()
	{
		start="2020-01-02";
		end="2020-01-12";
		type="Performence";

		
		Statement stmt;
		try {
			stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM reports where stage='"+type+"' and start='"
					+ start + "' and end='" + end + "'");
			if(rs.next())
			assertEquals(0, rs.getInt(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void addActiveCheckperformence()
	{
		start="2020-01-09";
		end="2020-01-12";
		type="Activity";

		
		Statement stmt;
		try {
			stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM reports where stage='Performence' and start='"
					+ start + "' and end='" + end + "'");
			if(rs.next())
			assertEquals(0, rs.getInt(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void addTwoSameReq()
	{
		start="2020-01-04";
		end="2020-01-12";
		type="Activity";
		DBmessage msg=new DBmessage(null, null);
		ArrayList<Object> send=new ArrayList<Object>();
		send.add(start);
		send.add(end);
		msg.setObjs(send);
		manager=new ITManagerReportsSController(msg, con);
		manager.makeActiveSuClo();
		msg.getObjs().clear();
		start="2020-01-04";
		end="2020-01-12";
		type="Activity";
		send.add(start);
		send.add(end);
		msg.setObjs(send);
		manager=new ITManagerReportsSController(msg, con);
		manager.makeActiveSuClo();
		
		
		Statement stmt;
		try {
			stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM reports where stage='"+type+"' and start='"
					+ start + "' and end='" + end + "'");
			if(rs.next())
			assertEquals(1, rs.getInt(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
