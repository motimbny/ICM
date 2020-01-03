package entity;

public class Evluationreport 
{
	private int requestID;
	private String Location;
	private int timeEstimated;
	private String descriptionOfChangeRequired;
	private String resultOfChange;
	private String constraintsAndRisks;
	
	public Evluationreport(int requestID, String Location, int timeEstimated, String descriptionOfChangeRequired, String resultOfChange, String constraintsAndRisks)
	{
		this.requestID=requestID;
		this.Location=Location;
		this.timeEstimated=timeEstimated;
		this.descriptionOfChangeRequired=descriptionOfChangeRequired;
		this.resultOfChange=resultOfChange;
		this.constraintsAndRisks=constraintsAndRisks;
	}
	
	public int getRequestID() {
		return requestID;
	}
	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public int getTimeEstimated() {
		return timeEstimated;
	}
	public void setTimeEstimated(int timeEstimated) {
		this.timeEstimated = timeEstimated;
	}
	public String getDescriptionOfChangeRequired() {
		return descriptionOfChangeRequired;
	}
	public void setDescriptionOfChangeRequired(String descriptionOfChangeRequired) {
		this.descriptionOfChangeRequired = descriptionOfChangeRequired;
	}
	public String getResultOfChange() {
		return resultOfChange;
	}
	public void setResultOfChange(String resultOfChange) {
		this.resultOfChange = resultOfChange;
	}
	public String getConstraintsAndRisks() {
		return constraintsAndRisks;
	}
	public void setConstraintsAndRisks(String constraintsAndRisks) {
		this.constraintsAndRisks = constraintsAndRisks;
	}


}
