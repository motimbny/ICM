package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import Enums.StageName;
import entity.ConnectToDB;

/**
 * The Class CheckTimeOfStages. This class handle the times for each stage in
 * the process, from the data base.
 */
public class CheckTimeOfStages {

	/** The connection. */
	private Connection connection;

	/**
	 * Instantiates a new check time of stages.
	 *
	 * @param connection the connection
	 */
	public CheckTimeOfStages(Connection connection) {
		this.connection = connection;
	}

	/**
	 * This method check the parameters and send to client.
	 */
	public void checkAndSend() {
		Statement stmt;
		Statement stmt1;
		int id;
		String currentstatus, currentstage, timeStage = "", startTime = "", ithandler = "";
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT SELECT id, currentStatus, currentStage FROM request");
			{
				while (rs.next() != false) {
					id = rs.getInt(1);
					currentstatus = rs.getString(2);
					currentstage = rs.getString(3);
					if (!currentstatus.equals("Suspend")) {
						String name = "";
						stmt1 = connection.createStatement();
						switch (currentstage) {
						case "meaningAssessment":
							name = "meaningAssessment";
							timeStage = "timeEvaluation";
							startTime = "meaningAssessmentStart";
							ithandler = "itAppraiser";
							break;
						case "examinationAndDecision":
							name = "examinationAndDecision";
							timeStage = "timeExaminationDecision";
							startTime = "examinationAndDecisionStart";
							ithandler = "itCCC1";
							break;
						case "execution":
							name = "execution";
							timeStage = "timePerform";
							startTime = "executiondStart";
							ithandler = "itPerformanceLeader";
							break;
						case "testing":
							name = "testing";
							timeStage = "timeTest";
							startTime = "testingStart";
							ithandler = "itTester";
							break;
						default:
							break;
						}
						if (!name.equals("")) {
							int x = 0, timeleft;
							String nameOFithandler = "";
							Date today = new Date();
							DateFormat d = new SimpleDateFormat("yyyy-MM-dd");
							Date start = null;
							stmt1 = connection.createStatement();
							ResultSet daters = stmt1.executeQuery("SELECT " + timeStage + ", " + ithandler
									+ " FROM requeststages WHERE id=" + id + "");
							while (daters.next() != false) {
								x = daters.getInt(1);
								nameOFithandler = daters.getString(2);
							}
							ResultSet startrs = stmt1
									.executeQuery("SELECT " + startTime + " FROM requesttime WHERE id=" + id + "");
							while (startrs.next() != false) {
								start = startrs.getDate(1);
							}
							float diffrence = (today.getDate() - start.getDate());
							timeleft = (int) (x - diffrence);
							if (timeleft == 1) {
								SendMail send = new SendMail(nameOFithandler, 2, id, name);
								send.sendEMail();
							}
						}
					}
				}
			}
		} catch (SQLException e) {
		}
	}

	/**
	 * The main method. This method connect to the server DB and update the time.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		ConnectToDB ctdb = new ConnectToDB("Aa123456", "root", "project");
		Connection connection = ctdb.Connect();
		;
		Statement stmt;
		Statement stmt1;
		int id;
		String currentstatus, currentstage, timeStage = "", startTime = "", ithandler = "";
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, currentStatus, currentStage FROM request");
			{
				while (rs.next() != false) {
					id = rs.getInt(1);
					currentstatus = rs.getString(2);
					currentstage = rs.getString(3);
					if (!currentstatus.equals("Suspend")) {
						String name = "";
						stmt1 = connection.createStatement();
						switch (currentstage) {
						case "meaningAssessment":
							name = "meaningAssessment";
							timeStage = "timeEvaluation";
							startTime = "meaningAssessmentStart";
							ithandler = "itAppraiser";
							break;
						case "examinationAndDecision":
							name = "examinationAndDecision";
							timeStage = "timeExaminationDecision";
							startTime = "examinationAndDecisionStart";
							ithandler = "itCCC1";
							break;
						case "execution":
							name = "execution";
							timeStage = "timePerform";
							startTime = "executiondStart";
							ithandler = "itPerformanceLeader";
							break;
						case "testing":
							name = "testing";
							timeStage = "timeTest";
							startTime = "testingStart";
							ithandler = "itTester";
							break;
						default:
							break;
						}
						if (!name.equals("")) {
							int x = 0, timeleft;
							String nameOFithandler = "";
							Date today = new Date();
							DateFormat d = new SimpleDateFormat("yyyy-MM-dd");
							Date start = null;
							stmt1 = connection.createStatement();
							ResultSet daters = stmt1.executeQuery("SELECT " + timeStage + ", " + ithandler
									+ " FROM requeststages WHERE id=" + id + "");
							while (daters.next() != false) {
								x = daters.getInt(1);
								nameOFithandler = daters.getString(2);
							}
							ResultSet startrs = stmt1
									.executeQuery("SELECT " + startTime + " FROM requesttime WHERE id=" + id + "");
							while (startrs.next() != false) {
								start = startrs.getDate(1);
							}
							float diffrence = (today.getDate() - start.getDate());
							timeleft = (int) (x - diffrence);
							if (timeleft == 1) {
								SendMail send = new SendMail(nameOFithandler, 2, id, name);
								send.sendEMail();
							}
						}
					}
				}
			}
		} catch (SQLException e) {
		}
	}

}
