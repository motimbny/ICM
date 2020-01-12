package entity;

import Enums.StageName;

/**
 * Stage class .
 *
 * @author SHIRA
 */
public class Stage { 
	
	/** The stagename. */
	private StageName stagename;
	
	/** The IT hundler. */
	private User ITHundler;
	
	/** The time allotted. */
	private int timeAllotted;
	
	/** The time left. */
	private int timeLeft;
	
	/**
	 * Gets the time allotted.
	 *
	 * @return the time allotted
	 */
	public int getTimeAllotted() 
	{
		return timeAllotted;
	}
	
	/**
	 * Sets the time allotted.
	 *
	 * @param timeAllotted the new time allotted
	 */
	public void setTimeAllotted(int timeAllotted) 
	{
		this.timeAllotted = timeAllotted;
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
	
	/**
	 * Gets the stagename.
	 *
	 * @return the stagename
	 */
	public StageName getStagename() 
	{
		return stagename;
	}
	
	/**
	 * Sets the stagename.
	 *
	 * @param stagename the new stagename
	 */
	public void setStagename(StageName stagename) 
	{
		this.stagename = stagename;
	}
	
	/**
	 * Gets the IT hundler.
	 *
	 * @return the IT hundler
	 */
	public User getITHundler() 
	{
		return ITHundler;
	}
	
	/**
	 * Sets the IT hundler.
	 *
	 * @param iTHundler the new IT hundler
	 */
	public void setITHundler(User iTHundler) 
	{
		ITHundler = iTHundler;
	}

}
