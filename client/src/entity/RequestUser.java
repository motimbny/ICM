package entity;

import java.io.Serializable;

import Enums.StageName;

/**
 * RequestUser class contain the fields of user request .
 *
 * @author SHIRA
 */
public class RequestUser implements Serializable  {

	/** The id. */
	private int id;
	
	/** The current status. */
	private String currentStatus; //status= in process/ suspended/ finished
	
	/** The current stage. */
	private StageName currentStage;  //stage= execution/testing etc... 
	
	/** The time left. */
	private int timeLeft;
	
	/**
	 * Instantiates a new request user.
	 *
	 * @param id the id
	 * @param currentStatus the current status
	 * @param currentStage the current stage
	 * @param timeLeft the time left
	 */
	public RequestUser(int id,String currentStatus,StageName currentStage, int timeLeft) 
	{
		this.id=id;
		this.currentStatus=currentStatus;
		this.currentStage=currentStage;
		this.timeLeft=timeLeft;
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
	 * Gets the time left.
	 *
	 * @return the time left
	 */
	public int getTimeLeft() {
		return timeLeft;
	}

	/**
	 * Sets the time left.
	 *
	 * @param timeLeft the new time left
	 */
	public void setTimeLeft(int timeLeft) {
		this.timeLeft = timeLeft;
	}
	

	
	
}
