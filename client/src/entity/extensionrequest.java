package entity;

import java.io.Serializable;

/**
 * The Class extensionrequest.
 */
public class extensionrequest implements Serializable
{
	
	/** The stage. */
	private String stage;
	
	/** The it handler. */
	private String itHandler;
	
	/** The reason. */
	private String reason;
	
	/** The time. */
	private int time;
	
	/** The id. */
	private int id;
	
	/** The status. */
	private String status;
  
  /**
   * Instantiates a new extensionrequest.
   *
   * @param id the id
   * @param stage the stage
   * @param itHandler the it handler
   * @param reason the reason
   * @param time the time
   * @param status the status
   */
  public extensionrequest(int id,String stage,String itHandler,String reason,int time,String status)
  {
	  this.id=id;
	  this.stage=stage;
	  this.itHandler=itHandler;
	  this.time=time;
	  this.reason=reason;
	  this.status=status;
  }
  
  /**
   * Gets the stage.
   *
   * @return the stage
   */
  public String getStage() {
		return stage;
	}
	
	/**
	 * Sets the stage.
	 *
	 * @param stage the new stage
	 */
	public void setStage(String stage) {
		this.stage = stage;
	}
	
	/**
	 * Gets the it handler.
	 *
	 * @return the it handler
	 */
	public String getItHandler() {
		return itHandler;
	}
	
	/**
	 * Sets the it handler.
	 *
	 * @param itHandler the new it handler
	 */
	public void setItHandler(String itHandler) {
		this.itHandler = itHandler;
	}
	
	/**
	 * Gets the reason.
	 *
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}
	
	/**
	 * Sets the reason.
	 *
	 * @param reason the new reason
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	/**
	 * Gets the time.
	 *
	 * @return the time
	 */
	public int getTime() {
		return time;
	}
	
	/**
	 * Sets the time.
	 *
	 * @param time the new time
	 */
	public void setTime(int time) {
		this.time = time;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}
}
