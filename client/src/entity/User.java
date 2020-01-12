package entity;

import java.io.Serializable;

import Enums.Position;
/**
 * User class contain all fields of user.
 *
 * @author SHIRA
 */
public class User implements Serializable 
{	
	
	/** The Name. */
	private String Name;
	
	/** The Password. */
	private String Password;
	
	/** The job. */
	private Position job;
	
	/** The str position. */
	private String strPosition;
	
	/**
	 * Instantiates a new user.
	 *
	 * @param Name the name
	 * @param Password the password
	 * @param job the job
	 */
	public User(String Name,String Password,String job)
	{
		this.Name=Name; 
		this.Password=Password;
		this.strPosition=job;
		switch(job)
		{
			case "IT":
				this.job =Position.ITMember;
			    break;
			case "CC":
				this.job =Position.ITMember;
			    break;
			case "CEO":
				this.job =Position.ITMember;
			    break;
			case "superviser":
				this.job =Position.ITMember;
			    break;
			case "student":
			    this.job =Position.student;
			    break;
			case "lecturer":
			    this.job =Position.lecturer;
			    break;
			case "CollegeEmployee":
			    this.job =Position.CollegeEmployee;
			    break;
			case "IT-manager":
				this.job =Position.ITManager;
			    break;
			    
		}			
	}
	
	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() 
	{
		return Password;
	}
	
	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) 
	{
		Password = password;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() 
	{
		return Name;
	}
	
	/**
	 * Gets the str position.
	 *
	 * @return the str position
	 */
	public String getstrPosition() 
	{
		return strPosition;
	}
	
	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	public Position getPosition()
	{
		return job;
	}
}