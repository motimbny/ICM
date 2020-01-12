package entity;

import java.io.Serializable;

import Enums.StageName;
import boundries.MainToRun;
import boundries.mainServer;

/**
 * Request entity contain all the fields of the request.
 *
 * @author SHIRA
 */
@SuppressWarnings("serial")
public class Request implements Serializable {
	
	/** The info system. */
	private String infoSystem;
	
	/** The current status. */
	private String currentStatus; // status= in process/ suspended/ finished
	
	/** The current stage. */
	private StageName currentStage; // stage= execution/testing etc...
	
	/** The req stages. */
	private Stage[] reqStages;
	
	/** The wanted change. */
	private String wantedChange;
	
	/** The reason for request. */
	private String reasonForRequest;
	
	/** The des ext sit. */
	private String desExtSit;
	
	/** The comments. */
	private String comments;
	
	/** The add documents. */
	private int addDocuments; // 1-if added, 0 if not
	
	/** The user sub full name. */
	private String userSubFullName;
	
	/** The user subposition. */
	private String userSubposition;
	
	/** The user subemail. */
	private String userSubemail;
	
	/** The req date. */
	private String reqDate;
	
	/** The id. */
	private int id;

	/**
	 * Instantiates a new request.
	 *
	 * @param infoSystem the info system
	 * @param currentStatus the current status
	 * @param currentStage the current stage
	 * @param wantedChange the wanted change
	 * @param desExtSit the des ext sit
	 * @param reasonForRequest the reason for request
	 * @param comments the comments
	 * @param userSubFullName the user sub full name
	 * @param userSubposition the user subposition
	 * @param reqDate the req date
	 * @param addDocuments the add documents
	 */
	public Request(String infoSystem, String currentStatus, StageName currentStage, String wantedChange,
			String desExtSit, String reasonForRequest, String comments, String userSubFullName, String userSubposition,
			String reqDate, int addDocuments) {
		this.reqStages = new Stage[5];
		this.infoSystem = infoSystem;
		this.currentStatus = currentStatus;
		this.currentStage = currentStage;
		this.wantedChange = wantedChange;
		this.desExtSit = desExtSit;
		this.reasonForRequest = reasonForRequest;
		this.comments = comments;
		this.userSubFullName = userSubFullName;
		this.userSubposition = userSubposition;
		this.userSubemail = userSubFullName + "@braude.ac.il";
		this.reqDate = reqDate;
		this.addDocuments = addDocuments;
		this.id = mainServer.NUM_OF_REQUEST;
		mainServer.NUM_OF_REQUEST++;
		;
	}

	/**
	 * Instantiates a new request.
	 *
	 * @param id the id
	 * @param userSubFullName the user sub full name
	 * @param infoSystem the info system
	 * @param currentStatus the current status
	 * @param currentStage the current stage
	 * @param desExtSit the des ext sit
	 * @param wantedChange the wanted change
	 */
	public Request(int id, String userSubFullName, String infoSystem, String currentStatus, StageName currentStage,
			String desExtSit, String wantedChange) {
		this.id = id;
		this.userSubFullName = userSubFullName;
		this.infoSystem = infoSystem;
		this.currentStatus = currentStatus;
		this.currentStage = currentStage;
		this.desExtSit = desExtSit;
		this.wantedChange = wantedChange;
	}

	/**
	 * Gets the info system.
	 *
	 * @return the info system
	 */
	public String getInfoSystem() {
		return infoSystem;
	}

	/**
	 * Sets the info system.
	 *
	 * @param infoSystem the new info system
	 */
	public void setInfoSystem(String infoSystem) {
		this.infoSystem = infoSystem;
	}

	/**
	 * Gets the current status.
	 *
	 * @return the current status
	 */
	public String getCurrentStatus() {
		return currentStatus;
	}

	/**
	 * Sets the current status.
	 *
	 * @param currentStatus the new current status
	 */
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	/**
	 * Gets the wanted change.
	 *
	 * @return the wanted change
	 */
	public String getWantedChange() {
		return wantedChange;
	}

	/**
	 * Sets the wanted change.
	 *
	 * @param wantedChange the new wanted change
	 */
	public void setWantedChange(String wantedChange) {
		this.wantedChange = wantedChange;
	}

	/**
	 * Gets the reason for request.
	 *
	 * @return the reason for request
	 */
	public String getReasonForRequest() {
		return reasonForRequest;
	}

	/**
	 * Sets the reason for request.
	 *
	 * @param reasonForRequest the new reason for request
	 */
	public void setReasonForRequest(String reasonForRequest) {
		this.reasonForRequest = reasonForRequest;
	}

	/**
	 * Gets the comments.
	 *
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * Sets the comments.
	 *
	 * @param comments the new comments
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * Gets the adds the documents.
	 *
	 * @return the adds the documents
	 */
	public int getAddDocuments() {
		return addDocuments;
	}

	/**
	 * Sets the adds the documents.
	 *
	 * @param addDocuments the new adds the documents
	 */
	public void setAddDocuments(int addDocuments) {
		this.addDocuments = addDocuments;
	}

	/**
	 * Gets the user sub full name.
	 *
	 * @return the user sub full name
	 */
	public String getUserSubFullName() {
		return userSubFullName;
	}

	/**
	 * Sets the user sub full name.
	 *
	 * @param userSubFullName the new user sub full name
	 */
	public void setUserSubFullName(String userSubFullName) {
		this.userSubFullName = userSubFullName;
	}

	/**
	 * Gets the user subposition.
	 *
	 * @return the user subposition
	 */
	public String getUserSubposition() {
		return userSubposition;
	}

	/**
	 * Sets the user subposition.
	 *
	 * @param userSubposition the new user subposition
	 */
	public void setUserSubposition(String userSubposition) {
		this.userSubposition = userSubposition;
	}

	/**
	 * Gets the user subemail.
	 *
	 * @return the user subemail
	 */
	public String getUserSubemail() {
		return userSubemail;
	}

	/**
	 * Sets the user subemail.
	 *
	 * @param userSubemail the new user subemail
	 */
	public void setUserSubemail(String userSubemail) {
		this.userSubemail = userSubemail;
	}

	/**
	 * Gets the req date.
	 *
	 * @return the req date
	 */
	public String getReqDate() {
		return reqDate;
	}

	/**
	 * Sets the req date.
	 *
	 * @param reqDate the new req date
	 */
	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the current stage.
	 *
	 * @return the current stage
	 */
	public StageName getCurrentStage() {
		return currentStage;
	}

	/**
	 * Sets the current stage.
	 *
	 * @param currentStage the new current stage
	 */
	public void setCurrentStage(StageName currentStage) {
		this.currentStage = currentStage;
	}

	/**
	 * Gets the req stages.
	 *
	 * @return the req stages
	 */
	public Stage[] getReqStages() {
		return reqStages;
	}

	/**
	 * Sets the req stages.
	 *
	 * @param reqStages the new req stages
	 */
	public void setReqStages(Stage[] reqStages) {
		this.reqStages = reqStages;
	}

	/**
	 * Gets the des ext sit.
	 *
	 * @return the des ext sit
	 */
	public String getDesExtSit() {
		return desExtSit;
	}

	/**
	 * Sets the des ext sit.
	 *
	 * @param desExtSit the new des ext sit
	 */
	public void setDesExtSit(String desExtSit) {
		this.desExtSit = desExtSit;
	}

}
