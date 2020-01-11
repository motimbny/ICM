package entity;

import java.io.Serializable;

import Enums.StageName;
/**
 * RequestUser class contain the fields of user request 
 * @author SHIRA
 *
 */
public class RequestUser implements Serializable  {

	private int id;
	private String currentStatus; //status= in process/ suspended/ finished
	private StageName currentStage;  //stage= execution/testing etc... 
	private int timeLeft;
	
	public RequestUser(int id,String currentStatus,StageName currentStage, int timeLeft) 
	{
		this.id=id;
		this.currentStatus=currentStatus;
		this.currentStage=currentStage;
		this.timeLeft=timeLeft;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public StageName getCurrentStage() {
		return currentStage;
	}

	public void setCurrentStage(StageName currentStage) {
		this.currentStage = currentStage;
	}

	public int getTimeLeft() {
		return timeLeft;
	}

	public void setTimeLeft(int timeLeft) {
		this.timeLeft = timeLeft;
	}
	

	
	
}
