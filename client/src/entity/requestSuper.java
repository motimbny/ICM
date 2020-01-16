package entity;

import java.io.Serializable;

/**
 * The Class requestSuper is for the supervisor.
 */
public class requestSuper implements Serializable
{
	
	/** The id. */
	private int id;
	
	/** The current status. */
	private String currentStatus;
	
	/** The current stage. */
	private String currentStage;
   
   /**
    * Instantiates a new request super.
    *
    * @param id the request id
    * @param currentStatus the current status
    * @param currentStage the current stage
    */
   public requestSuper(int id,String currentStatus,String currentStage)
   {
	   this.setId(id);
	   this.setCurrentStatus(currentStatus);
	   this.setCurrentStage(currentStage);
   }
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() 
	{
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) 
	{
		this.id = id;
	}
	
	/**
	 * Gets the current status.
	 *
	 * @return the current status
	 */
	public String getCurrentStatus() 
	{
		return currentStatus;
	}
	
	/**
	 * Sets the current status.
	 *
	 * @param currentStatus the new current status
	 */
	public void setCurrentStatus(String currentStatus)
	{
		this.currentStatus = currentStatus;
	}
	
	/**
	 * Gets the current stage.
	 *
	 * @return the current stage
	 */
	public String getCurrentStage()
	{
		return currentStage;
	}
	
	/**
	 * Sets the current stage.
	 *
	 * @param currentStage the new current stage
	 */
	public void setCurrentStage(String currentStage) 
	{
		this.currentStage = currentStage;
	}
}
