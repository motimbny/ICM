package entity;

import java.io.Serializable;

import Enums.Position;
/**
 * User class contain all fields of user
 * @author SHIRA
 *
 */
public class User implements Serializable 
{	
	private String Name;
	private String Password;
	private Position job;
	private String strPosition;
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
	public String getPassword() 
	{
		return Password;
	}
	public void setPassword(String password) 
	{
		Password = password;
	}
	public String getName() 
	{
		return Name;
	}
	public String getstrPosition() 
	{
		return strPosition;
	}
	public Position getPosition()
	{
		return job;
	}
}