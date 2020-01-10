package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import Enums.MessageTypeS;
import Enums.StageName;
import entity.DBSmessage;
import entity.DBmessage;
import entity.Request;
import entity.RequestUser;
import entity.updateRequest;

public class ITManagerReportsSController {

	private String user;
	private int reqId;
	private Connection connection;
	private DBmessage msg;

	public ITManagerReportsSController(DBmessage msg, Connection connection) {
		this.connection = connection;
		this.msg = msg;
	}

	public Object makeActiveSuClo() {
		Statement stmt;
		DBSmessage dbs;
		ArrayList<Object> toSendA = new ArrayList<Object>();
		ArrayList<Object> arry = msg.getObjs();
		String start = (String) arry.get(0);
		String end = (String) arry.get(1);

		int numofFailor = 0, numOfSec = 0, numOfSus = 0, AllReq = 0;

		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM failurreport WHERE date BETWEEN '" + start + "' AND '" + end + "'");
			while (rs.next() != false) {
				numofFailor++;
			}
			toSendA.add(numofFailor);
			rs = stmt.executeQuery("SELECT * FROM request WHERE reqDatel BETWEEN '" + start + "' AND '" + end + "'");

			while (rs.next() != false) {
				numOfSec++;
			}
			toSendA.add(numOfSec);
			rs = stmt.executeQuery("SELECT * FROM suspendrequest WHERE date BETWEEN '" + start + "' AND '" + end + "'");

			while (rs.next() != false) {
				numOfSus++;
			}
			toSendA.add(numOfSus);
			rs = stmt.executeQuery("SELECT * FROM requesttime WHERE meaningAssessmentStart AND closingEND BETWEEN '"
					+ start + "' AND '" + end + "'");
			while (rs.next() != false) {
				AllReq += getDateDiff(rs.getDate(2), rs.getDate(10), TimeUnit.DAYS);

			}
			toSendA.add(AllReq);
			dbs = new DBSmessage(MessageTypeS.makeActiveSuClo, toSendA);
			return dbs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
		long diffInMillies = date2.getTime() - date1.getTime();
		return (int) timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}

	public Object makePerformenct() {

		Statement stmt;
		DBSmessage dbs;
		ArrayList<Object> toSendA = new ArrayList<Object>();
		ArrayList<Object> arry = msg.getObjs();
		String start = (String) arry.get(0);
		String end = (String) arry.get(1);

		int  sum=0 ;

		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM extensionrequest WHERE date BETWEEN '" + start + "' AND '" + end + "'");
			while (rs.next() != false) 
			{
				sum += rs.getInt(5);
			}
			toSendA.add(sum);
			dbs = new DBSmessage(MessageTypeS.makePerformenct, toSendA);
			return dbs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

}
