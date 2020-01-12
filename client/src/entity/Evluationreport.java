package entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * The Class Evluationreport.
 */
public class Evluationreport implements Serializable 
{
	
	/** The request ID. */
	private int requestID;
	
	/** The Location. */
	private String Location;
	
	/** The time estimated. */
	private String timeEstimated;
	
	/** The description of change required. */
	private String descriptionOfChangeRequired;
	
	/** The result of change. */
	private String resultOfChange;
	
	/** The constraints and risks. */
	private String constraintsAndRisks;
	
	/**
	 * Instantiates a new evluationreport.
	 *
	 * @param requestID the request ID
	 * @param Location the location
	 * @param timeEstimated the time estimated
	 * @param descriptionOfChangeRequired the description of change required
	 * @param resultOfChange the result of change
	 * @param constraintsAndRisks the constraints and risks
	 */
	public Evluationreport(int requestID, String Location, String timeEstimated, String descriptionOfChangeRequired, String resultOfChange, String constraintsAndRisks)
	{
		this.requestID=requestID;
		this.Location=Location;
		this.timeEstimated=timeEstimated;
		this.descriptionOfChangeRequired=descriptionOfChangeRequired;
		this.resultOfChange=resultOfChange;
		this.constraintsAndRisks=constraintsAndRisks;
	}
	
	/**
	 * Gets the request ID.
	 *
	 * @return the request ID
	 */
	public int getRequestID() {
		return requestID;
	}
	
	/**
	 * Sets the request ID.
	 *
	 * @param requestID the new request ID
	 */
	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}
	
	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public String getLocation() {
		return Location;
	}
	
	/**
	 * Sets the location.
	 *
	 * @param location the new location
	 */
	public void setLocation(String location) {
		Location = location;
	}
	
	/**
	 * Gets the time estimated.
	 *
	 * @return the time estimated
	 */
	public String getTimeEstimated() {
		return timeEstimated;
	}
	
	/**
	 * Sets the time estimated.
	 *
	 * @param timeEstimated the new time estimated
	 */
	public void setTimeEstimated(String timeEstimated) {
		this.timeEstimated = timeEstimated;
	}
	
	/**
	 * Gets the description of change required.
	 *
	 * @return the description of change required
	 */
	public String getDescriptionOfChangeRequired() {
		return descriptionOfChangeRequired;
	}
	
	/**
	 * Sets the description of change required.
	 *
	 * @param descriptionOfChangeRequired the new description of change required
	 */
	public void setDescriptionOfChangeRequired(String descriptionOfChangeRequired) {
		this.descriptionOfChangeRequired = descriptionOfChangeRequired;
	}
	
	/**
	 * Gets the result of change.
	 *
	 * @return the result of change
	 */
	public String getResultOfChange() {
		return resultOfChange;
	}
	
	/**
	 * Sets the result of change.
	 *
	 * @param resultOfChange the new result of change
	 */
	public void setResultOfChange(String resultOfChange) {
		this.resultOfChange = resultOfChange;
	}
	
	/**
	 * Gets the constraints and risks.
	 *
	 * @return the constraints and risks
	 */
	public String getConstraintsAndRisks() {
		return constraintsAndRisks;
	}
	
	/**
	 * Sets the constraints and risks.
	 *
	 * @param constraintsAndRisks the new constraints and risks
	 */
	public void setConstraintsAndRisks(String constraintsAndRisks) {
		this.constraintsAndRisks = constraintsAndRisks;
	}


}
