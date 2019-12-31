package entity;

import java.io.Serializable;

public class requestSuper implements Serializable
{
	private int id;
	private String currentStatus;
	private String currentStage;
   public requestSuper(int id,String currentStatus,String currentStage)
   {
	   this.setId(id);
	   this.setCurrentStatus(currentStatus);
	   this.setCurrentStage(currentStage);
   }
	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	public String getCurrentStatus() 
	{
		return currentStatus;
	}
	public void setCurrentStatus(String currentStatus)
	{
		this.currentStatus = currentStatus;
	}
	public String getCurrentStage()
	{
		return currentStage;
	}
	public void setCurrentStage(String currentStage) 
	{
		this.currentStage = currentStage;
	}
}
