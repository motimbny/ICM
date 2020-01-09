package entity;

import Enums.StageName;
/**
 * Stage class 
 * @author SHIRA
 *
 */
public class Stage { 
	
	private StageName stagename;
	private User ITHundler;
	private int timeAllotted;
	private int timeLeft;
	
	public int getTimeAllotted() 
	{
		return timeAllotted;
	}
	public void setTimeAllotted(int timeAllotted) 
	{
		this.timeAllotted = timeAllotted;
	}
	public int getTimeLeft() {
		return timeLeft;
	}
	public void setTimeLeft(int timeLeft) {
		this.timeLeft = timeLeft;
	}
	public StageName getStagename() 
	{
		return stagename;
	}
	public void setStagename(StageName stagename) 
	{
		this.stagename = stagename;
	}
	public User getITHundler() 
	{
		return ITHundler;
	}
	public void setITHundler(User iTHundler) 
	{
		ITHundler = iTHundler;
	}

}
