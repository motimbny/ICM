package entity;

import java.io.Serializable;

import Enums.StageName;
import boundries.MainToRun;
import boundries.mainServer;

@SuppressWarnings("serial")
public class Request implements Serializable {
	private String infoSystem;
	private String currentStatus; //status= in process/ suspended/ finished
	private StageName currentStage;  //stage= execution/testing etc... 
	private Stage[] reqStages;
	private String wantedChange;
	private String reasonForRequest;
	private String desExtSit;
	private String comments;
	private int addDocuments;   // 1-if added, 0 if not
	private String userSubFullName;
	private String userSubposition;
	private String userSubemail;
	private String reqDate;
	private int id;
	public  Request(String infoSystem,String currentStatus,StageName currentStage,String wantedChange,String desExtSit,
    		String reasonForRequest,String comments,String userSubFullName,String userSubposition,
    		String reqDate,int addDocuments)
	{
		this.reqStages= new Stage[5];
    	this.infoSystem=infoSystem;
    	this.currentStatus=currentStatus;
    	this.currentStage=currentStage;
    	this.wantedChange=wantedChange;
    	this.desExtSit=desExtSit;
    	this.reasonForRequest=reasonForRequest;
    	this.comments=comments;
    	this.userSubFullName=userSubFullName;
    	this.userSubposition=userSubposition;
    	this.userSubemail=userSubFullName+"@braude.ac.il";
    	this.reqDate=reqDate;
    	this.addDocuments=addDocuments;
    	this.id=mainServer.NUM_OF_REQUEST;
    	mainServer.NUM_OF_REQUEST++;;
	}
	public String getInfoSystem() {
		return infoSystem;
	}

	public void setInfoSystem(String infoSystem) {
		this.infoSystem = infoSystem;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getWantedChange() {
		return wantedChange;
	}

	public void setWantedChange(String wantedChange) {
		this.wantedChange = wantedChange;
	}

	public String getReasonForRequest() {
		return reasonForRequest;
	}

	public void setReasonForRequest(String reasonForRequest) {
		this.reasonForRequest = reasonForRequest;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getAddDocuments() {
		return addDocuments;
	}

	public void setAddDocuments(int addDocuments) {
		this.addDocuments = addDocuments;
	}

	public String getUserSubFullName() {
		return userSubFullName;
	}

	public void setUserSubFullName(String userSubFullName) {
		this.userSubFullName = userSubFullName;
	}

	public String getUserSubposition() {
		return userSubposition;
	}

	public void setUserSubposition(String userSubposition) {
		this.userSubposition = userSubposition;
	}

	public String getUserSubemail() {
		return userSubemail;
	}

	public void setUserSubemail(String userSubemail) {
		this.userSubemail = userSubemail;
	}

	public String getReqDate() {
		return reqDate;
	}

	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public StageName getCurrentStage() {
		return currentStage;
	}

	public void setCurrentStage(StageName currentStage) {
		this.currentStage = currentStage;
	}

	public Stage[] getReqStages() 
	{
		return reqStages;
	}

	public void setReqStages(Stage[] reqStages) 
	{
		this.reqStages = reqStages;
	}
	public String getDesExtSit() {
		return desExtSit;
	}
	public void setDesExtSit(String desExtSit) {
		this.desExtSit = desExtSit;
	}

}
