package entity;

import java.io.Serializable;

import Enums.StageName;

public class RequestUser implements Serializable  {

	private int id;
	private String currentStatus; //status= in process/ suspended/ finished
	private StageName currentStage;  //stage= execution/testing etc... 
	private int timeLeft;
	
	public RequestUser(int id,String currentStatus,StageName currentStage) 
	{
		this.id=id;
		this.currentStatus=currentStatus;
		this.currentStage=currentStage;
		this.timeLeft=0;
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
