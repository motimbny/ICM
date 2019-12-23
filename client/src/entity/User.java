package entity;

import java.io.Serializable;

import Enums.Position;

public class User implements Serializable
{	
	private String Name;
	private String Password;
	private Position job;
	public User(String Name,String Password,String job)
	{
		this.Name=Name;
		this.Password=Password;
		if(job.equals("ITMember"))
			this.job =Position.ITMember;
		switch(job)
		{
			case "ITMember":
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
	public Position getPosition()
	{
		return job;
	}
}