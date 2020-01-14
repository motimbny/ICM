package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.print.attribute.HashAttributeSet;

import Enums.MessageTypeS;
import Enums.StageName;
import entity.DBSmessage;
import entity.DBmessage;
import entity.Request;
import entity.RequestUser;
import entity.recentreport;
import entity.requestSuper;
import entity.updateRequest;

/**
 * The Class ITManagerReportsSController.
 */
public class ITManagerReportsSController {

	/** The user. */
	private String user;

	/** The req id. */
	private int reqId;

	/** The connection. */
	private Connection connection;

	/** The msg. */
	private DBmessage msg;

	/**
	 * Instantiates a new IT manager reports S controller.
	 *
	 * @param msg        the msg
	 * @param connection the connection
	 */
	public ITManagerReportsSController(DBmessage msg, Connection connection) {
		this.connection = connection;
		this.msg = msg;
	}

	/**
	 * Make active su clo.
	 *
	 * @return the object
	 */
	public Object makeActiveSuClo() {
		Statement stmt;
		DBSmessage dbs;
		ArrayList<Object> toSendA = new ArrayList<Object>();
		ArrayList<Object> arry = msg.getObjs();
		String start = (String) arry.get(0);
		String end = (String) arry.get(1);
		ArrayList<Object> fail = new ArrayList<Object>();
		ArrayList<Object> suc = new ArrayList<Object>();
		ArrayList<Object> susp = new ArrayList<Object>();
		for (int i = 0; i < 12; i++) {
			fail.add(0);
			suc.add(0);
			susp.add(0);
		}

		int numofFailor = 0, numOfSec = 0, numOfSus = 0, AllReq = 0;

		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM failurreport WHERE date BETWEEN '" + start + "' AND '" + end + "'");
			while (rs.next() != false) {

				String[] dateParts = rs.getString(3).split("-");
				int month = Integer.parseInt(dateParts[1]);

				int sum = (int) fail.get(month);
				sum++;
				fail.set(month, sum);
			}

			rs = stmt.executeQuery("SELECT * FROM request WHERE reqDatel BETWEEN '" + start + "' AND '" + end + "'");

			while (rs.next() != false) {

				String[] dateParts = rs.getString(13).split("-");
				int month = Integer.parseInt(dateParts[1]);

				int sum = (int) suc.get(month);
				sum++;
				suc.set(month, sum);
			}

			rs = stmt.executeQuery("SELECT * FROM suspendrequest WHERE date BETWEEN '" + start + "' AND '" + end + "'");

			while (rs.next() != false) {
				String[] dateParts = rs.getString(3).split("-");
				int month = Integer.parseInt(dateParts[1]);

				int sum = (int) susp.get(month);
				sum++;
				susp.set(month, sum);
			}

			rs = stmt.executeQuery("SELECT * FROM requesttime WHERE meaningAssessmentStart AND closingEND BETWEEN '"
					+ start + "' AND '" + end + "'");
			while (rs.next() != false) {
				AllReq += getDateDiff(rs.getDate(2), rs.getDate(10), TimeUnit.DAYS);

			}
			toSendA.add(fail);
			toSendA.add(suc);
			toSendA.add(susp);
			toSendA.add(AllReq);
			dbs = new DBSmessage(MessageTypeS.makeActiveSuClo, toSendA);
			return dbs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Gets the date diff.
	 *
	 * @param date1    the date 1
	 * @param date2    the date 2
	 * @param timeUnit the time unit
	 * @return the date diff
	 */
	public int getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
		long diffInMillies = date2.getTime() - date1.getTime();
		return (int) timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}

	/**
	 * Make performenct.
	 *
	 * @return the object
	 */
	public Object makePerformenct() {

		Statement stmt;
		DBSmessage dbs;
		ArrayList<Object> toSendA = new ArrayList<Object>();
		ArrayList<Object> arry = msg.getObjs();
		String start = (String) arry.get(0);
		String end = (String) arry.get(1);
		int[] real = new int[4];
		int[] dev = new int[4];
		ArrayList<Object> deviation = new ArrayList<Object>();
		Map<Integer, Object> mapDev = new HashMap<Integer, Object>();
		Map<Integer, Object> mapDev2 = new HashMap<Integer, Object>();

		int sum = 0;

		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT * FROM extensionrequest WHERE date BETWEEN '" + start + "' AND '" + end + "'");
			while (rs.next() != false) {
				sum += rs.getInt(5);
			}
			toSendA.add(sum);

			rs = stmt.executeQuery(
					"SELECT * FROM requesttime WHERE meaningAssessmentStart BETWEEN '" + start + "' AND '" + end + "'");
			while (rs.next() != false) {

				Date date1 = null;
				Date date2 = null;
				for (int j = 2, i = 0; i < 4; i++, j = j + 2) {
					date1 = rs.getDate(j);
					date2 = rs.getDate(j + 1);
					System.out.println(date1);
					System.out.println(date2);
					long diff = date2.getTime() - date1.getTime();
					dev[i] = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
				}
				mapDev.put(rs.getInt(1), dev);

			}
			rs = stmt.executeQuery("SELECT * FROM request WHERE reqDatel BETWEEN '" + start + "' AND '" + end + "'");
			while (rs.next() != false) {

				mapDev2.put(rs.getInt(1), null);

			}
			rs = stmt.executeQuery("SELECT * FROM requeststages");
			while (rs.next() != false) {
				if (mapDev2.containsKey(rs.getInt(1)))
				{
					int[] temp=(int[]) mapDev.get(rs.getInt(1));
					temp[0]-=rs.getInt(10);
					temp[1]-=rs.getInt(11);
					temp[2]-=rs.getInt(12);
					temp[3]-=rs.getInt(13);
					mapDev.put(rs.getInt(1), temp);

				}

			}

			
			toSendA.add(mapDev);
			dbs = new DBSmessage(MessageTypeS.makePerformenct, toSendA);
			return dbs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Make delays.
	 *
	 * @return the object
	 */
	public Object makeDelays() {
		System.out.println("here");

		Statement stmt;
		DBSmessage dbs;
		ArrayList<Object> toSendA = new ArrayList<Object>();
		for (int i = 0; i < 24; i++)
			toSendA.add(0);

		ArrayList<Object> arry = msg.getObjs();
		String start = (String) arry.get(0);
		String end = (String) arry.get(1);

		try {
			System.out.println(start);
			System.out.println(end);
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT * FROM exceptionrequest WHERE date BETWEEN '" + start + "' AND '" + end + "'");
			while (rs.next() != false) {
				String[] dateParts = rs.getString(4).split("-");
				int month = Integer.parseInt(dateParts[1]);
				int days = (int) toSendA.get(month) + rs.getInt(3);
				toSendA.set(month, days);
				int sum = (int) toSendA.get(month + 12);
				sum++;
				toSendA.set(month + 12, sum);

			}
			dbs = new DBSmessage(MessageTypeS.makeDelays, toSendA);
			return dbs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}
	public Object viewrecentreport()
	{
		Statement stmt;
		DBSmessage dbs;
		ArrayList<Object> toSend= new ArrayList<Object>();
		try 
		{
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM reports");
				while(rs.next()!=false)
				{
					recentreport toAdd=new recentreport(rs.getString(1), rs.getString(2), rs.getString(3));
					System.out.println(rs.getString(1));
					System.out.println(rs.getString(2));
					System.out.println(rs.getString(3));
					toSend.add(toAdd);
				}
				
				dbs=new DBSmessage(MessageTypeS.viewrecentreport,toSend);
				return dbs;
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

}
