package controllers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Enums.MessageTypeS;
import Enums.StageName;
import entity.DBSmessage;
import entity.DBmessage;
import entity.Request;
import entity.ServerFile;

/**
 * The Class ITManagerRequestDetailsSController.
 */
public class ITManagerRequestDetailsSController {

	/** The req id. */
	private int reqId;

	/** The connection. */
	private Connection connection;

	/**
	 * Instantiates a new IT manager request details S controller.
	 *
	 * @param msg        the msg
	 * @param connection the connection
	 */
	public ITManagerRequestDetailsSController(DBmessage msg, Connection connection) {
		ArrayList<Object> arry = msg.getObjs();
		this.reqId = (int) arry.get(0);
		this.connection = connection;
	}

	/**
	 * This method return the request details
	 *
	 * @return the DB smessage
	 */
	public DBSmessage showRequestDetails() {

		Statement stmt;
		DBSmessage dbs;
		ArrayList<Object> toSendA = new ArrayList<Object>();
		Request tosend;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT id, userSubFullName, infoSystem, currentStatus,currentStage, desExtSit, wantedChange,addDocuments FROM request WHERE id='"
							+ reqId + "'");
			while (rs.next() != false) {
				StageName name = null;
				switch (rs.getString(5)) {
				case "supervisorApprovel":
					name = StageName.supervisorApprovel;
					break;
				case "waitingEvaluationTime":
					name = StageName.waitingEvaluationTime;
					break;
				case "waitingSupervisorApproveEvaluationTime":
					name = StageName.waitingSupervisorApproveEvaluationTime;
					break;
				case "meaningAssessment":
					name = StageName.meaningAssessment;
					break;
				case "waitingExecutionTime":
					name = StageName.waitingExecutionTime;
					break;
				case "waitingSupervisorApproveExecutionTime":
					name = StageName.waitingSupervisorApproveExecutionTime;
					break;
				case "examinationAndDecision":
					name = StageName.examinationAndDecision;
					break;
				case "execution":
					name = StageName.execution;
					break;
				case "testing":
					name = StageName.testing;
					break;
				case "closing":
					name = StageName.closing;
					break;
				case "Closed":
					name = StageName.Closed;
					break;
				}
				tosend = new Request(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), name,
						rs.getString(6), rs.getString(7), rs.getInt(8));
				toSendA.add(tosend);
			}
			dbs = new DBSmessage(MessageTypeS.showRequestDetailsITManager, toSendA);
			return dbs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This method get the attachment of the request
	 * 
	 * @return dbsm
	 */
	public DBSmessage getAttachRequest() {
		ArrayList<Object> toSend = new ArrayList<Object>();
		DBSmessage dbsm = null;
		ServerFile fileOfUser;
		String LocalfilePath = "C:\\serverfile/";
		LocalfilePath = LocalfilePath + "" + reqId + "" + FindUserName();
		fileOfUser = new ServerFile(reqId + "" + FindUserName());
		try {
			File newFile = new File(LocalfilePath);
			byte[] mybytearray = new byte[(int) newFile.length()];
			FileInputStream fis = new FileInputStream(newFile);
			BufferedInputStream bis = new BufferedInputStream(fis);
			fileOfUser.initArray(mybytearray.length);
			fileOfUser.setSize(mybytearray.length);
			bis.read(fileOfUser.getMybytearray(), 0, mybytearray.length);
			toSend.add(fileOfUser);
			dbsm = new DBSmessage(MessageTypeS.superviserAttachFileM, toSend);
			return dbsm;
		} catch (Exception e) {
			System.out.println("Error send File to Server");
		}
		return dbsm;
	}

	/**
	 * This method find the user name in data base
	 * 
	 * @return name
	 */
	public String FindUserName() {
		Statement stmt;
		String num = null;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT userSubFullName FROM request WHERE id=" + reqId + "");
			while (rs.next() != false) {
				num = rs.getString(1);
			}
			rs.close();
		} catch (SQLException e) {
		}
		return num;
	}

}
